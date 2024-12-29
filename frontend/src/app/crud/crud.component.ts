import { Component, Input } from '@angular/core';
import { OffreService } from '../services/offre.service';
import { Offre } from '../shared/offre';
import * as $ from 'jquery';

@Component({
  selector: 'app-crud',
  templateUrl: './crud.component.html',
  styleUrls: ['./crud.component.css'],
})
export class CrudComponent {
  offres: Offre[] = [];
  categories: String[] = [];
  selectedProduct: Offre = {
    nom: '',
    prix: 0,
    categorie: '',
    duration: 0,
    image: '',
  };

  constructor(private offreService: OffreService) {
    console.log(this.getOffres());
  }

  ngOnInit(): void {
    this.offreService.getAllCategories().subscribe((categories: String[]) => {
      categories.unshift('All Genre');
      this.categories = categories;
    });

    this.offreService.getAllOffres().subscribe((offres: Offre[]) => {
      this.offres = offres.map((offre) => {
        // Ensure image is displayed as a base64 string in the img src
        if (offre.image) {
          offre.image = 'data:image/jpeg;base64,' + offre.image; // Adjust the MIME type based on your image format
        }
        return offre;
      });
    });
  }

  setOffres(offres: Offre[]) {
    this.offres = offres;
  }
  getOffres() {
    return this.offres;
  }

  getOffresByCategorie(cat: String) {
    return this.offres.filter(
      (o) => cat === 'All Genre' || o.categorie === cat
    );
  }
  getRemiseOffres() {
    return this.offres.filter((o) => o.remise != null);
  }
  setCategories(categories: String[]) {
    this.categories = categories;
  }
  getCategories() {
    return this.categories;
  }
  formatCategory(category: String): string {
    return category.replace(/\s+/g, '-').toLowerCase();
  }
  editProduct(product?: any) {
    console.log(this.selectedProduct.id);
    this.selectedProduct = { ...product };
    console.log(this.selectedProduct.id);
  }
  clickButton() {
    document.getElementById('closeButton')?.click();
  }
  deleteButton() {
    const index = this.offres.findIndex(
      (p) => p.id === this.selectedProduct.id
    );
    this.offreService.deleteOffreById(this.selectedProduct.id).subscribe(() => {
      this.offres.splice(index, 1);
      // Delete category if it doesn't exist anymore after deletion
      const categoryExists = this.offres.some(
        (o) => o.categorie === this.selectedProduct.categorie
      );
      if (!categoryExists) {
        this.categories = this.categories.filter(
          (cat) => cat !== this.selectedProduct.categorie
        );
      }
      document.getElementById('closeButton')?.click();
    });
  }
  updateProduct() {
    const index = this.offres.findIndex(
      (p) => p.id === this.selectedProduct.id
    );

    if (this.selectedProduct.id !== null && index !== -1) {
      // Update existing course
      this.offres[index] = { ...this.selectedProduct };
      if (this.selectedProduct.prix==null) {
        this.selectedProduct.prix=0
      }
      if (this.selectedProduct.remise==null) {
        this.selectedProduct.remise=0
      }
      this.offreService
        .updateOffre(this.selectedProduct.id, this.selectedProduct)
        .subscribe({
          complete: () => {
            this.offreService
              .getAllCategories()
              .subscribe((categories: String[]) => {
                categories.unshift('All Genre');
                this.categories = categories;
              });
          },
        });
    } else {
      // Add new product
      this.offreService
        .postOffre(this.selectedProduct)
        .subscribe((newOffre: Offre) => {
          this.offres.push(newOffre); // Add the new course to the UI
          this.refreshCategories();
        });
    }

    document.getElementById('closeButton').click();
  }

  refreshCategories() {
    this.offreService.getAllCategories().subscribe((categories: String[]) => {
      categories.unshift('All Genre');
      this.categories = categories;
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input?.files?.[0];

    if (file) {
      const reader = new FileReader();

      // When the file is loaded, update the image source
      reader.onload = (e) => {
        this.selectedProduct.image = e.target?.result as string;
      };

      reader.readAsDataURL(file); // Read the file as a data URL
    }
  }
}

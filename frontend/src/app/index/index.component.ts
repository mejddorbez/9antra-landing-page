import { Component, OnInit } from '@angular/core';
import { OffreService } from '../services/offre.service';
import { Offre } from '../shared/offre';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit{

  offres : Offre[] = []
  randoffres : Offre[] = []
  categories : String[] = []
  
  constructor(private offreService : OffreService) {
    console.log(this.getOffres());
  }
  
  ngOnInit(): void {
    this.offreService.getAllCategories().subscribe((categories: String[]) => {
      categories.unshift("All Genre")
      this.categories = categories
    })
    this.offreService.getRandOffres().subscribe((offres: Offre[]) => {
      this.randoffres = offres.map(offre => {
        // Ensure image is displayed as a base64 string in the img src
        if (offre.image) {
          offre.image = 'data:image/jpeg;base64,' + offre.image;  // Adjust the MIME type based on your image format
        }
        return offre;
      });
    });
    this.offreService.getAllOffres().subscribe((offres: Offre[]) => {
      this.offres = offres.map(offre => {
        // Ensure image is displayed as a base64 string in the img src
        if (offre.image) {
          offre.image = 'data:image/jpeg;base64,' + offre.image;  // Adjust the MIME type based on your image format
        }
        return offre;
      });
    });
  }

  setOffres(offres : Offre[]) {
    this.offres = offres
  }
  getOffres(){
    return this.offres
  }
  setRandOffres(offres : Offre[]) {
    this.randoffres = offres
  }
  getRandOffres(){
    return this.randoffres
  }
  getOffresByCategorie(cat:String){
    return this.offres.filter(o=>cat==="All Genre" || o.categorie===cat)
  }
  getRemiseOffres(){
    return this.offres.filter(o=>o.remise!=null)
  }
  setCategories(categories : String[]) {
    this.categories = categories
  }
  getCategories(){
    return this.categories
  }
  formatCategory(category: String): string {
    return category.replace(/\s+/g, '-').toLowerCase();
  }
}

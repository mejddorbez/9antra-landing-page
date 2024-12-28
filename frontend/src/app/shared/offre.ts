export class Offre {
    id?: number;
    nom: string;
    description?: string;
    prix: number;
    remise?: number;
    categorie: string;
    duration: number;
    image: string;

    constructor(
        nom: string,
        prix: number,
        categorie: string,
        duration: number,
        image: string,
        id?: number,
        description?: string,
        remise?: number
    ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.remise = remise;
        this.categorie = categorie;
        this.duration = duration;
        this.image = image;
    }
}

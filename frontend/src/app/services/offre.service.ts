import { Inject, Injectable } from '@angular/core';
import { Offre } from '../shared/offre';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class OffreService {

  headers = {
    headers: new HttpHeaders()
  };

  constructor(
    private httpClient: HttpClient,
    @Inject('BaseURL') private baseURL: string,
    @Inject('API') private api: string
  ) {}

  getRandOffres(): Observable<Offre[]> {
    return this.httpClient.get<Offre[]>(`${this.baseURL}/${this.api}/offres/rand`);
  }
  getAllOffres(): Observable<Offre[]> {
    return this.httpClient.get<Offre[]>(`${this.baseURL}/${this.api}/offres`);
  }

  getAllCategories(): Observable<String[]> {
    return this.httpClient.get<String[]>(`${this.baseURL}/${this.api}/offres/categorie`);
  }

  getOffreById(id: number): Observable<Offre> {
    return this.httpClient.get<Offre>(`${this.baseURL}/${this.api}/offres/${id}`);
  }

  getImageOffreById(id: number): Observable<Blob> {
    return this.httpClient.get(`${this.baseURL}/${this.api}/offres/file/${id}`, {
      responseType: 'blob'
    });
  }

  getOffresByNom(nom: string): Observable<Offre[]> {
    return this.httpClient.get<Offre[]>(`${this.baseURL}/${this.api}/offres/nom/${nom}`);
  }

  getOffresByCategorie(categorie: string): Observable<Offre[]> {
    return this.httpClient.get<Offre[]>(`${this.baseURL}/${this.api}/offres/categorie/${categorie}`);
  }

  postOffre(offre: Offre): Observable<Offre> {
    const formData = new FormData();
  
    // Check if image is a base64 string
    if (offre.image && typeof offre.image === 'string' && offre.image.startsWith('data:image')) {
      // Convert base64 string to Blob
      const byteString = atob(offre.image.split(',')[1]); // Remove the base64 header and decode
      const arrayBuffer = new ArrayBuffer(byteString.length);
      const uintArray = new Uint8Array(arrayBuffer);
  
      // Convert byte string to Uint8Array
      for (let i = 0; i < byteString.length; i++) {
        uintArray[i] = byteString.charCodeAt(i);
      }
  
      // Create a Blob from the Uint8Array
      const blob = new Blob([uintArray], { type: 'image/jpeg' }); // Adjust MIME type if necessary
      formData.append('image', blob, 'image.jpg'); // Specify a filename
    }
  
    // Append other fields of 'offre' to FormData
    for (const key in offre) {
      if (offre.hasOwnProperty(key) && key !== 'image') {
        formData.append(key, offre[key]);
      }
    }
  
    // Send the POST request with the form data
    return this.httpClient.post<Offre>(
      `${this.baseURL}/${this.api}/offres/file`, 
      formData
    );
  }
  

  postOffreFile(formData: FormData): Observable<string> {
    return this.httpClient.post<string>(`${this.baseURL}/${this.api}/offres/file`, formData, {
      headers: new HttpHeaders(),
    });
  }

  updateOffre(id: number, offre: Offre): Observable<Offre> {
    const formData = new FormData();
  
    // Check if image is a base64 string
    if (offre.image && typeof offre.image === 'string' && offre.image.startsWith('data:image')) {
      // Convert base64 string to Blob
      const byteString = atob(offre.image.split(',')[1]); // Remove the base64 header and decode
      const arrayBuffer = new ArrayBuffer(byteString.length);
      const uintArray = new Uint8Array(arrayBuffer);
      
      // Convert byte string to uint8 array
      for (let i = 0; i < byteString.length; i++) {
        uintArray[i] = byteString.charCodeAt(i);
      }
  
      // Create a Blob from the Uint8Array
      const blob = new Blob([uintArray], { type: 'image/jpeg' }); // Change MIME type if necessary
      formData.append('image', blob, 'image.jpg'); // You can specify a filename here
    }
  
    // Append other fields of 'offre' to FormData
    for (const key in offre) {
      if (offre.hasOwnProperty(key) && key !== 'image') {
        formData.append(key, offre[key]);
      }
    }
  
    // Send the request with the form data
    return this.httpClient.put<Offre>(
      `${this.baseURL}/${this.api}/offres/file/${id}`, 
      formData
    );
  }
  
  
  deleteOffreById(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseURL}/${this.api}/offres/${id}`, this.headers);
  }
}

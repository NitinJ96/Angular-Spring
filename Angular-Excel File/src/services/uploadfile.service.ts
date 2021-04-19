import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadfileService {

  baseUrl = "http://localhost:8080";
    
  constructor(private http:HttpClient) { }
  
  // Returns an observable
  upload(file):Observable<any> {
  
      // Create form data
      const formData = new FormData(); 
        
      // Store form name as "file" with file data
      formData.append("file",file);
        
      // Make http post request over api
      // with formData as req
      return this.http.post("http://localhost:8080/fileUpload", formData)
  }

  getExcel(fileName: string, fileType: string) {
    return this.http.get<any>(`${this.baseUrl}/getExcel/${fileName}`, { responseType: 'arraybuffer' as 'json' });
  }
  
}

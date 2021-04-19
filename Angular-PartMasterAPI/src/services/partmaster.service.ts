import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PartmasterService {

  baseURL: string = "http://localhost:8080";

  constructor(private http:HttpClient) { }

  excelUpload(file: File):Observable<any>{
    const formData = new FormData();
    formData.append("file",file);
    let params = new HttpParams()
    .set('', file.name)
    .set('', "GMT")
    return this.http.post(this.baseURL+"/ExcelUpload", formData, {params: params})

  }

  logdownload(fileName: string):Observable<any>{
    let params = new HttpParams()
    .set('filename', fileName)
    return this.http.get<any>(this.baseURL+"/logdownload",{ responseType: 'arraybuffer' as 'json', params: params });
  }

  downloadTemplate(fileName: string) {
    let params = new HttpParams()
    .set('', fileName)
    .set('', "GMT")
    return this.http.get<any>(this.baseURL+"/downloadTemplate",{ responseType: 'arraybuffer' as 'json', params: params });
  }

  downloadExcel(fileName: string){
    let params = new HttpParams()
    .set('', fileName)
    .set('', "GMT")
    return this.http.get<any>(this.baseURL+"/downloadExcel",{ responseType: 'arraybuffer' as 'json', params: params });
  }
  
}

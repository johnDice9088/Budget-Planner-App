import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../environments/environment";
import { NewIncome, TotalIncome } from "../models/TotalIncome.model";
import { Observable } from "rxjs";


@Injectable({
    providedIn:'root',
})
export class ApiService{
    constructor(private http:HttpClient){
        
    }

    getAllIncome():Observable<TotalIncome[]>{
        return this.http.get<TotalIncome[]>(`${environment.apiUrl}/getall`);
    }

    postIncome(income:Partial<NewIncome>):Observable<TotalIncome>{
        return this.http.post<TotalIncome>(`${environment.apiUrl}/save`,income)
    } 

    updateOne(id:number,income:TotalIncome){
        return this.http.put<TotalIncome>(`${environment.apiUrl}/updateone/${id}`,income)
    }

    updateALL(id:number,data:TotalIncome){
        return this.http.put<TotalIncome>(`${environment.apiUrl}/update/${id}`,data)
    }
    deleteOneId(id:number){
        return this.http.delete(`${environment.apiUrl}/delete/${id}`,{
            responseType:'text'
        })
    }

    deleteAll(){
        return this.http.delete(`${environment.apiUrl}/delete`,{
            responseType:'text'
        })
    }
    search(data:string):Observable<TotalIncome[]>{
        return this.http.get<TotalIncome[]>(`${environment.apiUrl}/search?query=${data}`)
    }

}
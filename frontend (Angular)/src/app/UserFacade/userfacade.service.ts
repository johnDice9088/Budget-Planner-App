import { NewIncome } from './../models/TotalIncome.model';
import { Injectable } from '@angular/core';
import { TotalIncome } from '../models/TotalIncome.model';
import { ApiService } from '../services/api_service.service';
import { StateService } from '../Stateservice/state.service';
import { catchError, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserFacadeService {
  constructor(private state: StateService, private apiservice: ApiService) {}

  loadIncomes() {
    this.apiservice
      .getAllIncome()
      .subscribe((s: TotalIncome[]) => this.state.getIncome(s));
  }

  postAllIncomes(data:Partial <NewIncome>) {
    this.apiservice.postIncome(data).subscribe((s: TotalIncome) => {
      this.state.postLatestIncome(s);
    });
  }
  delete(id: number) {
    this.apiservice
      .deleteOneId(id)
      .pipe(
        tap((s) => console.log(s)),
        catchError((err) => {
          console.error('Delete failed:', err);
          return throwError(() => err);
        })
      )
      .subscribe((s) => this.state.deleteId(id));
  }

  updateOneId(id: number, data: any) {
    console.log(data);
    this.apiservice
      .updateOne(id, data)
      .subscribe((s) => this.state.updateLatestIncome(id, s));
  }

  deleteAll(){
    this.apiservice.deleteAll().subscribe(s=>this.state.deleteAll())
  }

  search(data:string){
    this.apiservice.search(data).subscribe((s)=>this.state.search(s))
  }
}

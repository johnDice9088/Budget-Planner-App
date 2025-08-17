import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TotalIncome } from '../models/TotalIncome.model';

@Injectable({
  providedIn: 'root',
})
export class StateService {
  private incomes = new BehaviorSubject<TotalIncome[]>([]);
  incomes$ = this.incomes.asObservable();

  private cachedata:TotalIncome[]=[]
  getIncome(data: TotalIncome[]) {
    this.cachedata= data
    return this.incomes.next(data);
  }

  postLatestIncome(data: TotalIncome) {
    const add = this.incomes.value;
    return this.incomes.next([...add, data]); 
  }

  deleteId(id: number): void {
    const current = this.incomes.value;
    const filtered = current.filter((income) => income.id !== id);
    this.incomes.next(filtered); // UI updates via async pipe
  }

  updateLatestIncome(id:number,data:TotalIncome){
        const edited = this.incomes.value.map((i)=>i.id ===id ? data:i);
        console.log(edited)
        return this.incomes?.next(edited);
      }

      deleteAll(){
        this.incomes.next([]);
      }
      search(data:TotalIncome[]){
        return this.incomes.next(data);
      }

      resetAll(){
        return this.incomes.next(this.cachedata);
      }
}

import { NewIncome, TotalIncome } from './../models/TotalIncome.model';
import { ApiService } from '../services/api_service.service';
import { environment } from './../environments/environment';
import { Component } from '@angular/core';
import { tap } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { StateService } from '../Stateservice/state.service';
import { UserFacadeService } from '../UserFacade/userfacade.service';

@Component({
  selector: 'app-revenue',
  standalone: true,
  imports: [NgFor, NgIf, ReactiveFormsModule, AsyncPipe],
  templateUrl: './revenue.component.html',
  styleUrl: './revenue.component.css',
})
export class RevenueComponent {
  constructor(
    private apiservice: ApiService,
    private state: StateService,
    private userfacade: UserFacadeService
  ) {}

  editButton: boolean = false;

  defaultValues = {
    category: '',
    amount: 0,
    note: '',
  };
  form = new FormGroup({
    category: new FormControl(this.defaultValues.category),
    amount: new FormControl(this.defaultValues.amount),
    note: new FormControl(this.defaultValues.note),
  });
  showAddForm: boolean = false;

  income$ = this.state.incomes$;
  UserID: number = 0;
  income: Partial<NewIncome> = {};
  original!: NewIncome;
  ngOnInit() {
    this.userfacade.loadIncomes();
  }

  onsubmit() {
    if (this.editButton) {
      this.income = {
        category: this.form.controls.category?.value,
        amount: this.form.controls.amount?.value,
        note: this.form.controls.note?.value,
      };
      const updated: Partial<NewIncome> = {};

      (Object.keys(this.original) as (keyof NewIncome)[]).forEach((s) => {
        if (this.original[s] !== this.income[s]) {
          updated[s] = this.income[s] as keyof NewIncome;
        }
      });
      this.income = updated;

      this.userfacade.updateOneId(this.UserID, this.income);
    } 
    else {
      this.income = {
        category: this.form.controls.category.value,
        amount: this.form.controls.amount.value,
        note: this.form.controls.note.value,
      };
      this.userfacade.postAllIncomes(this.income);
    }
    this.showAddForm = false;
    this.form.reset(this.defaultValues);
  }

  editIncome(id: TotalIncome) {
    this.UserID = id.id;
    this.form.setValue({
      category: id.category,
      amount: id.amount,
      note: id.note,
    });
    this.original = this.form.getRawValue();
    this.showAddForm = true;
    this.editButton = true;
  }

  deleteOne(id: number) {
    this.userfacade.delete(id);
  }

  deleteAll() {
    this.userfacade.deleteAll();
  }
}

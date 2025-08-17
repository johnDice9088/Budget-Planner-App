import { StateService } from './../Stateservice/state.service';
import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { debounceTime, distinctUntilChanged, tap } from 'rxjs';
import { UserFacadeService } from '../UserFacade/userfacade.service';

@Component({
  selector: 'app-sidebar-nav-bar',
  standalone: true,
  imports: [RouterLink, RouterOutlet, ReactiveFormsModule],
  templateUrl: './sidebar-nav-bar.component.html',
  styleUrl: './sidebar-nav-bar.component.css',
})
export class SidebarNavBarComponent {
  constructor(private userfacade:UserFacadeService,private state:StateService) {}
  searchBox = new FormControl('');

  ngOnInit() {
    this.searchBox.valueChanges.pipe(
      debounceTime(300),
  distinctUntilChanged(),tap(s =>{
      if(s == ''){
        this.state.resetAll();
      }
      else{

        this.userfacade.search(s ??'')
      }
    }
  )
  ).subscribe();
  }
}


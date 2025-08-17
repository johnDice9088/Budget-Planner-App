import { Component } from '@angular/core';
import { SidebarNavBarComponent } from "../sidebar-nav-bar/sidebar-nav-bar.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [SidebarNavBarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}

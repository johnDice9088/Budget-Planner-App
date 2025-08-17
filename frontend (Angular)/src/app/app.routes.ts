import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthenticationComponent } from './authentication/authentication.component';
import { SidebarNavBarComponent } from './sidebar-nav-bar/sidebar-nav-bar.component';
import { RevenueComponent } from './revenue/revenue.component';

export const routes: Routes = [


    {path:'',
        redirectTo:'login',
        pathMatch:'full' 

    },
    {
        path:'login',
        component:AuthenticationComponent,
      
    },
    {
        path:'',
        component:SidebarNavBarComponent,
          children:[
         {
        path:'home',
        component:HomeComponent
    },
       {
        path:'revenue',
        component:RevenueComponent
    },

        ]
    },
    {
        path:'**',
        redirectTo:'login'
    }
   
];

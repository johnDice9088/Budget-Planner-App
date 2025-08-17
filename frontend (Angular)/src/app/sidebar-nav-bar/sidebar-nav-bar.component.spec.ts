import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarNavBarComponent } from './sidebar-nav-bar.component';

describe('SidebarNavBarComponent', () => {
  let component: SidebarNavBarComponent;
  let fixture: ComponentFixture<SidebarNavBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SidebarNavBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SidebarNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

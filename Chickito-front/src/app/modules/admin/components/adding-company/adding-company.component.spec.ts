import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddingCompanyComponent } from './adding-company.component';

describe('AddingCompanyComponent', () => {
  let component: AddingCompanyComponent;
  let fixture: ComponentFixture<AddingCompanyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddingCompanyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddingCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

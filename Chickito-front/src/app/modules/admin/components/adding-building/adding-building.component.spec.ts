import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddingBuildingComponent } from './adding-building.component';

describe('AddingBuildingComponent', () => {
  let component: AddingBuildingComponent;
  let fixture: ComponentFixture<AddingBuildingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddingBuildingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddingBuildingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

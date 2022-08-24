import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMachineMaintenanceComponent } from './new-machine-maintenance.component';

describe('NewMachineMaintenanceComponent', () => {
  let component: NewMachineMaintenanceComponent;
  let fixture: ComponentFixture<NewMachineMaintenanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMachineMaintenanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewMachineMaintenanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineMaintenancesViewComponent } from './machine-maintenances-view.component';

describe('MachineMaintenancesViewComponent', () => {
  let component: MachineMaintenancesViewComponent;
  let fixture: ComponentFixture<MachineMaintenancesViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MachineMaintenancesViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MachineMaintenancesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

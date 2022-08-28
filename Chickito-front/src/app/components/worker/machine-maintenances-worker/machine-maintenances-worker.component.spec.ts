import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineMaintenancesWorkerComponent } from './machine-maintenances-worker.component';

describe('MachineMaintenancesWorkerComponent', () => {
  let component: MachineMaintenancesWorkerComponent;
  let fixture: ComponentFixture<MachineMaintenancesWorkerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MachineMaintenancesWorkerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MachineMaintenancesWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

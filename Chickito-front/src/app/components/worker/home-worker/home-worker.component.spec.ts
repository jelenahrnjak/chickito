import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeWorkerComponent } from './home-worker.component';

describe('HomeWorkerComponent', () => {
  let component: HomeWorkerComponent;
  let fixture: ComponentFixture<HomeWorkerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeWorkerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

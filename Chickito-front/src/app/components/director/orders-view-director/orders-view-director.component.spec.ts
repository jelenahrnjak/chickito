import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersViewDirectorComponent } from './orders-view-director.component';

describe('OrdersViewDirectorComponent', () => {
  let component: OrdersViewDirectorComponent;
  let fixture: ComponentFixture<OrdersViewDirectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdersViewDirectorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrdersViewDirectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

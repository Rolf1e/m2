import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DealShopComponent } from './deal-shop.component';

describe('DealShopComponent', () => {
  let component: DealShopComponent;
  let fixture: ComponentFixture<DealShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DealShopComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DealShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

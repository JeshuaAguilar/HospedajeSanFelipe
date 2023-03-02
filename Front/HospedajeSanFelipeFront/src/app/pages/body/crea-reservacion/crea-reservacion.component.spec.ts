import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreaReservacionComponent } from './crea-reservacion.component';

describe('CreaReservacionComponent', () => {
  let component: CreaReservacionComponent;
  let fixture: ComponentFixture<CreaReservacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreaReservacionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreaReservacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

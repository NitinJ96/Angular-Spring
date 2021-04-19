import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetexcelComponent } from './getexcel.component';

describe('GetexcelComponent', () => {
  let component: GetexcelComponent;
  let fixture: ComponentFixture<GetexcelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetexcelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetexcelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

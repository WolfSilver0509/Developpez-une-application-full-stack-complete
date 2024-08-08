import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribeButtonComponentComponent } from './subscribe-button-component.component';

describe('SubscribeButtonComponentComponent', () => {
  let component: SubscribeButtonComponentComponent;
  let fixture: ComponentFixture<SubscribeButtonComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribeButtonComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubscribeButtonComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribeListTopicComponent } from './subscribe-list-topic.component';

describe('SubscribeListTopicComponent', () => {
  let component: SubscribeListTopicComponent;
  let fixture: ComponentFixture<SubscribeListTopicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribeListTopicComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubscribeListTopicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

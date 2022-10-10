import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveVideoDetailComponent } from './save-video-detail.component';

describe('SaveVideoDetailComponent', () => {
  let component: SaveVideoDetailComponent;
  let fixture: ComponentFixture<SaveVideoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveVideoDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaveVideoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

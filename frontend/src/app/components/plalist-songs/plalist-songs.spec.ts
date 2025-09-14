import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlalistSongs } from './plalist-songs';

describe('PlalistSongs', () => {
  let component: PlalistSongs;
  let fixture: ComponentFixture<PlalistSongs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlalistSongs]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlalistSongs);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

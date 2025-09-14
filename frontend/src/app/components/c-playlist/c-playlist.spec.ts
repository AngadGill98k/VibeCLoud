import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CPlaylist } from './c-playlist';

describe('CPlaylist', () => {
  let component: CPlaylist;
  let fixture: ComponentFixture<CPlaylist>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CPlaylist]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CPlaylist);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

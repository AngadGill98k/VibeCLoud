import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CAlbum } from './c-album';

describe('CAlbum', () => {
  let component: CAlbum;
  let fixture: ComponentFixture<CAlbum>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CAlbum]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CAlbum);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

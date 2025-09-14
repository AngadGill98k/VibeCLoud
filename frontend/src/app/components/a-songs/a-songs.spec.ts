import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ASongs } from './a-songs';

describe('ASongs', () => {
  let component: ASongs;
  let fixture: ComponentFixture<ASongs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ASongs]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ASongs);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

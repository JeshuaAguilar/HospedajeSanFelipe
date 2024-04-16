import { TestBed } from '@angular/core/testing';

import { RangeSelectionStrategyService } from './range-selection-strategy.service';

describe('RangeSelectionStrategyService', () => {
  let service: RangeSelectionStrategyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RangeSelectionStrategyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

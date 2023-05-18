import { TestBed } from '@angular/core/testing';

import { ValidacaoCpfService } from './validacao-cpf.service';

describe('ValidacaoCpfService', () => {
  let service: ValidacaoCpfService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValidacaoCpfService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

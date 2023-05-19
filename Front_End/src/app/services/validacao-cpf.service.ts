import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CpfService {
  constructor() {}

  validarCpf(cpf: any): boolean {
    // Remova qualquer formatação do CPF (como pontos e traços)
    cpf = cpf.replace(/\D/g, '');

    // Verifique se o CPF possui 11 dígitos
    if (cpf.length !== 11) {
      return false;
    }

    // Verifique se todos os dígitos são iguais
    if (/^(\d)\1+$/.test(cpf)) {
      return false;
    }

    // Validação dos dígitos verificadores
    let sum = 0;
    let remainder: number;

    for (let i = 1; i <= 9; i++) {
      sum += parseInt(cpf.substring(i - 1, i)) * (11 - i);
    }

    remainder = (sum * 10) % 11;

    if (remainder === 10 || remainder === 11) {
      remainder = 0;
    }

    if (remainder !== parseInt(cpf.substring(9, 10))) {
      return false;
    }

    sum = 0;

    for (let i = 1; i <= 10; i++) {
      sum += parseInt(cpf.substring(i - 1, i)) * (12 - i);
    }

    remainder = (sum * 10) % 11;

    if (remainder === 10 || remainder === 11) {
      remainder = 0;
    }

    if (remainder !== parseInt(cpf.substring(10, 11))) {
      return false;
    }

    return true;
  }
}

# Requisitos Funcionais

## Histórico
|**Versão**|**Data**|**Alteração no Documento**|**Autor**|
|------|----|---------|-----|
|1.0| 13/05/2023 | Criação do documento | [Guilherme-Abraao](https://github.com/Guilherme-Abraao) |
|<Versão>|< data >|< descrição >|< autor >|

Os requisitos funcionais foram classificados de acordo com sua prioridade, adotando-se os seguintes critérios:

**Alta**: H.U. imprescindíveis para o funcionamento do sistema;

**Média**: H.U. que são optativas, sugestões e funcionalidades que são importantes mas não essenciais;

**Baixa**: funcionalidades médias que são de difícil implementação (devido ao tempo para implementar o projeto).


### H.U. 01 - Cadastro de clientes 

Versão: 1.0
Prioridade: alta

Como cliente do Pet Shop, quero realizar um cadastro, para acessar as funcionalidades do sistema.

#### Cenário 1: 

Dado que eu esteja na tela de cadastro, quando preencher os campos de Nome, CPF, data de nascimento, telefone, endereço de e-mail, endereço e senha corretamente E clicar no botão “confirmar” então o sistema deve me informar que o cadastro foi realizado com sucesso.

#### Cenário 2: 

Dado que eu esteja na tela de cadastro, quando preencher algum campo inválido ou vazio E clicar no botão “confirmar” então o sistema deve me informar quais campos estão faltando ou estão incorretos. 

---

### H.U. 02 - Cadastro de funcionários

Versão: 1.0
Prioridade: alta

Como administrador, quero realizar um cadastro de um funcionário, para garantir que ele possa acessar as funcionalidades do sistema.

#### Cenário 1: 

Dado que eu esteja na tela de cadastro de funcionários, quando preencher os campos de Nome, CPF, data de nascimento, telefone, endereço, endereço de e-mail, cargo, salário e senha corretamente E clicar sobre o botão “confirmar” então o sistema deve me informar que o cadastro foi realizado com sucesso.

#### Cenário 2: 

Dado que eu esteja na tela de cadastro, quando preencher algum campo inválido ou vazio E clicar sobre o botão “confirmar” então o sistema deve me informar quais campos estão faltando ou estão incorretos. 

---

### H.U. 03 - Cadastro de administrador

Versão: 1.0
Prioridade: alta

Como administrador, quero realizar um cadastro de um outro administrador, para garantir que ele possa acessar as funcionalidades do sistema.

#### Cenário 1: 

Dado que eu esteja na tela de cadastro de funcionários, quando preencher os campos de Nome, CPF, data de nascimento, telefone, endereço de e-mail, endereço, cargo, salário e senha corretamente E clicar sobre o botão “confirmar” então o sistema deve me informar que o cadastro foi realizado com sucesso.

#### Cenário 2: 

Dado que eu esteja na tela de cadastro, quando preencher algum campo inválido ou vazio E clicar sobre o botão “confirmar” então o sistema deve me informar quais campos estão faltando ou estão incorretos. 

---

### H.U. 04 - Editar cadastro (clientes)

Versão: 1.0
Prioridade: alta

Como cliente do Pet Shop, quero alterar dados cadastrais, para deixar o sistema atualizado com as informações corretas. 

#### Cenário 1: 

Dado que eu esteja na tela editar perfil, quando alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço, endereço de e-mail e senha) corretamente E clicar sobre o botão “confirmar” então o sistema deve me informar que o cadastro foi atualizado com sucesso.

#### Cenário 2: 

Dado que eu esteja na tela editar perfil, quando alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço, endereço de e-mail e senha) deixando vazio ou incorreto E clicar sobre o botão “confirmar” então o sistema deve me informar quais campos estão faltando ou estão incorretos, além de mostrar que o cadastro não foi atualizado. 

---



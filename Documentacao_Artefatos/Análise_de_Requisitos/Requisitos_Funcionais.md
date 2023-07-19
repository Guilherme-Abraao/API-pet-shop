# Requisitos Funcionais

## Histórico
|**Versão**|**Data**|**Alteração no Documento**|**Autor**|
|------|----|---------|-----|
|1.0| 13/05/2023 | Criação do documento | Guilherme-Abraao |
|1.1| 15/05/2023 | Inserção do H.U. 05 ao H.U. 09 | Wendel-Fl |
|1.2| 15/05/2023 | Inserção do H.U. 10 ao H.U. 13 | GabrielCamiloOliveira |
|1.3| 19/05/2023 | Remoção dos campos de endereço | GabrielCamiloOliveira |
|1.4| 30/06/2023 | Inserção do H.U. 14 ao H.U. 17 | GustavoMedeiroz |

Os requisitos funcionais foram classificados de acordo com sua prioridade, adotando-se os seguintes critérios:

**Alta**: H.U. imprescindíveis para o funcionamento do sistema;

**Média**: H.U. que são optativas, sugestões e funcionalidades que são importantes mas não essenciais;

**Baixa**: funcionalidades médias que são de difícil implementação (devido ao tempo para implementar o projeto).

---
### H.U. 01 - Cadastro de clientes 

Versão: 1.0  
Prioridade: alta

**Como** cliente do Pet Shop, **quero** realizar um cadastro, **para** acessar as funcionalidades do sistema.

#### Cenário 1: 

**Dado** que eu esteja na tela de cadastro, **quando** preencher os campos de Nome, CPF, data de nascimento, telefone, endereço de e-mail e senha corretamente **E** clicar no botão “confirmar”, **então** o sistema deve me informar que o cadastro foi realizado com sucesso.

#### Cenário 2: 

**Dado** que eu esteja na tela de cadastro, **quando** preencher algum campo inválido ou vazio **E** clicar no botão “confirmar”, **então** o sistema deve me informar quais campos estão faltando ou estão incorretos. 

---

### H.U. 02 - Cadastro de funcionários

Versão: 1.0  
Prioridade: alta

**Como** administrador, **quero** realizar um cadastro de um funcionário, **para** garantir que ele possa acessar as funcionalidades do sistema.

#### Cenário 1: 

**Dado** que eu esteja na tela de cadastro de funcionários, **quando** preencher os campos de Nome, CPF, data de nascimento, telefone, endereço de e-mail, cargo, salário e senha corretamente **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar que o cadastro foi realizado com sucesso.

#### Cenário 2: 

**Dado** que eu esteja na tela de cadastro, **quando** preencher algum campo inválido ou vazio **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar quais campos estão faltando ou estão incorretos. 

---

### H.U. 03 - Cadastro de administrador

Versão: 1.0  
Prioridade: alta

**Como** administrador, **quero** realizar um cadastro de um outro administrador, **para** garantir que ele possa acessar as funcionalidades do sistema.

#### Cenário 1: 

**Dado** que eu esteja na tela de cadastro de funcionários, **quando** preencher os campos de Nome, CPF, data de nascimento, telefone, endereço de e-mail, cargo, salário e senha corretamente **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar que o cadastro foi realizado com sucesso.

#### Cenário 2: 

**Dado** que eu esteja na tela de cadastro, **quando** preencher algum campo inválido ou vazio **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar quais campos estão faltando ou estão incorretos. 

---

### H.U. 04 - Editar cadastro (clientes)

Versão: 1.0  
Prioridade: alta

**Como** cliente do Pet Shop, **quero** alterar dados cadastrais, **para** deixar o sistema atualizado com as informações corretas. 

#### Cenário 1: 

**Dado** que eu esteja na tela editar perfil, **quando** alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço de e-mail e senha) corretamente **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar que o cadastro foi atualizado com sucesso.

#### Cenário 2: 

**Dado** que eu esteja na tela editar perfil, **quando** alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço de e-mail e senha) deixando vazio ou incorreto **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar quais campos estão faltando ou estão incorretos, além de mostrar que o cadastro não foi atualizado. 

---


### H.U. 05 - Editar cadastro (funcionários)

Versão: 1.0  
Prioridade: alta

**Como** administrador, **quero** alterar dados cadastrais, **para** deixar o sistema atualizado com as informações corretas.

#### Cenário 1:

**Dado** que eu esteja na tela editar perfil, **quando** alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço de e-mail, cargo, salário e senha) corretamente **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar que o cadastro foi atualizado com sucesso.

#### Cenário 2:

**Dado** que eu esteja na tela editar perfil, **quando** alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço de e-mail, cargo, salário e senha) deixando vazio ou incorreto **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar quais campos estão faltando ou estão incorretos, além de mostrar que o cadastro não foi atualizado.

---

### H.U. 06 - Editar cadastro (administrador)

Versão: 1.0  
Prioridade: alta

**Como** administrador do Pet Shop, **quero** alterar dados cadastrais, **para** deixar o sistema atualizado com as informações corretas. 

#### Cenário 1:

**Dado** que eu esteja na tela editar perfil, **quando** alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço de e-mail, cargo, salário e senha) corretamente **E** clicar no botão “confirmar”, **então** o sistema deve me informar que o cadastro foi atualizado com sucesso

#### Cenário 2:

**Dado** que eu esteja na tela editar perfil, **quando** alterar algum campo do cadastro (Nome, data de nascimento, telefone, endereço de e-mail, cargo, salário e senha) deixando vazio ou incorreto **E** clicar no botão “confirmar”, **então** o sistema deve me informar quais campos estão faltando ou estão incorretos, além de mostrar que o cadastro não foi atualizado.

---

### H.U. 07 - Login no sistema (cliente)

Versão: 1.0  
Prioridade: alta

**Como** cliente do Pet Shop, **quero** realizar login no sistema, **para** acessar as funcionalidades do sistema.

#### Cenário 1:

**Dado** que eu esteja na tela de login do sistema, **quando** preencher corretamente os campos de CPF e senha **E** clicar sobre o botão “realizar login”, **então** o sistema deve me informar que o login foi realizado com sucesso **E** redirecionar para o página principal.

#### Cenário 2:

**Dado** que eu esteja na tela de login do sistema, **quando** preencher os campos de CPF e senha errados ou vazios **E** clicar sobre o botão “realizar login”, **então** o sistema deve me informar que o login e a senha não correspondem.

---

### H.U. 08 - Login no sistema (funcionário)

Versão: 1.0  
Prioridade: alta

**Como** funcionário do Pet Shop, **quero** realizar login, **para** acessar as funcionalidades do sistema.

#### Cenário 1: 

**Dado** que eu esteja na tela de login do sistema, **quando** preencher corretamente os campos de CPF e senha **E** clicar sobre o botão “realizar login”, **então** o sistema deve me informar que o login foi realizado com sucesso **E** redirecionar para o página principal.

#### Cenário 2: 

**Dado** que eu esteja na tela de login do sistema, **quando** preencher os campos de CPF e senha errados ou vazios **E** clicar sobre o botão “realizar login”, **então** o sistema deve me informar que o login e a senha não correspondem. 

---

### H.U. 09 - Login no sistema (administrador)

Versão: 1.0  
Prioridade: alta

**Como** administrador do Pet Shop, **quero** realizar login, **para** acessar as funcionalidades do sistema.

#### Cenário 1:

**Dado** que eu esteja na tela de login do sistema, **quando** preencher corretamente os campos de CPF e senha **E** clicar sobre o botão “realizar login”, **então** o sistema deve me informar que o login foi realizado com sucesso **E** redirecionar para o página principal.

#### Cenário 2:

**Dado** que eu esteja na tela de login do sistema, **quando** preencher os campos de CPF e senha errados ou vazios **E** clicar sobre o botão “realizar login”, **então** o sistema deve me informar que o login e a senha não correspondem.

---

### H.U. 10 - Cadastro de animais 

Versão: 1.0  
Prioridade: alta

**Como** cliente, **quero** realizar um cadastro de um animal de estimação **para** realizar agendamentos de serviços.

#### Cenário 1: 

**Dado** que eu esteja logado no sistema como cliente **E** na tela de cadastro de animais, **quando** preencher os campos de nome, data de nascimento, espécie e raça **E** clicar sobre o botão “confirmar”, **então** o sistema deve me informar que o cadastro foi efetuado com sucesso.

#### Cenário 2: 

**Dado** que eu esteja logado no sistema como cliente, **quando** preencher os campos erroneamente ou vazio(s) **E** clicar sobre o botão “confirmar”, **então** o sistema deve informar quais campos estão faltando ou estão incorretos. 

---

### H.U. 11 - Editar cadastro de animais 

Versão: 1.0  
Prioridade: alta

**Como** cliente, **quero** realizar um cadastro de um animal de estimação **para** realizar agendamentos de serviços.

#### Cenário 1: 

**Dado** que eu esteja logado no sistema como cliente **E** na tela de visualização de animais cadastrados, **quando** alterar algum campo do cadastro (Nome, data de nascimento, raça ou espécie) corretamente **E** clicar sobre o botão “confirmar”, **então** o sistema deve informar que as informações foram atualizadas com sucesso.

#### Cenário 2: 

**Dado** que eu esteja logado no sistema como cliente **E** na tela de visualização de animais cadastrados, **quando** alterar algum campo do cadastro (Nome, data de nascimento, raça ou espécie) erroneamente ou deixá-lo(s) vazio(s) **E** clicar sobre o botão “confirmar”, **então** o sistema deve informar quais campos estão faltando ou estão incorretos, além de mostrar que as informações não foram atualizadas.

---

### H.U. 12 - Deletar cadastro de animais 

Versão: 1.0  
Prioridade: alta

**Como** cliente, **quero** deletar um cadastro de um animal de estimação **para** não mostrar mais o cadastro dele. 

#### Cenário 1: 

**Dado** que eu esteja logado no sistema como cliente **E** na tela de visualização de animais cadastrados, **quando** clicar em “Excluir” **E** o sistema perguntar “Tem certeza que deseja excluir o cadastro desse animal?" **E** clicar em “Sim”, **então** o cadastro do animal deixará de ser mostrado no perfil do cliente, mas existente no banco de dados. 

#### Cenário 2:

**Dado** que eu esteja logado no sistema como cliente **E** na tela de visualização de animais cadastrados, **quando** clicar em “Excluir” **E** o sistema perguntar “Tem certeza que deseja excluir o cadastro desse animal?" **E** clicar em “Não”, **então** o sistema deve voltar a tela de visualização de animais cadastrados. 

---

### H.U. 13 - Agendamento de serviços

Versão: 1.0  
Prioridade: alta

**Como** cliente, **quero** escolher uma data e horário disponíveis **para** realizar um agendamento de algum serviço para um animal cadastrado no meu perfil.

#### Cenário 1:

**Dado** que eu esteja logado no sistema como cliente **E** na tela de agendamento, **quando** selecionar uma data e horário disponível **E** informar o animal e serviço(s) requisitado(s) **E** clicar sobre o botão “confirmar agendamento”, **então** o sistema deve me informar que o agendamento foi efetuado com sucesso **E** registrar o horário agendado como “ocupado”.

#### Cenário 2:

**Dado** que eu esteja logado no sistema como cliente **E** na tela de agendamento, **quando** selecionar uma data e horário indisponível, **então** o sistema deve me informar que já existem serviços agendados para o horário selecionado.

--- 

### H.U. 14 - Visualização de Agendamento

Versão: 1.0
Prioridade: alta

**Como** cliente, **quero** visualizar os agendamentos de serviços feitos por mim **para** confirmar o agendamento e não me esquecer do horário e data.

#### Cenário 1: 
**Dado** que eu esteja logado no sistema como cliente **E** estiver na tela inicial **E** possuir um agendamento marcado **então** o sistema deve me informar todos os agendamentos futuros que foram feitos para os animais cadastrados em meu perfil.

#### Cenário 2: 
**Dado** que eu esteja logado no sistema como cliente **E** estiver na tela inicial **E** não possuir ao menos um agendamento marcado **então** o sistema deve me informar que não há agendamentos futuros marcados.

#### Cenário 3:
**Dado** que eu esteja logado no sistema **como** cliente **E** estiver na tela inicial **E** possui um agendamento que não foi realizado **então** o sistema deve me informar que o agendamento não foi realizado.

---

### H.U. 15 - Visualização de Agendamento para funcionário

Versão: 1.0
Prioridade: alta

**Como** funcionário, **quero** visualizar os agendamentos de serviços destinados a mim **para** me inteirar dos horários.

#### Cenário 1: 
**Dado** que eu esteja logado no sistema **como** funcionário **E** estiver na tela inicial **E** possuir um agendamento marcado **então** o sistema deve me informar todos os agendamentos futuros com a descrição do serviço e informações de horário, data e cliente (incluindo o animal).

#### Cenário 2: 
**Dado** que eu esteja logado no sistema **como** funcionário **E** estiver na tela inicial **E** não possuir ao menos um agendamento marcado **então** o sistema deve me informar que não há agendamentos de serviços.

#### Cenário 3:
**Dado** que eu esteja logado no sistema **como** funcionário **E** estiver na tela inicial **E** possui um agendamento atrasado **então** o sistema deve me informar que o agendamento não foi realizado e o número do cliente para contato.

---

### H.U. 16 - Edição de Agendamento

Versão: 1.0
Prioridade: alta

**Como** cliente, **quero** conseguir editar o agendamento já feito **para** alterar informações, datas e horários passador de forma errada.

#### Cenário 1: 
**Dado** que eu tenha feito um agendamento **E** estiver na tela inicial **E** verificar informação gravada de forma errada **então** o sistema deve disponibilizar a edição do agendamento.


---

### H.U. 17 - Cancelamento de Agendamento

Versão: 1.0
Prioridade: alta

**Como** cliente, **quero** ter a possibilidade de cancelar o agendamento **para** evitar pagar o serviço de forma a não utiliza-lo e não deixar horário vago no petshop.

#### Cenário 1: 
**Dado** que eu tenha feito um agendamento **E** queira cancelar o agendamento feito **então** o sistema deve disponibilizar o cancelamento até 12 horas antes do horário e data marcados.


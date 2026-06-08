# Desafio Transferência ⇄🧾

## Sobre o Projeto

Aplicação desenvolvida para o desafio de agendamento de transferências financeiras utilizando Java 11 e Spring Boot.

O sistema permite que usuários realizem agendamentos de transferências entre contas bancárias, realizando automaticamente o cálculo das taxas de acordo com as regras de negócio definidas no enunciado.

---

## Tecnologias Utilizadas

* Java 11
* Spring Boot
* Spring Data JPA
* H2 Database
* JUnit 5
* Mockito
* Swagger/OpenAPI

---

## Regras de Negócio

### Agendamento de Transferências

O sistema permite o agendamento de transferências contendo:

* Conta de origem
* Conta de destino
* Valor da transferência
* Data da transferência
* Data de agendamento (gerada automaticamente pelo sistema)

### Validações

* Não permite datas anteriores à data atual.
* Permite agendamento de até 50 dias a partir da data atual.
* Contas devem seguir o formato definido pela aplicação.
* Caso não exista uma taxa aplicável para o período informado, a transferência não é permitida.

### Formato da Conta

Como o enunciado não especifica detalhadamente o formato das contas bancárias, foi adotado o padrão:

```text
XXXXXXXX-X
```

Onde:

* 8 dígitos numéricos
* hífen (-)
* 1 dígito verificador

Totalizando 10 caracteres.

---

## Tabela de Taxas

| Dias para Transferência | Taxa Fixa | Taxa Percentual |
| ----------------------- | --------- | --------------- |
| 0                       | R$ 3,00   | 2,5%            |
| 1 a 10                  | R$ 12,00  | 0%              |
| 11 a 20                 | R$ 0,00   | 8,2%            |
| 21 a 30                 | R$ 0,00   | 6,9%            |
| 31 a 40                 | R$ 0,00   | 4,7%            |
| 41 a 50                 | R$ 0,00   | 1,7%            |

Caso não exista uma taxa aplicável para o período informado, a operação é rejeitada.

---

## Arquitetura

A aplicação foi desenvolvida seguindo uma arquitetura em camadas, promovendo separação de responsabilidades e facilidade de manutenção.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
H2 Database
```

### Controller

Responsável por:

* Receber as requisições HTTP.
* Validar os dados de entrada através dos DTOs.
* Delegar o processamento para a camada de serviço.

### Service

#### TaxaService

Responsável por:

* Calcular a taxa da transferência.
* Determinar a faixa de cobrança aplicável.

#### TransferenciaService

Responsável por:

* Validar regras de negócio.
* Validar contas informadas.
* Solicitar o cálculo da taxa.
* Persistir transferências.
* Consultar agendamentos realizados.

### Camada de Domínio

Foi criada uma camada de domínio para representar as faixas de cobrança da tabela de taxas.

Essa abordagem evita o uso excessivo de estruturas condicionais encadeadas (`if/else`) e facilita futuras alterações nas regras de negócio.

---

## Endpoints

### Agendar Transferência

```http
POST /v1/transferencia
```

#### Exemplo de Requisição

```json
{
  "contaOrigem": "01038890-2",
  "contaDestino": "01038890-4",
  "valorTransferencia": 100.00,
  "dataTransferencia": "2026-07-15"
}
```

---

### Consultar Extrato

```http
GET /v1/transferencia
```

Retorna todos os agendamentos cadastrados.

---

## Tratamento de Exceções

A aplicação possui exceções específicas para cenários de erro de validação e regras de negócio.

### Exemplos

* Conta inválida
* Data da transferência anterior à data atual
* Data da transferência superior a 50 dias
* Não existe taxa aplicável para o período informado

### GlobalExceptionHandler

Todas as exceções são centralizadas e tratadas através do `GlobalExceptionHandler`, garantindo respostas padronizadas e mensagens amigáveis para o cliente da API.

---

## Testes

Foram implementados testes automatizados utilizando JUnit e Mockito.

### Cobertura

* Testes unitários da camada de serviço.
* Testes de controller.
* Testes de integração do fluxo completo.
* Validação das regras de negócio.
* Validação dos cálculos de taxa.

### Executar os Testes

```bash
mvn clean test
```

---

## Banco de Dados H2

A aplicação utiliza banco de dados H2 em memória.

Acesso:

```text
http://localhost:8080/h2-console
```

Utilize as credenciais exibidas no log da aplicação durante a inicialização.

---

## Documentação da API

Swagger/OpenAPI:

```text
http://localhost:8080/swagger-ui/index.html#/
```

---

## Como Executar o Projeto

### Clonar o Repositório

```bash
git clone <repositorio>
```

### Entrar na Pasta

```bash
cd desafio-transferencia
```

### Compilar o Projeto

```bash
mvn clean install
```

### Executar a Aplicação

```bash
mvn spring-boot:run
```

---

## Decisões Arquiteturais

* Utilização de DTOs para desacoplamento entre API e domínio.
* Separação de responsabilidades através das camadas Controller, Service e Repository.
* Centralização do tratamento de erros via GlobalExceptionHandler.
* Modelagem das faixas de taxa através de objetos de domínio para evitar lógica condicional excessiva.
* Utilização do banco H2 em memória conforme solicitado pelo desafio.
* Cobertura de testes automatizados para validação das regras de negócio e fluxo da aplicação.

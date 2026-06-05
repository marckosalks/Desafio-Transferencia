## DESAFIO TRANSFERÊNCIA ⇄🧾

## Tecnologias

- Java 11
- Spring Boot
- Spring Data JPA
- H2 Database
- JUnit 


## Regras de negócio

- Não permite datas passadas
- Máximo de 50 dias para agendamento
- Taxas calculadas conforme tabela


 Observação: Considerou-se o formato de conta bancária como XXXXXXXX-X (8 dígitos + hífen + dígito verificador), totalizando 10 caracteres, por não haver especificação detalhada no enunciado.

 ## Como planejo fazer esse projeto?

### Entidades
 -Transferencia

### DTO de entrada / saída
 -json enviado pelo usuário : 
  contaOrigem, contaDestino, valorTransferencia, dataTransferencia
  (data de agendamento gerado pelo backend)


### Controller
Tenho apenas um controller que aplica as validações presentes no DTO
assim caso tenha algum erro de validação aciona a exceção de validação.


### Serviços
-Taxa, reponsavel por:
calcular a taxas

-Transferencia, reponsavel por:
validar contas,chamar TaxaService, salvar no banco, listar agendamentos


### Camada de dominio
Foi criado uma camada de dominio pra auxiliar na modelagem dos dados
da tabela de taxas, preferi fazer assim do que simplesmente usar ifs aninhados


### Endpoits:
POST/entrada de dados
GET/extrato das transferencias
 -Exibir extrato, responsável por:
  verificar ultimas transferencias feitas
  (pode ser tanto no log, quanto no banco)

### Exceptions:
Temos exceptions especificas que passo no codigo mesmo, tanto de validação da entrada de dados,
quanto para erros de regra de negocio ou erros aleatórios porem tratados para exibir uma critica amigavel.


-Conta inválida

-Data da transferência anterior à data atual

Quando a data da transferencia for superior a cinquenta dias ou
anterior a data atual;


-Não existe taxa aplicável para o período informado

### GlobalExceptionHandler

Todos os erros passam por aqui, e destino no codigo qual exceção vou disparar

### Testes

## Arquitetura

Controller

↓

Service

↓

Repository

↓

H2 Database

## Banco de dados H2 (em memoria)

http://localhost:8080/h2-console

-precisa colocar o nome que está no log pra
acessar o banco


## Swagger da api:

http://localhost:8080/swagger-ui/index.html#/

## Como executar:

git clone <repositorio>

cd desafio-transferencia

mvn clean install

mvn spring-boot:run

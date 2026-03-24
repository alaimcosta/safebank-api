# 💳 API de Pagamentos SAFEBANK

SAFEBANK é uma API REST para gerenciamento e processamento de pagamentos. O sistema possibilita o recebimento de pagamentos de débitos de pessoas físicas e jurídicas e foi desenvolvido com o framework **Spring Boot**.

---

## 🚀 Tecnologias utilizadas

* ☕ Java 17+
* 🌱 Spring Boot
* 📦 Spring Data JPA
* 🗄️ H2 Database
* 📄 OpenAPI / Swagger

---

## 📁 Estrutura do projeto

```
src/main/java/br/com/safebank
├── interface/
│   ├── controller/                      # Endpoints REST
│   │     └── PagamentoController.java
│   └── dto/                             # Objetos de entrada/saída
├── infra/        
│   └── repository/                      # Acesso ao banco
├── domain/
│   ├── enums
│   ├── model                            # Entidades JPA
│   └── service                          # Regras de negócio
```

---

## ⚙️ Como executar o projeto

### Pré-requisitos

* Java 17
* Maven

---

### ▶️ Executando

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```
---

## 🗄️ Banco de dados (H2)

O projeto utiliza o banco em memória H2 para facilitar o desenvolvimento.

### 🔗 Acessar o console

```
http://localhost:8080/h2-console
```

### 🔧 Configuração

| Campo    | Valor              |
| -------- |--------------------|
| JDBC URL | jdbc:h2:mem:testdb |
| User     | sa                 |
| Password |               |

### 🧪 Mock de dados no banco

#### 📌 Inserindo dados de status de pagamento.
```
INSERT INTO status_pagamento (id, tipo, descricao) VALUES (1, 'PENDENTE', 'Pendente de Processamento');
INSERT INTO status_pagamento (id, tipo, descricao) VALUES (2, 'SUCESSO', 'Processado com Sucesso');
INSERT INTO status_pagamento (id, tipo, descricao) VALUES (3, 'FALHA', 'Processado com Falha');
```
---

## 📚 Documentação da API

Documentação disponível via Swagger.

### 🔗 Acessar Swagger

```
http://localhost:8080/swagger-ui/index.html
```

### 🔗 OpenAPI JSON

```
http://localhost:8080/v1/pagamento
```

---

## 📌 Endpoints

### ➕ Criar pagamento

```
POST /
```

#### 📥 Request

```json
{
  "codigoDebito": 1,
  "cpfCnpj": "544.992.098-02",
  "metodoPagamento": "CARTAO_CREDITO",
  "numeroCartao": "34409392928939",
  "valor": 1.45
}
```

#### 📤 Response

```json
{
  "id": 1,
  "codigoDebito": 1,
  "cpfCnpj": "54499209802",
  "status": "PENDENTE",
  "valor": 1.45
}
```

---

### 📄 Listar pagamentos

```
GET /pagamento?cpfCnpj=54499209802
```
#### 📤 Response

```json
[
  {
    "id": 1,
    "codigoDebito": 1,
    "cpfCnpj": "54499209802",
    "status": "PENDENTE",
    "valor": 1.45
  }
]
```
---
### 🔄 Atualizar status de pagamentos

```
PUT /status
```
#### 📤 Request

```json
{
  "idPagamento": 1,
  "status": "SUCESSO"
}
```
---
### 🗑️ Excluir pagamentos

```
DELETE /{id}
```
---

## 📊 Status de pagamento

| Status    | Descrição                  |
|-----------|----------------------------|
| PENDENTE  | Pendente de Processamento  |
| SUCESSO   | Processado com Sucesso     |
| FALHA     | Processado com Falha       |

---

## ⚠️ Tratamento de erros

A API utiliza um padrão de resposta para erros:

```json
{
  "timestamp": "2026-03-24T00:33:25.480Z",
  "status": 404,
  "error": "Not Found",
  "path": "/pagamento/3"
}
```

## 👨‍💻 Autor

Desenvolvido por Alaim de Jesus Leão Costa

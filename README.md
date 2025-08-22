# Desafio Técnico: Microsserviços com Spring
## 📖 Sobre
Este projeto implementa uma arquitetura de microsserviços com Spring Boot e Spring Cloud, como parte do desafio técnico do bootcamp "NTT DATA Java e IA para Iniciantes". Agradeço à [Digital Innovation One (DIO)](https://web.dio.me/home) e NTT DATA pela oportunidade.


A aplicação simula um catálogo de produtos e um serviço de pedidos, acessados através de um API Gateway central.

## 🛠️ Tecnologias Utilizadas
- Java 21

- Spring Boot 3.5.5

- Spring Cloud (Gateway, OpenFeign, Eureka)

- Spring Data JPA & Spring Security

- Maven & H2 Database

## 🚀 Como Executar
As aplicações devem ser iniciadas na seguinte ordem:

service-discovery (porta 8761)

ms-catalogo-produtos (porta 8100)

ms-simulador-pedidos (porta 8200)

api-gateway (porta 8700)

## ✅ Como Testar (via API Gateway)
Todas as requisições devem ser feitas para a porta do Gateway: http://localhost:8700.

Segurança: Todas as chamadas exigem um Bearer Token no cabeçalho de autorização.

Header: Authorization

Token: Bearer NTT-DATA-CHALLENGE-2025

### 1. Criar um novo produto
Método: POST

URL: http://localhost:8700/produtos

```bash
{
    "nome": "Produto Teste",
    "descricao": "Descrição do produto.",
    "preco": 99.99
}
```
### 2. Simular um pedido
Método: POST

URL: http://localhost:8700/pedidos

```bash
{
    "idsProdutos": [1, 2]
}
```
## Autores
[Ciro Henrique](https://github.com/cirohopa)

---
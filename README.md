# prjint3-backend

API REST criada em **Java 21** com **Spring Boot**, **Spring Data JPA**, **MySQL** e **SpringDoc Swagger**.

## Recursos implementados

- CRUD de clientes
- CRUD de funcionários
- CRUD de produtos
- CRUD de mídias
- CRUD de reservas
- CRUD de vendas
- Swagger/OpenAPI
- Validação de entrada com Bean Validation
- Tratamento centralizado de erros
- Apagado lógico por status:
  - `-1` = apagado
  - `0` = inativo
  - `1` = ativo

## Observação importante sobre o banco enviado

A estrutura original tem dois pontos que impedem um CRUD JPA completo:

1. A tabela `midias` não possui chave primária.
2. A tabela `vendas` não possui coluna de status, embora o projeto peça apagado lógico para registros.

Por isso, incluí o script:

```text
sql/02_ajustes_necessarios.sql
```

Execute esse script uma vez antes de rodar a aplicação.

## Configuração do banco

Edite o arquivo:

```text
src/main/resources/application.properties
```

Ajuste:

```properties
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

A URL já está configurada para:

```properties
jdbc:mysql://edumysql.acesso.rj.senac.br:3306/20261_prjint3_manha_victormerling
```

## Como rodar

Com Java 21 e Maven instalados:

```bash
mvn clean spring-boot:run
```

## Swagger

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/api-docs
```

## Endpoints principais

Cada recurso segue o mesmo padrão:

```text
GET    /api/clientes
GET    /api/clientes/ativos
GET    /api/clientes/{id}
POST   /api/clientes
PUT    /api/clientes/{id}
PATCH  /api/clientes/{id}/ativar
PATCH  /api/clientes/{id}/inativar
DELETE /api/clientes/{id}
```

Recursos disponíveis:

```text
/api/clientes
/api/funcionarios
/api/produtos
/api/midias
/api/reservas
/api/vendas
```

## Exemplos de JSON

### Cliente

```json
{
  "nome": "Maria Silva",
  "cpf": "12345678901",
  "telefone": "21999999999",
  "email": "maria@email.com",
  "chavePix": "maria@email.com",
  "senha": "123456",
  "status": 1
}
```

### Produto

```json
{
  "clienteId": 1,
  "descricao": "Camisa social azul",
  "preco": 89.90,
  "dataDeCadastro": "2026-06-03",
  "tamanho": "M",
  "genero": "masculino",
  "faixaEtaria": 18,
  "status": 1,
  "imagem": "https://exemplo.com/camisa.jpg"
}
```

### Mídia

```json
{
  "produtoId": 1,
  "url": "https://exemplo.com/foto1.jpg",
  "status": 1
}
```

### Reserva

```json
{
  "produtoId": 1,
  "clienteId": 1,
  "data": "2026-06-03",
  "preco": 89.90,
  "status": 1
}
```

### Venda

```json
{
  "data": "2026-06-03",
  "valor": 89.90,
  "produtoId": 1,
  "status": 1
}
```

## Observações de implementação

- O `DELETE` não remove fisicamente do banco. Ele apenas altera o status para `-1`.
- O endpoint `GET /api/{recurso}` lista registros com status diferente de `-1`.
- O endpoint `GET /api/{recurso}/ativos` lista apenas registros com status `1`.
- O campo original `prduto_faixaEtaria` foi mantido exatamente com esse nome no mapeamento JPA, pois está escrito assim na estrutura SQL enviada.
- As senhas foram mantidas como texto recebido no JSON para respeitar o escopo acadêmico do CRUD. Em produção, use criptografia com BCrypt e autenticação adequada.

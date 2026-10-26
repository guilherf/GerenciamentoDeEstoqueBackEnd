# Gerenciamento de Estoque (Back-end)

API REST em **Spring Boot** para controlar o **estoque de uma livraria**: cadastrar livros, consultar, buscar por nome, ajustar quantidades e remover.

## Para que serve

Ser o back-end de um sistema de estoque. Ele guarda dados de cada livro (preços, fornecedor, quantidade em estoque, estoque mínimo etc.) e expõe endpoints HTTP que um site ou aplicativo pode consumir. O CORS está liberado (`*`) para facilitar a integração com um front-end em desenvolvimento.

## Tecnologias

- Java 21 e Spring Boot 4.1.0
- Spring Web, Spring Data JPA e Bean Validation
- Banco **H2 em memória** (console em `/h2-console`)
- Lombok
- Maven Wrapper (`mvnw`), sem precisar instalar o Maven

## Endpoints

| Método | Rota | O que faz |
|---|---|---|
| `POST` | `/SalvarLivro` | Cadastra um livro. A data de entrada é gerada automaticamente e o status inicial é "Livro Disponivel". |
| `GET` | `/alllivros` | Lista todos os livros. |
| `GET` | `/buscarlivro/{id}` | Busca um livro pelo ID. |
| `GET` | `/buscarLivroNome/por-nome?nomeLivro=...` | Busca livros pelo nome exato; retorna `204` se não encontrar nada. |
| `PATCH` | `/{id}/adicionar?quantidade=N` | Soma `N` unidades ao estoque do livro. |
| `PATCH` | `/{id}/remover?quantidade=N` | Retira `N` unidades do estoque do livro. Não permite o estoque ficar negativo ("Estoque insuficiente para a operação"). |
| `DELETE` | `/deletarLivro/{id}` | Remove o livro. |

### Exemplo: cadastrar um livro

```bash
curl -X POST http://localhost:8080/SalvarLivro \
  -H "Content-Type: application/json" \
  -d '{
    "nomeLivro": "Dom Casmurro",
    "autor": "Machado de Assis",
    "editora": "Editora Exemplo",
    "cartegoria": "Romance",
    "fornecedor": "Distribuidora X",
    "precoCusto": 20.0,
    "precoVenda": 35.0,
    "margemLucro": 15.0,
    "estoque": 10,
    "estoqueMinimo": "3",
    "status": "Disponivel"
  }'
```

O único campo obrigatório é `nomeLivro`; sem ele a API responde `400` com a mensagem "Necessito do nome do livro".

## Estrutura do código

```
src/main/java/net/bifewagyu/gerenciamentoEstoque/
├── controllers/   # endpoints REST
├── service/       # regras de negócio
├── repository/    # acesso ao banco (Spring Data JPA)
├── entity/        # tabela livro_estoques
├── dtos/          # objetos de entrada (Request) e saída (Response) e erro
├── exceptions/    # tratamento de erros de validação
├── status/        # enum de status do livro
└── apiconfig/     # formato da data de entrada (dd/MM/yyyy HH:mm:ss)
```

## Como executar

Pré-requisito: **JDK 21**.

```bash
git clone https://github.com/guilherf/GerenciamentoDeEstoqueBackEnd.git
cd GerenciamentoDeEstoqueBackEnd
./mvnw spring-boot:run        # no Windows: mvnw.cmd spring-boot:run
```

A API sobe em `http://localhost:8080`. O console do banco fica em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:LivroEstoque`, usuário `BifeWagyu`, senha em branco).

> O banco é **em memória**: os dados são apagados sempre que a aplicação é reiniciada.

## Autor

Guilherme Ferreira — [@guilherf](https://github.com/guilherf)

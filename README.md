# ConectaTrabajo API
API para conexão entre jovens imigrantes e oportunidades de emprego


## ✨ Sobre o Projeto
Plataforma que facilita a integração de jovens refugiados (16-22 anos) ao mercado de trabalho brasileiro, promovendo diversidade e inclusão social.

## 🚀 Funcionalidades Implementadas

### <span style="color: #197d98">Cadastros Básicos</span>
- **Usuários** (jovens imigrantes)
- **Empresas** (oportunidades de emprego)
- **Vagas** (com detalhes de requisitos)
- **Endereços** (vinculados a usuários/empresas)

### <span style="color: #197d98">Operações CRUD</span>
| Entidade   | Endpoints                      |
|------------|--------------------------------|
| Usuários   | `POST /usuarios`, `GET /usuarios/{id}` |
| Empresas   | `POST /empresas`, `PUT /empresas/{id}` |
| Vagas      | `GET /vagas`, `DELETE /vagas/{id}`     |

## 🛠 Tecnologias
<div style="display: flex; gap: 10px; flex-wrap: wrap;">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</div>

## 📊 Modelo de Dados Simplificado
```mermaid
erDiagram
    USUARIO ||--o{ ENDERECO : possui
    EMPRESA ||--o{ ENDERECO : possui
    EMPRESA ||--o{ VAGA : publica
    USUARIO }|--|| CANDIDATURA : faz
    VAGA }|--|| CANDIDATURA : recebe
```

## 🛠️ Como Executar

1. **Pré-requisitos**:
    - Java 17
    - MySQL 8.x

2. **Configuração de Perfis: `dev` e `prod`**:
  Este projeto utiliza **dois perfis de execução** para separar os ambientes de desenvolvimento e produção:

- `dev`: ambiente local com banco H2 em memória
- `prod`: ambiente conectado ao banco de dados MySQL

### Estrutura dos Arquivos

- `src/main/resources/application-dev.properties`  
  → Usado no perfil de desenvolvimento

- `src/main/resources/application-prod.properties`  
  → Usado no perfil de produção

3. **Iniciar a aplicação**:
   #### Executar com Banco H2 (Desenvolvimento)
    ```bash
    mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
    ```

    Para acessar o console H2, acesse:  
    [http://localhost:8081/h2-console](http://localhost:8081/h2-console)


    #### Executar com MySQL (Produção)

    ```bash
    mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
    ```


    <!-- 
    #### Exemplo de `application-dev.properties`:

      ```properties
      spring.datasource.url=jdbc:h2:mem:testdb
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=

      spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
      spring.h2.console.enabled=true
      spring.h2.console.path=/h2-console
      ``` 
    #### Exemplo de `application-prod.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    ``` 
    -->

## 🌐 Endpoints Principais

### **Usuários** `(/usuarios)`
| Método | Rota               | Descrição                          | Status |
|--------|--------------------|------------------------------------|--------|
| POST   | `/usuarios`        | Cria novo usuário                  | ✅      |
| GET    | `/usuarios/{id}`   | Busca usuário por ID               | ✅      |
| PUT    | `/usuarios/{id}`   | Atualiza usuário                   | ✅      |
| DELETE | `/usuarios/{id}`   | Remove usuário                     | ✅      |

**Exemplo POST**:
```json
{
  "nome": "Carlos Silva",
  "email": "carlos@email.com",
  "cpf": "12345678901",
  "dataNascimento": "2000-01-01"
}
```

### **Vagas** `(/vagas)`
| Método | Rota               | Descrição                          | Status |
|--------|--------------------|------------------------------------|--------|
| POST   | `/vagas`           | Cria nova vaga                     | ✅      |
| GET    | `/vagas/{id}`      | Busca vaga por ID                  | ✅      |
| PUT    | `/vagas/{id}`      | Atualiza vaga                      | ✅      |
| DELETE | `/vagas/{id}`      | Remove vaga                        | ✅      |


**Exemplo POST**:
```json
{
  "titulo": "Auxiliar Administrativo",
  "descricao": "Vaga para jovens com ensino médio completo",
  "salario": 1800.50
}
```

### **Endereços** `(/enderecos)`
| Método | Rota                       | Descrição                          | Status |
|--------|----------------------------|------------------------------------|--------|
| POST   | `/enderecos`               | Vincula endereço a usuário/empresa | ✅      |
| GET    | `/enderecos/usuario/{id}`  | Busca endereços por usuário        | ✅      |
| GET    | `/enderecos/empresa/{id}`  | Busca endereços por empresa        | ✅      |

**Exemplo POST**:
```json
{
  "cep": "12345678",
  "rua": "Av. Paulista",
  "numero": "1000",
  "usuario": { "id": 1 }
}
```
## 🛠 Como Testar
1. **Instale** o [Postman](https://www.postman.com/) ou use `curl`
2. **Importe** a coleção:
   ```bash
   curl -X POST http://localhost:8080/usuarios -H "Content-Type: application/json" -d '{"nome":"Exemplo", "email":"ex@test.com"}'
   ```

## 📚 Documentação
Acesse a documentação interativa:

Swagger UI: http://localhost:8080/docs


## 🤝 Contribuição
Ajude a melhorar esta iniciativa para jovens em situação de vulnerabilidade:
1. Faça um fork do projeto
2. Crie uma branch (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas alterações (`git commit -m 'Adiciona X'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request
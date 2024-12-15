### Documentação Detalhada do Projeto Back4App

---

#### Visão Geral
Este projeto Spring Boot gerencia clientes utilizando a API Back4App como back-end. Ele fornece funcionalidades CRUD para manipular dados de clientes, geração de relatórios em PDF e CSV, e uma interface de usuário baseada no Thymeleaf. As informações são manipuladas utilizando as camadas **Controller**, **Service** e **Repository**, garantindo uma arquitetura bem estruturada.

---

### Estrutura do Projeto

#### 1. **Classe `ClientController`**
**Pacote:** `br.com.javaricci.Back4App.Controller`  
**Responsabilidade:** Controlar as requisições HTTP e mapear endpoints para interagir com a camada de serviço.

**Principais Métodos:**
- **`listClients`** (`GET /clients/contatos`): Obtém a lista de clientes e a exibe em uma página HTML.
- **`newClient`** (`GET /clients/new`): Exibe o formulário para adicionar ou editar clientes.
- **`saveClient`** (`POST /clients`): Salva os dados de um cliente no Back4App.
- **`showAlterarContatoPage`** (`GET /clients/edit/{id}`): Recupera os dados de um cliente pelo ID e os exibe no formulário de edição.
- **`deleteClient`** (`GET /clients/delete/{id}`): Remove um cliente pelo ID.
- **`generatePdfReport`** (`GET /clients/pdf`): Gera um relatório PDF com os dados dos clientes.
- **`generateCsvReport`** (`GET /clients/csv`): Gera um relatório CSV com os dados dos clientes.

---

#### 2. **Classe `Client`**
**Pacote:** `br.com.javaricci.Back4App.Model`  
**Responsabilidade:** Representar o modelo de dados de um cliente.  
**Campos:**  
- `id`: Identificador único.  
- `name`: Nome do cliente.  
- `price`: Preço associado ao cliente.  
- `stock`: Estoque do cliente.  
- `selling`: Indica se o cliente está ativo (`true`/`false`).

---

#### 3. **Classe `ClientRepository`**
**Pacote:** `br.com.javaricci.Back4App.Repository`  
**Responsabilidade:** Comunicar-se com a API REST do Back4App para realizar operações CRUD.

**Principais Métodos:**
- **`findAllClients`**: Recupera todos os clientes da API Back4App.  
- **`findClientById`**: Recupera os detalhes de um cliente pelo ID.  
- **`addClient`**: Adiciona um novo cliente.  
- **`updateClient`**: Atualiza os dados de um cliente existente.  
- **`deleteClient`**: Remove um cliente pelo ID.

**Configuração da API:**
- `API_URL`: Endpoint da API Back4App.  
- `APP_ID` e `API_KEY`: Credenciais de autenticação.

---

#### 4. **Classe `ClientService`**
**Pacote:** `br.com.javaricci.Back4App.Service`  
**Responsabilidade:** Implementar a lógica de negócios do projeto.

**Principais Métodos:**
- **`findAllClients`**: Chama o repositório para listar todos os clientes.  
- **`findClientById`**: Busca um cliente específico pelo ID.  
- **`saveClient`**: Decide entre adicionar ou atualizar um cliente.  
- **`deleteClient`**: Remove um cliente pelo ID.  
- **`generatePdfReport`**: Gera um arquivo PDF com os dados dos clientes usando a biblioteca iText.  
- **`generateCsvReport`**: Gera um arquivo CSV com os dados dos clientes utilizando o Apache Commons CSV.

---

#### 5. **Arquivo `application.properties`**
**Configuração do Spring Boot:**
- Nome da aplicação: `Back4App`.
- Porta do servidor: `8081`.
- Contexto da aplicação: `/Back4App`.

---

### Interface do Usuário

#### Arquivo `form.html`
**Responsabilidade:** Formulário para adicionar ou editar clientes.  
**Funcionalidades:**  
- Preencher campos como ID, Nome, Salário, Estoque, e Status (`V/F`).  
- Máscara de entrada para o campo Salário.  
- Botão de salvamento que envia os dados para o servidor.

#### Arquivo `list.html`
**Responsabilidade:** Página de listagem dos clientes.  
**Funcionalidades:**  
- Tabela com informações como ID, Nome, Salário, Estoque, e Status.  
- Botões para editar, excluir ou gerar relatórios (PDF/CSV).

---

### Fluxo Geral

1. **Listagem de Clientes (`GET /clients/contatos`)**  
   - Usuário acessa a página inicial e visualiza a tabela de clientes cadastrados.

2. **Cadastro/Edição de Cliente (`GET /clients/new` ou `/clients/edit/{id}`)**  
   - Exibe o formulário preenchido para edição ou vazio para novo cadastro.

3. **Salvamento de Cliente (`POST /clients`)**  
   - Dados do cliente são enviados para o Back4App via método POST ou PUT.

4. **Exclusão de Cliente (`GET /clients/delete/{id}`)**  
   - Remove um cliente da base de dados.

5. **Geração de Relatórios (PDF/CSV)**  
   - Cria relatórios baseados nos clientes cadastrados.

---

### Relatórios
**PDF:** Relatório estruturado com colunas como ID, Nome, Salário, Estoque e Status.  
**CSV:** Arquivo delimitado por vírgulas com as mesmas informações.

---

### Dependências do Projeto
- **Spring Boot:** Framework principal para o desenvolvimento.  
- **Thymeleaf:** Gerador de páginas HTML dinâmicas.  
- **iText:** Geração de relatórios PDF.  
- **Apache Commons CSV:** Manipulação de arquivos CSV.  
- **HTTP Client Java:** Comunicação com a API REST.  

---

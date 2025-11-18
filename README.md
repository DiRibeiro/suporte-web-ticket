# 🛠️ Sistema de Suporte / Help Desk (Spring Boot + Vue 3)

Este repositório contém um sistema completo de **Suporte / Help Desk**, com:

- **Backend**: API REST em Java + Spring Boot  
- **Frontend**: SPA em Vue 3 + Vite  
- **Banco de dados**: PostgreSQL  

O objetivo é fornecer uma base organizada para gestão de **tickets**, **usuários**, **técnicos** e **categorias**, com separação entre **área do cliente** e **área administrativa**.

---

## 📂 Estrutura do repositório

```text
.
├── suporte-backend/        # Backend (Spring Boot, Java, PostgreSQL)
│   └── src/main/java/com/example/suporte
│       ├── config/         # Configurações (ex: GlobalExceptionHandler)
│       ├── controller/     # REST controllers
│       ├── domain/         # Entidades JPA + enum Status
│       ├── dto/            # DTOs (inclui TicketRequest/ResponseDTO)
│       ├── repository/     # Interfaces Spring Data JPA
│       └── service/        # Regras de negócio
│
├── suporte-frontend/       # Frontend (Vue 3 + Vite)
│   ├── src/
│   │   ├── api/            # Clientes HTTP (axios) para tickets, usuários, técnicos, categorias
│   │   ├── components/     # Componentes de formulário com tooltips de ajuda (❓)
│   │   ├── router/         # Rotas (cliente x admin)
│   │   └── views/          # Telas (tickets, usuários, técnicos, categorias)
│   ├── index.html
│   ├── package.json
│   ├── pnpm-lock.yaml      # Gerenciador de pacotes (pnpm) - pode usar npm/yarn também
│   └── vite.config.js
│
├── LICENSE                 # Licença do projeto (se aplicável)
└── README.md               # Este arquivo
Cada subprojeto possui seu próprio README com detalhes específicos:

suporte-backend/README.md – configuração do Spring Boot, banco, etc.

suporte-frontend/README.md – rotas, comandos do Vite, etc.

🔧 Tecnologias utilizadas
Backend – suporte-backend
Java 17+

Spring Boot

Spring Web

Spring Data JPA

Bean Validation (Jakarta Validation)

PostgreSQL

Maven

JPA / Hibernate

Camadas principais:

domain – entidades: Ticket, Usuario, Tecnico, Categoria, Comentario, Status (enum)

repository – interfaces JpaRepository

service – regras de negócio e orquestração

controller – endpoints REST (/api/tickets, /api/usuarios, etc.)

dto – TicketRequestDTO, TicketResponseDTO, UsuarioDTO, TecnicoDTO, CategoriaDTO, ComentarioDTO

config – tratamento global de exceções, etc.

Frontend – suporte-frontend
Vue 3 (Composition API)

Vite

Vue Router

Axios

CSS simples (sem framework), incluindo:

Ícones de ajuda (❓) ao lado dos labels

Tooltips bonitos em CSS puro para explicar cada campo

🧱 Modelagem do domínio
O sistema foi baseado em um diagrama UML de suporte/atendimento:

Ticket

Campos: título, descrição, impacto, urgência, prioridade, status, criadoEm, atualizadoEm

Relacionamentos:

Categoria

Usuario (criador)

Tecnico (técnico atribuído)

Lista de Comentario

Usuario

id, nome, email

Pode criar/abrir tickets

Tecnico

id, nome, email

Lista de especialidades (List<String>)

Categoria

id, nome

Comentario

id, mensagem, criadoEm

Associado a um Ticket

🌐 Funcionalidades
Área do Cliente (frontend)
Listar meus tickets

Abrir novo ticket

Editar/excluir tickets próprios

Formulário orientado com tooltips de ajuda (❓)

No momento, o “usuário logado” na área do cliente é um mock (ID fixo configurado no frontend).

Área Administrativa (frontend)
Tickets

Listar todos

Criar / editar / excluir

Definir criador, categoria, técnico, impacto, urgência, prioridade, status

Usuários

CRUD completo

Técnicos

CRUD completo, incluindo especialidades

Categorias

CRUD completo

🚀 Como rodar o projeto
1. Banco de Dados (PostgreSQL)
Criar o banco (exemplo):

sql
Copiar código
CREATE DATABASE suporte_db;
Ajustar suporte-backend/src/main/resources/application.yml com sua URL, usuário e senha do PostgreSQL:

yaml
Copiar código
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/suporte_db
    username: SEU_USUARIO
    password: SUA_SENHA

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
(Opcional) Executar scripts de mock para popular tabelas (usuários, técnicos, categorias, tickets).

2. Subir o backend (Spring Boot)
bash
Copiar código
cd suporte-backend
mvn spring-boot:run
A API ficará disponível em:

text
Copiar código
http://localhost:8080
3. Subir o frontend (Vue 3 + Vite)
bash
Copiar código
cd suporte-frontend

# usando pnpm (recomendado, já que existe pnpm-lock.yaml)
pnpm install
pnpm dev

# ou, se preferir:
# npm install
# npm run dev
A aplicação ficará disponível em:

text
Copiar código
http://localhost:5173
O vite.config.js já contém um proxy para /api → http://localhost:8080.

🧩 Padrão de UX – Tooltips de ajuda (❓)
Os formulários do frontend seguem um padrão de acessibilidade:

Ícone de ajuda ❓ ao lado do label

Tooltip em CSS puro explicando o campo

Funciona com mouse (hover) e teclado (focus no ícone)

Exemplo:

vue
Copiar código
<label class="label-with-help">
  Nome
  <span class="help-icon" tabindex="0">
    ❓
    <span class="tooltip">
      Texto de ajuda sobre o campo.
    </span>
  </span>
</label>
Esse padrão é usado em:

TicketForm.vue

UsuarioForm.vue

TecnicoForm.vue

CategoriaForm.vue

🔮 Próximos passos / ideias
Autenticação real (JWT, roles CLIENTE e ADMIN)

Controle de acesso por perfil no backend

Filtros de tickets (por status, categoria, técnico, período)

Dashboard com métricas de atendimento (SLA, tickets por técnico, etc.)

Upload de anexos em tickets

Melhorias visuais com um framework de UI (ex.: Tailwind, PrimeVue, etc.)

📜 Licença
Consulte o arquivo LICENSE na raiz do repositório para detalhes da licença de uso.
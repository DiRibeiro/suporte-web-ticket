```markdown
# 🎨 Suporte Frontend – Vue 3 + Vite

Este módulo é o **frontend** do sistema de Suporte / Help Desk, responsável pela interface web da:

- **Área do Cliente** – abertura e acompanhamento de tickets
- **Área Administrativa** – gestão de tickets, usuários, técnicos e categorias

Consome a API REST exposta pelo backend (`suporte-backend`).

---

## ⚙️ Tecnologias

- Vue 3 (Composition API)
- Vite
- Vue Router
- Axios
- CSS puro (sem UI framework) com:
  - Ícones de ajuda (❓) ao lado dos labels
  - Tooltips bonitos e acessíveis

---

## 📂 Estrutura de pastas

```text
suporte-frontend/
├── index.html
├── package.json
├── pnpm-lock.yaml
├── vite.config.js
└── src
    ├── api/
    │   ├── categorias.js
    │   ├── http.js
    │   ├── tecnicos.js
    │   ├── tickets.js
    │   └── usuarios.js
    ├── components/
    │   ├── CategoriaForm.vue
    │   ├── TecnicoForm.vue
    │   ├── TicketForm.vue
    │   └── UsuarioForm.vue
    ├── router/
    │   └── index.js
    ├── views/
    │   ├── CategoriaFormView.vue
    │   ├── CategoriasListView.vue
    │   ├── TecnicoFormView.vue
    │   ├── TecnicosListView.vue
    │   ├── TicketFormView.vue
    │   ├── TicketsListView.vue
    │   ├── UsuarioFormView.vue
    │   └── UsuariosListView.vue
    ├── App.vue
    ├── main.js
    └── style.css
🌐 Integração com o backend
Arquivo: vite.config.js

js
Copiar código
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
})
O frontend chama endpoints como /api/tickets

O proxy redireciona para http://localhost:8080/api/tickets (backend)

Certifique-se de que o backend (suporte-backend) esteja rodando em http://localhost:8080.

👣 Rotas e Navegação
Arquivo: src/router/index.js

Área do Cliente
/cliente/tickets – lista de tickets do cliente

/cliente/tickets/novo – abertura de novo ticket

/cliente/tickets/:id/editar – edição de ticket existente

No momento o “usuário logado” na área do cliente é um mock com CLIENTE_ID = 1 no código.

Área Administrativa
Tickets:

/admin/tickets

/admin/tickets/novo

/admin/tickets/:id/editar

Usuários:

/admin/usuarios

/admin/usuarios/novo

/admin/usuarios/:id/editar

Técnicos:

/admin/tecnicos

/admin/tecnicos/novo

/admin/tecnicos/:id/editar

Categorias:

/admin/categorias

/admin/categorias/novo

/admin/categorias/:id/editar

As rotas usam as views correspondentes em src/views.

📡 Camada de API (Axios)
Arquivo base: src/api/http.js

js
Copiar código
import axios from 'axios'

const http = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json'
  }
})

export default http
Clientes específicos:

src/api/tickets.js

src/api/usuarios.js

src/api/tecnicos.js

src/api/categorias.js

Cada arquivo exporta funções como:

listarTickets(params)

buscarTicket(id)

criarTicket(data)

atualizarTicket(id, data)

deletarTicket(id)

🧩 Formulários e Tooltips de Ajuda (❓)
Os componentes em src/components implementam formulários com ícone de ajuda:

TicketForm.vue

UsuarioForm.vue

TecnicoForm.vue

CategoriaForm.vue

Padrão:

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
.help-icon e .tooltip são estilizados em CSS puro

Tooltip aparece ao passar o mouse ou focar com teclado

Melhora a acessibilidade e entendimento de cada campo

▶️ Como executar o frontend
Na raiz do módulo suporte-frontend:

Usando pnpm (recomendado, já que existe pnpm-lock.yaml)
bash
Copiar código
pnpm install
pnpm dev
Usando npm
bash
Copiar código
npm install
npm run dev
Acesse no navegador:

text
Copiar código
http://localhost:5173
Lembre de manter o backend rodando em http://localhost:8080 para que as chamadas /api/... funcionem.

✅ Requisitos
Node.js (LTS)

pnpm ou npm

Backend suporte-backend rodando e acessível


## Documentação da API

#### Retorna todos os itens

```http
  GET /api/items
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

#### Retorna um item

```http
  GET /api/items/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |


## Etiquetas

Adicione etiquetas de algum lugar, como: [shields.io](https://shields.io/)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)


## Referência

 - [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Awesome README](https://github.com/matiassingers/awesome-readme)
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)

## Documentação de cores

| Cor               | Hexadecimal                                                |
| ----------------- | ---------------------------------------------------------------- |
| Cor exemplo       | ![#0a192f](https://via.placeholder.com/10/0a192f?text=+) #0a192f |
| Cor exemplo       | ![#f8f8f8](https://via.placeholder.com/10/f8f8f8?text=+) #f8f8f8 |
| Cor exemplo       | ![#00b48a](https://via.placeholder.com/10/00b48a?text=+) #00b48a |
| Cor exemplo       | ![#00d1a0](https://via.placeholder.com/10/00b48a?text=+) #00d1a0 |

import { createRouter, createWebHistory } from 'vue-router'
import TicketsListView from '../views/TicketsListView.vue'
import TicketFormView from '../views/TicketFormView.vue'
import UsuariosListView from '../views/UsuariosListView.vue'
import UsuarioFormView from '../views/UsuarioFormView.vue'
import TecnicosListView from '../views/TecnicosListView.vue'
import TecnicoFormView from '../views/TecnicoFormView.vue'
import CategoriasListView from '../views/CategoriasListView.vue'
import CategoriaFormView from '../views/CategoriaFormView.vue'

const routes = [
  { path: '/', redirect: '/cliente/tickets' },

  { path: '/cliente/tickets', name: 'cliente-tickets', component: TicketsListView, meta: { mode: 'cliente' } },
  { path: '/cliente/tickets/novo', name: 'cliente-ticket-novo', component: TicketFormView, meta: { mode: 'cliente' } },
  { path: '/cliente/tickets/:id/editar', name: 'cliente-ticket-editar', component: TicketFormView, meta: { mode: 'cliente' } },

  { path: '/admin/tickets', name: 'admin-tickets', component: TicketsListView, meta: { mode: 'admin' } },
  { path: '/admin/tickets/novo', name: 'admin-ticket-novo', component: TicketFormView, meta: { mode: 'admin' } },
  { path: '/admin/tickets/:id/editar', name: 'admin-ticket-editar', component: TicketFormView, meta: { mode: 'admin' } },

  { path: '/admin/usuarios', name: 'admin-usuarios', component: UsuariosListView },
  { path: '/admin/usuarios/novo', name: 'admin-usuario-novo', component: UsuarioFormView },
  { path: '/admin/usuarios/:id/editar', name: 'admin-usuario-editar', component: UsuarioFormView, props: true },

  { path: '/admin/tecnicos', name: 'admin-tecnicos', component: TecnicosListView },
  { path: '/admin/tecnicos/novo', name: 'admin-tecnico-novo', component: TecnicoFormView },
  { path: '/admin/tecnicos/:id/editar', name: 'admin-tecnico-editar', component: TecnicoFormView, props: true },

  { path: '/admin/categorias', name: 'admin-categorias', component: CategoriasListView },
  { path: '/admin/categorias/novo', name: 'admin-categoria-novo', component: CategoriaFormView },
  { path: '/admin/categorias/:id/editar', name: 'admin-categoria-editar', component: CategoriaFormView, props: true }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

<template>
  <section>
    <header class="list-header">
      <h2>{{ isCliente ? 'Meus Tickets' : 'Todos os Tickets' }}</h2>
      <router-link class="btn" :to="novoTicketRoute">+ Novo Ticket</router-link>
    </header>

    <div v-if="carregando">Carregando...</div>

    <div v-else>
      <table class="table" v-if="tickets.length">
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Status</th>
            <th>Prioridade</th>
            <th>Criador</th>
            <th>Técnico</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="t in tickets" :key="t.id">
            <td>{{ t.id }}</td>
            <td>{{ t.titulo }}</td>
            <td>{{ t.status }}</td>
            <td>{{ t.prioridade }}</td>
            <td>{{ t.criadorNome || '-' }}</td>
            <td>{{ t.tecnicoAtribuidoNome || '-' }}</td>
            <td>
              <router-link
                class="btn btn-small"
                :to="editarRoute(t.id)"
              >
                Editar
              </router-link>
              <button class="btn btn-small btn-danger" @click="confirmarDelete(t.id)">
                Excluir
              </button>
            </td>
          </tr>
        </tbody>
      </table>

      <p v-else>Nenhum ticket encontrado.</p>
    </div>
  </section>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { listarTickets, deletarTicket } from '../api/tickets'

const CLIENTE_ID = 1

const tickets = ref([])
const carregando = ref(false)

const route = useRoute()
const mode = computed(() => route.meta.mode || 'admin')
const isCliente = computed(() => mode.value === 'cliente')

const novoTicketRoute = computed(() =>
  isCliente.value ? '/cliente/tickets/novo' : '/admin/tickets/novo'
)

function editarRoute(id) {
  return isCliente.value
    ? `/cliente/tickets/${id}/editar`
    : `/admin/tickets/${id}/editar`
}

async function carregar() {
  try {
    carregando.value = true
    const params = { page: 0, size: 20 }
    if (isCliente.value) {
      params.criadorId = CLIENTE_ID
    }
    const { data } = await listarTickets(params)
    tickets.value = data.content || []
  } catch (e) {
    console.error(e)
    alert('Erro ao carregar tickets')
  } finally {
    carregando.value = false
  }
}

async function confirmarDelete(id) {
  if (!confirm('Tem certeza que deseja excluir este ticket?')) return
  try {
    await deletarTicket(id)
    await carregar()
  } catch (e) {
    console.error(e)
    alert('Erro ao excluir ticket')
  }
}

onMounted(carregar)
</script>

<style scoped>
.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.btn {
  background-color: #1e40af;
  color: #fff;
  border: none;
  padding: 0.4rem 0.8rem;
  border-radius: 4px;
  cursor: pointer;
  text-decoration: none;
  font-size: 0.9rem;
}

.btn:hover {
  opacity: 0.9;
}

.btn-small {
  padding: 0.2rem 0.5rem;
  margin-right: 0.3rem;
}

.btn-danger {
  background-color: #b91c1c;
}

.table {
  width: 100%;
  border-collapse: collapse;
}

.table th,
.table td {
  border: 1px solid #ddd;
  padding: 0.4rem 0.6rem;
  font-size: 0.9rem;
}

.table th {
  background-color: #f3f4f6;
  text-align: left;
}
</style>

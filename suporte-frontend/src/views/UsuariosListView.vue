<template>
  <section>
    <header class="list-header">
      <h2>Usuários</h2>
      <router-link class="btn" to="/admin/usuarios/novo">+ Novo Usuário</router-link>
    </header>

    <div v-if="carregando">Carregando...</div>

    <div v-else>
      <table class="table" v-if="usuarios.length">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>E-mail</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="u in usuarios" :key="u.id">
            <td>{{ u.id }}</td>
            <td>{{ u.nome }}</td>
            <td>{{ u.email }}</td>
            <td>
              <router-link
                class="btn btn-small"
                :to="`/admin/usuarios/${u.id}/editar`"
              >
                Editar
              </router-link>
              <button class="btn btn-small btn-danger" @click="confirmarDelete(u.id)">
                Excluir
              </button>
            </td>
          </tr>
        </tbody>
      </table>

      <p v-else>Nenhum usuário encontrado.</p>
    </div>
  </section>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { listarUsuarios, deletarUsuario } from '../api/usuarios'

const usuarios = ref([])
const carregando = ref(false)

async function carregar() {
  try {
    carregando.value = true
    const { data } = await listarUsuarios({ page: 0, size: 50 })
    usuarios.value = data.content || []
  } catch (e) {
    console.error(e)
    alert('Erro ao carregar usuários')
  } finally {
    carregando.value = false
  }
}

async function confirmarDelete(id) {
  if (!confirm('Tem certeza que deseja excluir este usuário?')) return
  try {
    await deletarUsuario(id)
    await carregar()
  } catch (e) {
    console.error(e)
    alert('Erro ao excluir usuário')
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

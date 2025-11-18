<template>
  <section>
    <h2>{{ isEdit ? 'Editar Usuário' : 'Novo Usuário' }}</h2>

    <div v-if="carregando">Carregando...</div>

    <UsuarioForm
      v-else
      v-model="form"
      :submit-label="isEdit ? 'Atualizar' : 'Criar'"
      @submit="salvar"
      @cancel="voltar"
    />
  </section>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import UsuarioForm from '../components/UsuarioForm.vue'
import { buscarUsuario, criarUsuario, atualizarUsuario } from '../api/usuarios'

const route = useRoute()
const router = useRouter()

const id = route.params.id
const isEdit = computed(() => !!id)

const carregando = ref(false)

const form = ref({
  nome: '',
  email: ''
})

async function carregarUsuario() {
  if (!isEdit.value) return
  try {
    carregando.value = true
    const { data } = await buscarUsuario(id)
    form.value = {
      nome: data.nome,
      email: data.email
    }
  } catch (e) {
    console.error(e)
    alert('Erro ao carregar usuário')
  } finally {
    carregando.value = false
  }
}

async function salvar() {
  try {
    if (isEdit.value) {
      await atualizarUsuario(id, form.value)
      alert('Usuário atualizado com sucesso!')
    } else {
      await criarUsuario(form.value)
      alert('Usuário criado com sucesso!')
    }
    voltar()
  } catch (e) {
    console.error(e)
    if (e.response && e.response.data) {
      alert('Erro: ' + JSON.stringify(e.response.data))
    } else {
      alert('Erro ao salvar usuário')
    }
  }
}

function voltar() {
  router.push('/admin/usuarios')
}

onMounted(carregarUsuario)
</script>

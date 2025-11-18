<template>
  <section>
    <h2>{{ isEdit ? 'Editar Técnico' : 'Novo Técnico' }}</h2>

    <div v-if="carregando">Carregando...</div>

    <TecnicoForm
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
import TecnicoForm from '../components/TecnicoForm.vue'
import { buscarTecnico, criarTecnico, atualizarTecnico } from '../api/tecnicos'

const route = useRoute()
const router = useRouter()

const id = route.params.id
const isEdit = computed(() => !!id)

const carregando = ref(false)

const form = ref({
  nome: '',
  email: '',
  especialidades: []
})

async function carregarTecnico() {
  if (!isEdit.value) return
  try {
    carregando.value = true
    const { data } = await buscarTecnico(id)
    form.value = {
      nome: data.nome,
      email: data.email,
      especialidades: data.especialidades || []
    }
  } catch (e) {
    console.error(e)
    alert('Erro ao carregar técnico')
  } finally {
    carregando.value = false
  }
}

async function salvar() {
  try {
    if (isEdit.value) {
      await atualizarTecnico(id, form.value)
      alert('Técnico atualizado com sucesso!')
    } else {
      await criarTecnico(form.value)
      alert('Técnico criado com sucesso!')
    }
    voltar()
  } catch (e) {
    console.error(e)
    if (e.response && e.response.data) {
      alert('Erro: ' + JSON.stringify(e.response.data))
    } else {
      alert('Erro ao salvar técnico')
    }
  }
}

function voltar() {
  router.push('/admin/tecnicos')
}

onMounted(carregarTecnico)
</script>

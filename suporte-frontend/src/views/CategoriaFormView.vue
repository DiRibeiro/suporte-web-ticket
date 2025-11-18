<template>
  <section>
    <h2>{{ isEdit ? 'Editar Categoria' : 'Nova Categoria' }}</h2>

    <div v-if="carregando">Carregando...</div>

    <CategoriaForm
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
import CategoriaForm from '../components/CategoriaForm.vue'
import { buscarCategoria, criarCategoria, atualizarCategoria } from '../api/categorias'

const route = useRoute()
const router = useRouter()

const id = route.params.id
const isEdit = computed(() => !!id)

const carregando = ref(false)

const form = ref({
  nome: ''
})

async function carregarCategoria() {
  if (!isEdit.value) return
  try {
    carregando.value = true
    const { data } = await buscarCategoria(id)
    form.value = { nome: data.nome }
  } catch (e) {
    console.error(e)
    alert('Erro ao carregar categoria')
  } finally {
    carregando.value = false
  }
}

async function salvar() {
  try {
    if (isEdit.value) {
      await atualizarCategoria(id, form.value)
      alert('Categoria atualizada com sucesso!')
    } else {
      await criarCategoria(form.value)
      alert('Categoria criada com sucesso!')
    }
    voltar()
  } catch (e) {
    console.error(e)
    if (e.response && e.response.data) {
      alert('Erro: ' + JSON.stringify(e.response.data))
    } else {
      alert('Erro ao salvar categoria')
    }
  }
}

function voltar() {
  router.push('/admin/categorias')
}

onMounted(carregarCategoria)
</script>

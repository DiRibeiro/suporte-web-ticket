<template>
  <section>
    <h2>{{ isEdit ? tituloEditar : tituloNovo }}</h2>

    <div v-if="carregando">Carregando...</div>

    <TicketForm
      v-else
      v-model="form"
      :submit-label="isEdit ? 'Atualizar' : 'Criar'"
      :is-cliente="isCliente"
      :categorias="categorias"
      :usuarios="usuarios"
      :tecnicos="tecnicos"
      :cliente-id="CLIENTE_ID"
      @submit="salvar"
      @cancel="voltar"
    />
  </section>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import TicketForm from '../components/TicketForm.vue'
import { buscarTicket, criarTicket, atualizarTicket } from '../api/tickets'
import { listarUsuarios } from '../api/usuarios'
import { listarCategorias } from '../api/categorias'
import { listarTecnicos } from '../api/tecnicos'

const CLIENTE_ID = 1

const route = useRoute()
const router = useRouter()

const id = route.params.id
const mode = computed(() => route.meta.mode || 'admin')
const isCliente = computed(() => mode.value === 'cliente')

const carregando = ref(false)

const form = ref({
  titulo: '',
  descricao: '',
  impacto: 1,
  urgencia: 1,
  prioridade: 1,
  status: 'NOVO',
  categoriaId: null,
  criadorId: null,
  tecnicoAtribuidoId: null
})

const categorias = ref([])
const usuarios = ref([])
const tecnicos = ref([])

const isEdit = computed(() => !!id)

const tituloNovo = computed(() =>
  isCliente.value ? 'Abrir novo ticket (Cliente)' : 'Criar novo ticket (Admin)'
)

const tituloEditar = computed(() =>
  isCliente.value ? 'Editar ticket (Cliente)' : 'Editar ticket (Admin)'
)

async function carregarLookups() {
  try {
    const [uRes, cRes, tRes] = await Promise.all([
      listarUsuarios({ page: 0, size: 50 }),
      listarCategorias({ page: 0, size: 50 }),
      listarTecnicos({ page: 0, size: 50 })
    ])

    usuarios.value = uRes.data.content || []
    categorias.value = cRes.data.content || []
    tecnicos.value = tRes.data.content || []

    if (isCliente.value && !isEdit.value) {
      form.value.criadorId = CLIENTE_ID
    }
  } catch (e) {
    console.error(e)
    alert('Erro ao carregar dados auxiliares (usuários, categorias, técnicos)')
  }
}

async function carregarTicket() {
  if (!isEdit.value) return
  try {
    carregando.value = true
    const { data } = await buscarTicket(id)
    form.value = {
      titulo: data.titulo,
      descricao: data.descricao,
      impacto: data.impacto,
      urgencia: data.urgencia,
      prioridade: data.prioridade,
      status: data.status,
      categoriaId: data.categoriaId,
      criadorId: data.criadorId,
      tecnicoAtribuidoId: data.tecnicoAtribuidoId
    }
  } catch (e) {
    console.error(e)
    alert('Erro ao carregar ticket')
  } finally {
    carregando.value = false
  }
}

async function salvar() {
  try {
    if (isCliente.value) {
      form.value.criadorId = CLIENTE_ID
    }

    if (isEdit.value) {
      await atualizarTicket(id, form.value)
      alert('Ticket atualizado com sucesso!')
    } else {
      await criarTicket(form.value)
      alert('Ticket criado com sucesso!')
    }

    voltar()
  } catch (e) {
    console.error(e)
    if (e.response && e.response.data) {
      alert('Erro: ' + JSON.stringify(e.response.data))
    } else {
      alert('Erro ao salvar ticket')
    }
  }
}

function voltar() {
  router.push(isCliente.value ? '/cliente/tickets' : '/admin/tickets')
}

onMounted(async () => {
  await carregarLookups()
  await carregarTicket()
})
</script>

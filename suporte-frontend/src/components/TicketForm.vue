<template>
  <form @submit.prevent="onSubmit" class="form">
    <div class="field">
      <label for="titulo" class="label-with-help">
        Título
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Título"
        >
          ❓
          <span class="tooltip">
            Título resumido do problema, por exemplo:
            <strong>“Servidor fora do ar”</strong>.
            Esse texto aparece na lista de tickets.
          </span>
        </span>
      </label>
      <input
        id="titulo"
        v-model="localValue.titulo"
        type="text"
        required
      />
    </div>

    <div class="field">
      <label for="descricao" class="label-with-help">
        Descrição
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Descrição"
        >
          ❓
          <span class="tooltip">
            Explique com detalhes o problema, incluindo quando começou,
            mensagens de erro, passos para reproduzir, etc.
          </span>
        </span>
      </label>
      <textarea
        id="descricao"
        v-model="localValue.descricao"
        required
      ></textarea>
    </div>

    <div class="field-inline">
      <div>
        <label for="impacto" class="label-with-help">
          Impacto
          <span
            class="help-icon"
            tabindex="0"
            aria-label="Ajuda sobre o campo Impacto"
          >
            ❓
            <span class="tooltip">
              Impacto no negócio (1 a 5). Quanto mais usuários/sistemas
              afetados, maior o impacto.
            </span>
          </span>
        </label>
        <input
          id="impacto"
          v-model.number="localValue.impacto"
          type="number"
          min="1"
          max="5"
          required
        />
      </div>

      <div>
        <label for="urgencia" class="label-with-help">
          Urgência
          <span
            class="help-icon"
            tabindex="0"
            aria-label="Ajuda sobre o campo Urgência"
          >
            ❓
            <span class="tooltip">
              Quão rápido isso precisa ser resolvido (1 a 5). 5 = precisa
              de atenção imediata.
            </span>
          </span>
        </label>
        <input
          id="urgencia"
          v-model.number="localValue.urgencia"
          type="number"
          min="1"
          max="5"
          required
        />
      </div>

      <div>
        <label for="prioridade" class="label-with-help">
          Prioridade
          <span
            class="help-icon"
            tabindex="0"
            aria-label="Ajuda sobre o campo Prioridade"
          >
            ❓
            <span class="tooltip">
              Prioridade de atendimento (1 a 5), geralmente calculada a partir
              de impacto x urgência.
            </span>
          </span>
        </label>
        <input
          id="prioridade"
          v-model.number="localValue.prioridade"
          type="number"
          min="1"
          max="5"
          required
        />
      </div>
    </div>

    <div class="field">
      <label for="status" class="label-with-help">
        Status
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Status"
        >
          ❓
          <span class="tooltip">
            <strong>NOVO</strong>: aberto<br />
            <strong>EM_ANDAMENTO</strong>: em tratamento<br />
            <strong>RESOLVIDO</strong>: solução aplicada<br />
            <strong>FECHADO</strong>: chamado encerrado
          </span>
        </span>
      </label>
      <select
        id="status"
        v-model="localValue.status"
        required
      >
        <option value="NOVO">NOVO</option>
        <option value="EM_ANDAMENTO">EM_ANDAMENTO</option>
        <option value="RESOLVIDO">RESOLVIDO</option>
        <option value="FECHADO">FECHADO</option>
      </select>
    </div>

    <div class="field">
      <label for="categoriaId" class="label-with-help">
        Categoria
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Categoria"
        >
          ❓
          <span class="tooltip">
            Área responsável pelo problema, por exemplo:
            Infraestrutura, Aplicações, Rede, etc.
          </span>
        </span>
      </label>
      <select
        id="categoriaId"
        v-model.number="localValue.categoriaId"
        required
      >
        <option disabled value="">Selecione...</option>
        <option v-for="c in categorias" :key="c.id" :value="c.id">
          {{ c.nome }}
        </option>
      </select>
    </div>

    <div class="field" v-if="!isCliente">
      <label for="criadorId" class="label-with-help">
        Criador (Usuário)
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Criador"
        >
          ❓
          <span class="tooltip">
            Usuário em nome de quem o ticket está sendo aberto. Normalmente
            o próprio solicitante.
          </span>
        </span>
      </label>
      <select
        id="criadorId"
        v-model.number="localValue.criadorId"
        required
      >
        <option disabled value="">Selecione...</option>
        <option v-for="u in usuarios" :key="u.id" :value="u.id">
          {{ u.nome }} ({{ u.email }})
        </option>
      </select>
    </div>

    <div class="field" v-else>
      <label class="label-with-help">
        Criador
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Criador"
        >
          ❓
          <span class="tooltip">
            Usuário atualmente logado na área do cliente. O ticket será
            registrado em seu nome.
          </span>
        </span>
      </label>
      <input
        type="text"
        :value="clienteNome"
        disabled
      />
    </div>

    <div class="field">
      <label for="tecnicoId" class="label-with-help">
        Técnico (opcional)
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Técnico"
        >
          ❓
          <span class="tooltip">
            Técnico responsável por atender o chamado. Se não for definido,
            o ticket fica na fila geral.
          </span>
        </span>
      </label>
      <select
        id="tecnicoId"
        v-model.number="localValue.tecnicoAtribuidoId"
      >
        <option :value="null">Não atribuído</option>
        <option v-for="t in tecnicos" :key="t.id" :value="t.id">
          {{ t.nome }} ({{ t.email }})
        </option>
      </select>
    </div>

    <div class="actions">
      <button type="submit" class="btn">
        {{ submitLabel }}
      </button>
      <button type="button" class="btn btn-secondary" @click="$emit('cancel')">
        Cancelar
      </button>
    </div>
  </form>
</template>

<script setup>
import { reactive, watch, computed } from 'vue'

const props = defineProps({
  modelValue: {
    type: Object,
    required: true
  },
  submitLabel: {
    type: String,
    default: 'Salvar'
  },
  isCliente: {
    type: Boolean,
    default: false
  },
  categorias: {
    type: Array,
    default: () => []
  },
  usuarios: {
    type: Array,
    default: () => []
  },
  tecnicos: {
    type: Array,
    default: () => []
  },
  clienteId: {
    type: Number,
    default: null
  }
})

const emit = defineEmits(['update:modelValue', 'submit', 'cancel'])

const localValue = reactive({ ...props.modelValue })

watch(
  () => props.modelValue,
  (val) => {
    Object.assign(localValue, val)
  },
  { deep: true }
)

watch(
  localValue,
  (val) => {
    emit('update:modelValue', { ...val })
  },
  { deep: true }
)

const clienteNome = computed(() => {
  if (!props.isCliente || !props.clienteId) return ''
  const found = props.usuarios.find(u => u.id === props.clienteId)
  return found ? `${found.nome} (${found.email})` : `Usuário ${props.clienteId}`
})

function onSubmit() {
  emit('submit')
}
</script>

<style scoped>
.form {
  max-width: 700px;
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
}

.field {
  display: flex;
  flex-direction: column;
  margin-bottom: 0.5rem;
}

.field-inline {
  display: flex;
  gap: 1rem;
  margin-bottom: 0.5rem;
}

.field-inline > div {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.label-with-help {
  display: inline-flex;
  align-items: center;
  gap: 0.3rem;
  font-size: 0.9rem;
  margin-bottom: 0.2rem;
}

input,
textarea,
select {
  padding: 0.3rem 0.4rem;
  border-radius: 4px;
  border: 1px solid #d1d5db;
  font-size: 0.9rem;
  background-color: #fff;
}

textarea {
  min-height: 80px;
}

.help-icon {
  position: relative;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 1.1rem;
  height: 1.1rem;
  border-radius: 999px;
  font-size: 0.7rem;
  background-color: #e5e7eb;
  color: #111827;
  cursor: pointer;
  flex-shrink: 0;
  outline: none;
}

.help-icon:hover {
  background-color: #d1d5db;
}

.help-icon:focus-visible {
  box-shadow: 0 0 0 2px #1d4ed8;
}

.tooltip {
  position: absolute;
  bottom: 130%;
  left: 50%;
  transform: translateX(-50%);
  background-color: #111827;
  color: #f9fafb;
  padding: 0.35rem 0.6rem;
  border-radius: 0.4rem;
  font-size: 0.75rem;
  line-height: 1.2;
  min-width: 200px;
  max-width: 260px;
  white-space: normal;
  opacity: 0;
  visibility: hidden;
  transition: opacity 0.15s ease, visibility 0.15s ease, transform 0.15s ease;
  z-index: 20;
}

.tooltip::after {
  content: '';
  position: absolute;
  top: 100%;
  left: 50%;
  transform: translateX(-50%);
  border-width: 6px;
  border-style: solid;
  border-color: #111827 transparent transparent transparent;
}

.help-icon:hover .tooltip,
.help-icon:focus .tooltip,
.help-icon:focus-within .tooltip {
  opacity: 1;
  visibility: visible;
  transform: translateX(-50%) translateY(-2px);
}

.actions {
  margin-top: 1rem;
  display: flex;
  gap: 0.5rem;
}

.btn {
  background-color: #1e40af;
  color: #fff;
  border: none;
  padding: 0.4rem 0.8rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
}

.btn-secondary {
  background-color: #6b7280;
}
</style>

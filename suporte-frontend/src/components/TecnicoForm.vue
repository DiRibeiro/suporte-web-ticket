<template>
  <form @submit.prevent="onSubmit" class="form">
    <div class="field">
      <label for="nome" class="label-with-help">
        Nome
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Nome do Técnico"
        >
          ❓
          <span class="tooltip">
            Nome completo do técnico, usado na atribuição de tickets.
          </span>
        </span>
      </label>
      <input
        id="nome"
        v-model="localValue.nome"
        type="text"
        required
      />
    </div>

    <div class="field">
      <label for="email" class="label-with-help">
        E-mail
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo E-mail do Técnico"
        >
          ❓
          <span class="tooltip">
            Endereço de e-mail do técnico, usado para contato e notificações.
          </span>
        </span>
      </label>
      <input
        id="email"
        v-model="localValue.email"
        type="email"
        required
      />
    </div>

    <div class="field">
      <label for="especialidades" class="label-with-help">
        Especialidades
        <span
          class="help-icon"
          tabindex="0"
          aria-label="Ajuda sobre o campo Especialidades"
        >
          ❓
          <span class="tooltip">
            Lista de especialidades separadas por vírgula. Ex.: Redes, Banco de Dados.
          </span>
        </span>
      </label>
      <input
        id="especialidades"
        v-model="especialidadesTexto"
        type="text"
        placeholder="Infraestrutura, Redes, Banco de Dados"
      />
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
import { reactive, ref, watch } from 'vue'

const props = defineProps({
  modelValue: {
    type: Object,
    required: true
  },
  submitLabel: {
    type: String,
    default: 'Salvar'
  }
})

const emit = defineEmits(['update:modelValue', 'submit', 'cancel'])

const localValue = reactive({ ...props.modelValue })
const especialidadesTexto = ref((localValue.especialidades || []).join(', '))

watch(
  () => props.modelValue,
  (val) => {
    Object.assign(localValue, val)
    especialidadesTexto.value = (val.especialidades || []).join(', ')
  },
  { deep: true }
)

watch(
  () => ({ ...localValue, especialidadesTexto: especialidadesTexto.value }),
  () => {
    const especialidades = especialidadesTexto.value
      .split(',')
      .map(e => e.trim())
      .filter(e => e.length > 0)

    emit('update:modelValue', {
      ...localValue,
      especialidades
    })
  },
  { deep: true }
)

function onSubmit() {
  emit('submit')
}
</script>

<style scoped>
.form {
  max-width: 500px;
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
}

.field {
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

input {
  padding: 0.3rem 0.4rem;
  border-radius: 4px;
  border: 1px solid #d1d5db;
  font-size: 0.9rem;
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

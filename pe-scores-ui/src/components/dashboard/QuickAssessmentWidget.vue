<template>
  <div class="widget-card">
    <div class="widget-header">
      <div>
        <h3>⚡ Бързо засичане на терен</h3>
        <p class="subtitle">Въвеждане на резултат с автоматично изчисление</p>
      </div>
    </div>

    <form @submit.prevent="submitScore" class="quick-form">
      <div class="form-row">
        <div class="field">
          <label>Клас</label>
          <select v-model="selectedClass">
            <option value="5A">5А клас</option>
            <option value="6B">6Б клас</option>
            <option value="7A">7А клас</option>
          </select>
        </div>

        <div class="field">
          <label>Дисциплина</label>
          <select v-model="selectedTask">
            <option value="50m">50м бягане (сек)</option>
            <option value="jump">Скок от място (см)</option>
            <option value="ball">Плътна топка (м)</option>
          </select>
        </div>
      </div>

      <div class="field">
        <label>Ученик</label>
        <select v-model="selectedStudent">
          <option value="1">Александър Георгиев</option>
          <option value="2">Виктория Петрова</option>
          <option value="3">Димитър Иванов</option>
        </select>
      </div>

      <div class="input-calc-group">
        <div class="field flex-1">
          <label>Постигнат резултат</label>
          <input type="number" step="0.01" v-model="rawResult" placeholder="напр. 7.45" required />
        </div>

        <div class="calculated-preview" :class="{ active: calculatedPercent !== null }">
          <span class="calc-label">Норматив</span>
          <span class="calc-val">{{ calculatedPercent ? calculatedPercent + '%' : '--' }}</span>
        </div>
      </div>

      <button type="submit" class="submit-btn">Запиши резултата</button>
    </form>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const selectedClass = ref('5A')
const selectedTask = ref('50m')
const selectedStudent = ref('1')
const rawResult = ref('')

const calculatedPercent = computed(() => {
  if (!rawResult.value) return null
  // Примерна бърза калкулация за изглед
  const val = parseFloat(rawResult.value)
  if (selectedTask.value === '50m') {
    return val <= 7.5 ? 100 : val <= 8.5 ? 85 : 65
  }
  return 80
})

const submitScore = () => {
  alert(`Записано! Резултат ${rawResult.value} за ученик ID:${selectedStudent.value}`)
  rawResult.value = ''
}
</script>

<style scoped>
.widget-card {
  background: var(--bg-card, #ffffff);
  border: 1px solid var(--border-color, #e2e8f0);
  border-radius: 16px;
  padding: 1.5rem;
}
.widget-header h3 { font-size: 1.1rem; font-weight: 700; margin: 0; }
.subtitle { font-size: 0.85rem; color: #64748b; margin: 0.2rem 0 1rem 0; }

.quick-form { display: flex; flex-direction: column; gap: 0.9rem; }
.form-row { display: flex; gap: 0.75rem; }
.field { display: flex; flex-direction: column; gap: 0.3rem; flex: 1; }
.field label { font-size: 0.8rem; font-weight: 600; color: #475569; }
select, input {
  padding: 0.6rem 0.8rem;
  border: 1px solid #cbd5e1;
  border-radius: 8px;
  font-size: 0.9rem;
  background: #f8fafc;
}

.input-calc-group { display: flex; gap: 0.75rem; align-items: flex-end; }
.flex-1 { flex: 1; }

.calculated-preview {
  background: #f1f5f9;
  border: 1px dashed #cbd5e1;
  border-radius: 8px;
  padding: 0.4rem 0.8rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 90px;
}
.calculated-preview.active {
  background: #ecfdf5;
  border-color: #10b981;
  color: #047857;
}
.calc-label { font-size: 0.68rem; text-transform: uppercase; font-weight: 700; }
.calc-val { font-size: 1.1rem; font-weight: 800; }

.submit-btn {
  background: #2563eb;
  color: white;
  border: none;
  padding: 0.75rem;
  border-radius: 8px;
  font-weight: 700;
  cursor: pointer;
  margin-top: 0.3rem;
  transition: background 0.2s;
}
.submit-btn:hover { background: #1d4ed8; }
</style>
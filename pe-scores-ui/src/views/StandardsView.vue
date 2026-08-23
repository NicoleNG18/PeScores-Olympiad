<template>
  <div class="page-layout">
    <AppNavbar :is-dark="isDark" @toggle-theme="toggleTheme" />

    <main class="standards-container">
      <header class="page-header">
        <h1>{{ $t('standards.title') }}</h1>
        <p class="subtitle">
          {{ $t('standards.subtitle') }}
        </p>
      </header>

      <!-- Индикатор за зареждане -->
      <div v-if="loading" class="state-container">
        <div class="spinner"></div>
        <p>{{ $t('standards.loadingDisciplines') }}</p>
      </div>

      <!-- Състояние на грешка -->
      <div v-else-if="errorMessage" class="state-container error">
        <p>⚠️ {{ errorMessage }}</p>
        <button @click="initView" class="retry-btn">{{ $t('standards.retry') }}</button>
      </div>

      <!-- Основна задвижвана от REST интерфейс форма -->
      <div v-else-if="disciplines.length > 0">
        <div class="filters-card">
          <div class="filter-group">
            <label for="discipline-select">{{ $t('standards.discipline') }}</label>
            <select
                id="discipline-select"
                v-model="selectedDiscipline"
                @change="fetchNormatives"
                class="filter-select"
            >
              <option v-for="disc in disciplines" :key="disc" :value="disc">
                {{ disc }}
              </option>
            </select>
          </div>

          <div class="filter-group">
            <label for="grade-select">{{ $t('standards.grade') }}</label>
            <select
                id="grade-select"
                v-model.number="selectedGrade"
                @change="fetchNormatives"
                class="filter-select"
            >
              <option v-for="grade in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]" :key="grade" :value="grade">
                {{ grade }} {{ $t('standards.gradeSuffix') }}
              </option>
            </select>
          </div>
        </div>

        <!-- Детайли и таблица за избрания норматив -->
        <article class="standard-card">
          <div class="card-header">
            <div class="card-title-group">
              <span class="card-icon">🏆</span>
              <div>
                <h2>{{ selectedDiscipline }}</h2>
                <span class="category-badge">{{ selectedGrade }} {{ $t('standards.gradeSuffix') }}</span>
              </div>
            </div>
          </div>

          <div v-if="loadingNormatives" class="state-container inline">
            <div class="spinner small"></div>
            <p>{{ $t('standards.loadingNormatives') }}</p>
          </div>

          <div v-else-if="normatives.length === 0" class="empty-state">
            {{ $t('standards.empty') }}
          </div>

          <div v-else class="table-wrapper">
            <table class="benchmark-table">
              <thead>
              <tr>
                <th>{{ $t('standards.gender') }}</th>
                <th>{{ $t('standards.points') }}</th>
                <th>{{ $t('standards.resultInterval') }}</th>
              </tr>
              </thead>
              <tbody>
              <!-- Момчета -->
              <tr v-for="item in boysNormatives" :key="'boy-' + item.id">
                <td v-if="item === boysNormatives[0]" :rowspan="boysNormatives.length" class="row-label male-label">
                  <span class="gender-icon">👦</span> {{ $t('standards.boys') }}
                </td>
                <td class="points-cell">{{ item.points }} {{ $t('standards.pts') }}</td>
                <td class="result-cell">{{ formatRange(item.minResult, item.maxResult) }}</td>
              </tr>

              <!-- Момичета -->
              <tr v-for="item in girlsNormatives" :key="'girl-' + item.id">
                <td v-if="item === girlsNormatives[0]" :rowspan="girlsNormatives.length" class="row-label female-label">
                  <span class="gender-icon">👧</span> {{ $t('standards.girls') }}
                </td>
                <td class="points-cell">{{ item.points }} {{ $t('standards.pts') }}</td>
                <td class="result-cell">{{ formatRange(item.minResult, item.maxResult) }}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </article>
      </div>
    </main>

    <AppFooter :is-dark="isDark" @toggle-theme="toggleTheme"/>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { normativeService, type INormative } from '../services/NormativeService'

import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const { t } = useI18n()

const isDark = ref<boolean>(false)
const toggleTheme = (): void => {
  isDark.value = !isDark.value
  document.documentElement.classList.toggle('dark-theme', isDark.value)
}

const disciplines = ref<string[]>([])
const normatives = ref<INormative[]>([])

const selectedDiscipline = ref<string>('')
const selectedGrade = ref<number>(5)

const loading = ref<boolean>(true)
const loadingNormatives = ref<boolean>(false)
const errorMessage = ref<string | null>(null)

const initView = async (): Promise<void> => {
  loading.value = true
  errorMessage.value = null

  try {
    disciplines.value = await normativeService.getDisciplines()
    if (disciplines.value.length > 0) {
      selectedDiscipline.value = disciplines.value[0]
      await fetchNormatives()
    }
  } catch (err: unknown) {
    errorMessage.value = t('standards.errorMessage')
  } finally {
    loading.value = false
  }
}

const fetchNormatives = async (): Promise<void> => {
  if (!selectedDiscipline.value) return

  loadingNormatives.value = true
  try {
    normatives.value = await normativeService.getNormatives(selectedGrade.value, selectedDiscipline.value)
  } catch (err: unknown) {
    console.error('Грешка при зареждане на нормативи:', err)
  } finally {
    loadingNormatives.value = false
  }
}

onMounted(() => {
  initView()
})

const boysNormatives = computed<INormative[]>(() => {
  return normatives.value
      .filter(n => n.gender === 'BOY')
      .sort((a, b) => b.points - a.points)
})

const girlsNormatives = computed<INormative[]>(() => {
  return normatives.value
      .filter(n => n.gender === 'GIRL')
      .sort((a, b) => b.points - a.points)
})

const formatRange = (min: number, max: number): string => {
  if (min === max) return `${min}`
  return `${min} - ${max}`
}
</script>

<style scoped>
.page-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: var(--bg-main, #f8fafc);
  color: var(--text-main);
  transition: background-color 0.3s ease, color 0.3s ease;
}

.standards-container {
  flex: 1;
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
  padding: 2rem 1.5rem;
}

.page-header {
  margin-bottom: 2rem;
  text-align: center;
}

.page-header h1 {
  font-size: 2.2rem;
  font-weight: 800;
  color: var(--text-main);
}

.subtitle {
  color: var(--text-muted, #64748b);
  font-size: 1.05rem;
  margin-top: 0.5rem;
}

.state-container {
  text-align: center;
  padding: 3rem 2rem;
  background: var(--bg-card, #ffffff);
  border-radius: 16px;
  border: 1px solid var(--border-color, #e2e8f0);
}

.state-container.inline {
  padding: 1.5rem;
  border: none;
  background: transparent;
}

.state-container.error {
  color: #ef4444;
}

.spinner {
  width: 36px;
  height: 36px;
  border: 4px solid rgba(37, 99, 235, 0.1);
  border-left-color: var(--primary-color, #2563eb);
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin: 0 auto 1rem;
}

.spinner.small {
  width: 24px;
  height: 24px;
  border-width: 3px;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.retry-btn {
  margin-top: 1rem;
  padding: 0.5rem 1rem;
  background: var(--primary-color, #2563eb);
  color: #ffffff;
  border: none;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
}

.empty-state {
  text-align: center;
  padding: 2rem;
  color: var(--text-muted, #64748b);
  font-style: italic;
}

.filters-card {
  display: flex;
  justify-content: center;
  gap: 2rem;
  background: var(--bg-card, #ffffff);
  border: 1px solid var(--border-color, #e2e8f0);
  border-radius: 12px;
  padding: 1.25rem;
  margin-bottom: 2rem;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
}

.filter-group {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  font-weight: 600;
  color: var(--text-main);
}

.filter-select {
  padding: 0.55rem 1rem;
  border-radius: 8px;
  border: 1px solid var(--border-color, #cbd5e1);
  background: var(--bg-main, #f8fafc);
  color: var(--text-main);
  font-weight: 600;
  cursor: pointer;
  outline: none;
}

.filter-select:focus {
  border-color: var(--primary-color, #2563eb);
}

.standard-card {
  background: var(--bg-card, #ffffff);
  border: 1px solid var(--border-color, #e2e8f0);
  border-radius: 16px;
  padding: 1.75rem;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.04);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.card-title-group {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.card-title-group h2 {
  color: var(--text-main);
}

.card-icon {
  font-size: 2rem;
  background: rgba(37, 99, 235, 0.08);
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 12px;
}

.category-badge {
  display: inline-block;
  font-size: 0.75rem;
  font-weight: 700;
  text-transform: uppercase;
  color: var(--primary-color, #2563eb);
  background: rgba(37, 99, 235, 0.1);
  padding: 0.2rem 0.5rem;
  border-radius: 6px;
  margin-top: 0.2rem;
}

/* Таблица */
.table-wrapper {
  overflow-x: auto;
}

.benchmark-table {
  width: 100%;
  border-collapse: collapse;
  text-align: center;
  font-size: 0.95rem;
}

.benchmark-table th,
.benchmark-table td {
  padding: 0.75rem 1rem;
  border: 1px solid var(--border-color, #e2e8f0);
  color: var(--text-main);
}

.benchmark-table th {
  background: var(--bg-main, #f8fafc);
  font-weight: 700;
  color: var(--text-main);
}

.row-label {
  font-weight: 700;
  text-align: center;
  vertical-align: middle;
  width: 160px;
  background: var(--bg-main, #f8fafc);
}

.male-label { color: #2563eb; }
.female-label { color: #ec4899; }
.gender-icon { margin-right: 0.3rem; }

.points-cell {
  font-weight: 700;
  color: var(--primary-color, #2563eb);
}

.result-cell {
  font-weight: 600;
}

@media (max-width: 640px) {
  .filters-card {
    flex-direction: column;
    gap: 1rem;
  }
}
</style>
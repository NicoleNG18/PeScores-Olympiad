<template>
  <div class="classes-page">
    <AppNavbar :is-dark="isDark" @toggle-theme="toggleTheme" />

    <main class="main-section">
      <div class="page-header">
        <div class="header-titles">
          <h2>{{ $t('classes.title') }}</h2>
          <p class="subtitle">{{ $t('classes.subtitle') }}</p>
        </div>
        <router-link to="/add-class" class="primary-btn">
          + {{ $t('classes.addClassBtn') }}
        </router-link>
      </div>

      <div class="filters-bar">
        <div class="search-box">
          <span class="search-icon">🔍</span>
          <input
              type="text"
              v-model="searchQuery"
              :placeholder="$t('classes.searchPlaceholder')"
          />
        </div>
      </div>

      <div v-if="filteredClasses.length === 0" class="empty-state">
        <span class="empty-icon">🎒</span>
        <h3>{{ $t('classes.noClassesTitle') }}</h3>
        <p>{{ $t('classes.noClassesDesc') }}</p>
        <router-link to="/add-class" class="secondary-btn">
          {{ $t('classes.addClassBtn') }}
        </router-link>
      </div>

      <div v-else class="classes-grid">
        <div v-for="schoolClass in filteredClasses" :key="schoolClass.id" class="class-card">
          <div class="card-top">
            <div class="class-badge">
              <span class="grade-num">{{ schoolClass.grade }}</span>
              <span class="section-letter">{{ schoolClass.section }}</span>
            </div>
            <div class="class-info">
              <h3>{{ schoolClass.grade }} {{ schoolClass.section }} клас</h3>
              <p class="teacher-name">👤 {{ schoolClass.teacherName }}</p>
            </div>
          </div>

          <div class="card-stats">
            <div class="stat-item">
              <span class="stat-value">{{ schoolClass.studentsCount }}</span>
              <span class="stat-label">{{ $t('classes.studentsCount') }}</span>
            </div>
            <div class="stat-item">
              <span class="stat-value highlight">{{ schoolClass.schoolName }}</span>
              <span class="stat-label">{{ $t('classes.schoolLabel') }}</span>
            </div>
          </div>

          <div class="card-actions">
            <router-link :to="`/classes/${schoolClass.id}`" class="action-btn view-btn">
              {{ $t('classes.viewBtn') }} →
            </router-link>
          </div>
        </div>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const { t } = useI18n()

const isDark = ref<boolean>(false)
const searchQuery = ref<string>('')

interface SchoolClass {
  id: string;
  grade: number;
  section: string;
  teacherName: string;
  studentsCount: number;
  schoolName: string;
}

const classesList = ref<SchoolClass[]>([
  { id: '1', grade: 8, section: 'А', teacherName: 'Иван Георгиев', studentsCount: 26, schoolName: 'ПГЕЕ "Ап. Попов"' },
  { id: '2', grade: 8, section: 'Б', teacherName: 'Мария Стоянова', studentsCount: 24, schoolName: 'ПГЕЕ "Ап. Попов"' },
  { id: '3', grade: 10, section: 'В', teacherName: 'Димитър Петров', studentsCount: 28, schoolName: 'ПГЕЕ "Ап. Попов"' }
])

const toggleTheme = (): void => {
  isDark.value = !isDark.value
  const theme = isDark.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-theme', theme)
  localStorage.setItem('theme', theme)
}

const filteredClasses = computed(() => {
  if (!searchQuery.value.trim()) return classesList.value
  const query = searchQuery.value.toLowerCase()
  return classesList.value.filter(c =>
      `${c.grade} ${c.section}`.toLowerCase().includes(query) ||
      c.teacherName.toLowerCase().includes(query) ||
      c.schoolName.toLowerCase().includes(query)
  )
})

onMounted(() => {
  const savedTheme = localStorage.getItem('theme') || 'light'
  isDark.value = savedTheme === 'dark'
  document.documentElement.setAttribute('data-theme', savedTheme)
})
</script>

<style scoped>
.classes-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #f1f5f9;
}

:global(body.dark) .classes-page,
:root[data-theme="dark"] .classes-page {
  background-color: var(--bg-main, #0b0f19);
}

.main-section {
  flex: 1;
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
  padding: 2.5rem 1.5rem;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
  flex-wrap: wrap;
  gap: 1rem;
}

.header-titles h2 {
  font-size: 1.8rem;
  font-weight: 800;
  color: #0f172a;
  margin-bottom: 0.3rem;
}

:global(body.dark) .header-titles h2,
:root[data-theme="dark"] .header-titles h2 {
  color: var(--text-main, #f1f5f9);
}

.subtitle {
  font-size: 0.92rem;
  color: #64748b;
}

:global(body.dark) .subtitle,
:root[data-theme="dark"] .subtitle {
  color: var(--text-muted, #94a3b8);
}

.primary-btn {
  background: linear-gradient(135deg, var(--primary-color, #2563eb) 0%, var(--primary-hover, #1d4ed8) 100%);
  color: #ffffff;
  padding: 0.75rem 1.25rem;
  border-radius: 10px;
  font-weight: 700;
  font-size: 0.92rem;
  text-decoration: none;
  transition: all 0.2s ease;
  box-shadow: 0 4px 12px rgba(37, 99, 235, 0.2);
}

.primary-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 16px rgba(37, 99, 235, 0.35);
}

.filters-bar {
  margin-bottom: 2rem;
}

.search-box {
  position: relative;
  max-width: 350px;
}

.search-icon {
  position: absolute;
  left: 1rem;
  top: 50%;
  transform: translateY(-50%);
  font-size: 0.9rem;
}

.search-box input {
  width: 100%;
  padding: 0.75rem 1rem 0.75rem 2.6rem;
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 10px;
  font-size: 0.9rem;
  color: #0f172a;
  outline: none;
  transition: all 0.2s ease;
}

:global(body.dark) .search-box input,
:root[data-theme="dark"] .search-box input {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
  color: var(--text-main, #f1f5f9);
}

.search-box input:focus {
  border-color: var(--primary-color, #2563eb);
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
}

/* Мрежа с кутийки */
.classes-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 1.5rem;
}

.class-card {
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 16px;
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

:global(body.dark) .class-card,
:root[data-theme="dark"] .class-card {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
  box-shadow: 0 10px 30px -10px rgba(0, 0, 0, 0.2);
}

.class-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 15px 35px -10px rgba(0, 0, 0, 0.1);
}

.card-top {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1.2rem;
}

.class-badge {
  width: 55px;
  height: 55px;
  background: linear-gradient(135deg, var(--primary-color, #2563eb) 0%, #1e40af 100%);
  color: #ffffff;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 800;
  font-size: 1.2rem;
  box-shadow: 0 4px 10px rgba(37, 99, 235, 0.3);
}

.class-info h3 {
  font-size: 1.15rem;
  font-weight: 700;
  color: #0f172a;
  margin-bottom: 0.2rem;
}

:global(body.dark) .class-info h3,
:root[data-theme="dark"] .class-info h3 {
  color: var(--text-main, #f1f5f9);
}

.teacher-name {
  font-size: 0.85rem;
  color: #64748b;
}

:global(body.dark) .teacher-name,
:root[data-theme="dark"] .teacher-name {
  color: var(--text-muted, #94a3b8);
}

.card-stats {
  display: flex;
  justify-content: space-between;
  background: #f8fafc;
  padding: 0.8rem 1rem;
  border-radius: 10px;
  margin-bottom: 1.2rem;
  border: 1px solid #e2e8f0;
}

:global(body.dark) .card-stats,
:root[data-theme="dark"] .card-stats {
  background: rgba(255, 255, 255, 0.02);
  border-color: var(--border-color, #334155);
}

.stat-item {
  display: flex;
  flex-direction: column;
}

.stat-value {
  font-size: 0.95rem;
  font-weight: 700;
  color: #0f172a;
}

:global(body.dark) .stat-value,
:root[data-theme="dark"] .stat-value {
  color: var(--text-main, #f1f5f9);
}

.stat-value.highlight {
  font-size: 0.85rem;
  color: var(--primary-color, #2563eb);
}

.stat-label {
  font-size: 0.72rem;
  color: #64748b;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}

.card-actions {
  display: flex;
  justify-content: flex-end;
}

.action-btn {
  display: inline-block;
  width: 100%;
  text-align: center;
  padding: 0.65rem;
  background: rgba(37, 99, 235, 0.1);
  color: var(--primary-color, #2563eb);
  border-radius: 8px;
  font-weight: 600;
  font-size: 0.9rem;
  text-decoration: none;
  transition: background-color 0.2s ease;
}

.action-btn:hover {
  background: rgba(37, 99, 235, 0.2);
}

.empty-state {
  text-align: center;
  padding: 4rem 1rem;
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 16px;
}

:global(body.dark) .empty-state,
:root[data-theme="dark"] .empty-state {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
}

.empty-icon {
  font-size: 3rem;
  display: block;
  margin-bottom: 1rem;
}

.empty-state h3 {
  font-size: 1.2rem;
  font-weight: 700;
  margin-bottom: 0.4rem;
  color: var(--text-main);
}

.empty-state p {
  font-size: 0.9rem;
  color: #64748b;
  margin-bottom: 1.5rem;
}

.secondary-btn {
  display: inline-block;
  padding: 0.6rem 1.2rem;
  background: var(--border-color);
  color: var(--text-main);
  border-radius: 8px;
  font-weight: 600;
  text-decoration: none;
}
</style>
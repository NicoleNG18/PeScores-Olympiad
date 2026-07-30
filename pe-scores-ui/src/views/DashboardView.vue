<template>
  <div class="dashboard-page">
    <AppNavbar>
      <template #links>
        <router-link to="/dashboard" class="nav-link-btn active">
          <span>{{ t('nav.dashboard') }}</span>
        </router-link>
        <router-link to="/classes" class="nav-link-btn">
          <span>{{ t('nav.classes') }}</span>
        </router-link>
        <router-link to="/tasks" class="nav-link-btn">
          <span>{{ t('nav.tasks') }}</span>
        </router-link>
        <button @click="handleLogout" class="logout-btn">
          {{ t('nav.logout') }}
        </button>
      </template>
    </AppNavbar>

    <main class="dashboard-main">
      <div class="dashboard-container">
        <!-- HEADER -->
        <div class="welcome-header">
          <h1>{{ t('dashboard.welcome') }}, <span class="highlight">г-н Иванов</span> 👋</h1>
          <p class="subtitle">{{ t('dashboard.subtitle') }}</p>
        </div>

        <!-- WIDGETS GRID -->
        <div class="widgets-grid">
          <RadarWidget />
          <QuickInputWidget />
          <LeaderboardWidget />
        </div>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { useTheme } from '../composables/useTheme'

import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'
import RadarWidget from '../components/dashboard/PhysicalRadarWidget.vue'
import QuickInputWidget from '../components/dashboard/QuickAssessmentWidget.vue'
import LeaderboardWidget from '../components/dashboard/LeaderboardWidget.vue'

const router = useRouter()
const { t } = useI18n()
const { initTheme } = useTheme()

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/auth')
}

onMounted(() => {
  initTheme()
})
</script>

<style scoped>
/* Page Layout */
.dashboard-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: var(--bg-main);
  color: var(--text-main);
  font-family: inherit;
}

.dashboard-main {
  flex: 1;
  padding: 2.5rem 1.5rem;
}

.dashboard-container {
  max-width: 1280px;
  margin: 0 auto;
}

/* Header Section */
.welcome-header {
  margin-bottom: 2.5rem;
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.welcome-header h1 {
  font-size: 2rem;
  font-weight: 800;
  color: var(--text-main);
  letter-spacing: -0.025em;
  margin: 0;
}

.highlight {
  color: var(--primary-color);
  background: linear-gradient(135deg, var(--primary-color), #6366f1);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.subtitle {
  font-size: 0.95rem;
  color: var(--text-muted);
  margin: 0;
  font-weight: 500;
}

.widgets-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1.75rem;
  align-items: start;
}

@media (max-width: 1024px) {
  .widgets-grid {
    grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  }
}

.widget-card {
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  border-radius: 20px;
  padding: 1.75rem;
  display: flex;
  flex-direction: column;
  box-shadow: 0 4px 20px -2px rgba(0, 0, 0, 0.05);
  transition: transform 0.25s ease, box-shadow 0.25s ease, border-color 0.25s ease;
  height: 100%;
  box-sizing: border-box;
}

.widget-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 12px 30px -4px rgba(0, 0, 0, 0.08);
  border-color: rgba(37, 99, 235, 0.3);
}

.card-header-flex {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 1.5rem;
  gap: 1rem;
}

.card-header-flex h3 {
  font-size: 1.2rem;
  font-weight: 800;
  color: var(--text-main);
  margin: 0 0 0.3rem 0;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.badge {
  background: rgba(37, 99, 235, 0.1);
  color: var(--primary-color);
  font-size: 0.75rem;
  font-weight: 700;
  padding: 0.35rem 0.8rem;
  border-radius: 999px;
  white-space: nowrap;
  letter-spacing: 0.02em;
}

.radar-container {
  width: 100%;
  max-width: 260px;
  margin: auto;
  padding: 0.5rem 0;
}

.radar-svg {
  width: 100%;
  height: auto;
  overflow: visible;
}

.grid-ring {
  fill: none;
  stroke: var(--border-color);
  stroke-dasharray: 4 4;
  stroke-width: 1;
}

.axis-line {
  stroke: var(--border-color);
  stroke-width: 1.2;
}

.data-polygon {
  fill: rgba(37, 99, 235, 0.25);
  stroke: var(--primary-color);
  stroke-width: 2.5;
  filter: drop-shadow(0 4px 6px rgba(37, 99, 235, 0.2));
}

.data-point {
  fill: var(--primary-color);
  transition: r 0.2s ease;
}

.data-point:hover {
  r: 6;
}

.axis-label {
  font-size: 10px;
  font-weight: 700;
  fill: var(--text-muted);
}

.widget-form {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 0.85rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.45rem;
  text-align: left;
}

label {
  font-size: 0.82rem;
  font-weight: 700;
  color: var(--text-main);
  letter-spacing: 0.01em;
}

select, input {
  width: 100%;
  padding: 0.75rem 0.9rem;
  border: 1px solid var(--border-color);
  border-radius: 12px;
  font-size: 0.9rem;
  font-weight: 500;
  background: var(--bg-main);
  color: var(--text-main);
  outline: none;
  box-sizing: border-box;
  transition: all 0.2s ease;
}

select:focus, input:focus {
  border-color: var(--primary-color);
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
  background: var(--bg-card);
}

.input-calc-group {
  display: flex;
  gap: 0.85rem;
  align-items: flex-end;
}

.flex-1 {
  flex: 1;
}

.calculated-preview {
  background: var(--bg-main);
  border: 1.5px dashed var(--border-color);
  border-radius: 12px;
  padding: 0.5rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-width: 90px;
  height: 44px;
  box-sizing: border-box;
  transition: all 0.25s ease;
}

.calculated-preview.active {
  background: rgba(16, 185, 129, 0.08);
  border-color: #10b981;
  color: #10b981;
  border-style: solid;
}

.calc-label {
  font-size: 0.62rem;
  text-transform: uppercase;
  font-weight: 800;
  letter-spacing: 0.05em;
  opacity: 0.8;
}

.calc-val {
  font-size: 1.15rem;
  font-weight: 800;
  line-height: 1.1;
}

.submit-btn {
  margin-top: 0.4rem;
  padding: 0.85rem;
  background: linear-gradient(135deg, var(--primary-color) 0%, #1d4ed8 100%);
  color: #ffffff;
  border: none;
  border-radius: 12px;
  font-weight: 700;
  font-size: 0.95rem;
  cursor: pointer;
  box-shadow: 0 4px 12px rgba(37, 99, 235, 0.25);
  transition: all 0.2s ease;
}

.submit-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(37, 99, 235, 0.35);
}

.submit-btn:active {
  transform: translateY(0);
}

.leaders-list {
  display: flex;
  flex-direction: column;
  gap: 0.85rem;
}

.leader-item {
  display: flex;
  align-items: center;
  gap: 0.9rem;
  padding: 0.8rem 1rem;
  background: var(--bg-main);
  border: 1px solid var(--border-color);
  border-radius: 14px;
  transition: transform 0.2s ease, border-color 0.2s ease;
}

.leader-item:hover {
  border-color: rgba(37, 99, 235, 0.3);
  transform: translateX(2px);
}

.rank {
  width: 32px;
  height: 32px;
  border-radius: 10px;
  background: var(--border-color);
  color: var(--text-main);
  font-weight: 800;
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.rank-1 {
  background: linear-gradient(135deg, #fef08a, #facc15);
  color: #854d0e;
  box-shadow: 0 2px 8px rgba(234, 179, 8, 0.3);
}

.rank-2 {
  background: linear-gradient(135deg, #f1f5f9, #cbd5e1);
  color: #334155;
}

.rank-3 {
  background: linear-gradient(135deg, #ffedd5, #fdba74);
  color: #9a3412;
}

.student-info {
  display: flex;
  flex-direction: column;
  gap: 0.15rem;
  flex: 1;
  overflow: hidden;
}

.name {
  font-size: 0.92rem;
  font-weight: 700;
  color: var(--text-main);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.meta {
  font-size: 0.78rem;
  color: var(--text-muted);
  font-weight: 500;
}

.score-badge {
  font-size: 0.85rem;
  font-weight: 800;
  color: #10b981;
  background: rgba(16, 185, 129, 0.1);
  padding: 0.4rem 0.75rem;
  border-radius: 10px;
  white-space: nowrap;
}

.logout-btn {
  background: transparent;
  border: 1px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
  padding: 0.55rem 1rem;
  border-radius: 10px;
  font-weight: 600;
  font-size: 0.88rem;
  cursor: pointer;
  transition: all 0.2s ease;
}

.logout-btn:hover {
  background: rgba(239, 68, 68, 0.1);
  border-color: #ef4444;
}

@media (max-width: 640px) {
  .dashboard-main {
    padding: 1.5rem 1rem;
  }

  .form-row {
    grid-template-columns: 1fr;
  }

  .welcome-header h1 {
    font-size: 1.5rem;
  }
}
</style>
<template>
  <div class="widget-card">
    <div class="widget-header">
      <div>
        <h3>🏆 Скаутинг & Топ Рекорди</h3>
        <p class="subtitle">Потенциал за училищните отбори</p>
      </div>
      <router-link to="/scouting" class="link-btn">Виж всички →</router-link>
    </div>

    <div class="leaders-list">
      <div v-for="(item, i) in leaders" :key="i" class="leader-item">
        <div class="rank" :class="'rank-' + (i + 1)">{{ i + 1 }}</div>
        <div class="student-info">
          <span class="name">{{ item.name }}</span>
          <span class="meta">{{ item.class }} • {{ item.task }}</span>
        </div>
        <div class="score-badge">
          {{ item.score }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const leaders = ref([
  { name: 'Виктор Илиев', class: '7А клас', task: '50м бягане', score: '6.70 сек' },
  { name: 'Мария Тодорова', class: '6Б клас', task: 'Скок от място', score: '215 см' },
  { name: 'Георги Вълчев', class: '7Б клас', task: 'Плътна топка', score: '11.4 м' }
])
</script>

<style scoped>
.widget-card {
  background: var(--bg-card, #ffffff);
  border: 1px solid var(--border-color, #e2e8f0);
  border-radius: 16px;
  padding: 1.5rem;
}
.widget-header { display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 1rem; }
.widget-header h3 { font-size: 1.1rem; font-weight: 700; margin: 0; }
.subtitle { font-size: 0.85rem; color: #64748b; margin: 0.2rem 0 0 0; }
.link-btn { font-size: 0.8rem; font-weight: 700; color: #2563eb; text-decoration: none; }

.leaders-list { display: flex; flex-direction: column; gap: 0.75rem; }
.leader-item {
  display: flex;
  align-items: center;
  gap: 0.8rem;
  padding: 0.6rem 0.8rem;
  background: #f8fafc;
  border-radius: 10px;
}
.rank {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background: #e2e8f0;
  color: #475569;
  font-weight: 800;
  font-size: 0.85rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
.rank-1 { background: #fef08a; color: #a16207; }
.rank-2 { background: #e2e8f0; color: #475569; }
.rank-3 { background: #ffedd5; color: #c2410c; }

.student-info { display: flex; flex-direction: column; flex: 1; }
.name { font-size: 0.9rem; font-weight: 700; color: #1e293b; }
.meta { font-size: 0.75rem; color: #64748b; }

.score-badge {
  font-size: 0.85rem;
  font-weight: 800;
  color: #059669;
  background: #ecfdf5;
  padding: 0.3rem 0.6rem;
  border-radius: 6px;
}
</style>
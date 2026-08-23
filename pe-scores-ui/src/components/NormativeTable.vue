<script setup lang="ts">
import type { INormative } from '@/services/NormativeService';

defineProps<{
  title: string;
  genderType: 'boys' | 'girls';
  items: INormative[];
}>();
</script>

<template>
  <div class="table-card" :class="genderType">
    <div class="card-header">
      <h3>{{ title }}</h3>
      <span class="badge">{{ items.length }} норматива</span>
    </div>

    <table class="standards-table">
      <thead>
      <tr>
        <th>Точки</th>
        <th>Мин. Резултат</th>
        <th>Макс. Резултат</th>
      </tr>
      </thead>
      <tbody>
      <tr v-if="items.length === 0">
        <td colspan="3" class="no-data">Няма данни за избраната категория</td>
      </tr>
      <tr v-for="item in items" :key="item.id">
        <td class="points-cell"><strong>{{ item.points }} б.</strong></td>
        <td>{{ item.minResult }}</td>
        <td>{{ item.maxResult }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.table-card {
  flex: 1;
  background: #ffffff;
  border-radius: 12px;
  padding: 1.5rem;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.06);
  border-top: 5px solid #cbd5e1;
}

.table-card.boys {
  border-top-color: #3b82f6;
}

.table-card.girls {
  border-top-color: #ec4899;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.card-header h3 {
  margin: 0;
  font-size: 1.3rem;
  color: #1e293b;
}

.badge {
  background: #f1f5f9;
  color: #64748b;
  padding: 0.25rem 0.6rem;
  border-radius: 20px;
  font-size: 0.8rem;
  font-weight: 600;
}

.standards-table {
  width: 100%;
  border-collapse: collapse;
}

.standards-table th, .standards-table td {
  padding: 0.85rem;
  text-align: center;
  border-bottom: 1px solid #f1f5f9;
}

.standards-table th {
  background-color: #f8fafc;
  color: #475569;
  font-weight: 600;
  font-size: 0.9rem;
}

.points-cell {
  color: #0f172a;
}

.no-data {
  color: #94a3b8;
  padding: 2rem;
  font-style: italic;
}
</style>
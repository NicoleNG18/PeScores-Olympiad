<template>
  <div class="widget-card">
    <div class="widget-header">
      <div>
        <h3>Профил на физическите качества</h3>
        <p class="subtitle">Средно равнище за училището</p>
      </div>
      <span class="badge">Випуск 2026</span>
    </div>

    <div class="radar-container">
      <svg viewBox="0 0 300 300" class="radar-svg">
        <!-- Background Grid Rings -->
        <circle cx="150" cy="150" r="100" class="grid-ring" />
        <circle cx="150" cy="150" r="75" class="grid-ring" />
        <circle cx="150" cy="150" r="50" class="grid-ring" />
        <circle cx="150" cy="150" r="25" class="grid-ring" />

        <!-- Axis Lines -->
        <line v-for="(p, i) in axisPoints" :key="i" x1="150" y1="150" :x2="p.x" :y2="p.y" class="axis-line" />

        <!-- Data Polygon -->
        <polygon :points="polygonPoints" class="data-polygon" />

        <!-- Data Points -->
        <circle v-for="(pt, i) in dataPoints" :key="'dp-'+i" :cx="pt.x" :cy="pt.y" r="4" class="data-point" />

        <!-- Labels -->
        <text v-for="(lbl, i) in labelPoints" :key="'lbl-'+i" :x="lbl.x" :y="lbl.y" class="axis-label" text-anchor="middle">
          {{ lbl.text }}
        </text>
      </svg>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'

// 5 основни показателя (стойности от 0 до 100%)
const metrics = [
  { name: 'Бързина (50м)', value: 82 },
  { name: 'Взривна сила', value: 75 },
  { name: 'Издръжливост', value: 60 },
  { name: 'Ловкост', value: 88 },
  { name: 'Сила (Топка)', value: 70 }
]

const center = 150
const radius = 100
const total = metrics.length

const getCoordinates = (index, valuePercent) => {
  const angle = (Math.PI * 2 / total) * index - Math.PI / 2
  const r = (valuePercent / 100) * radius
  return {
    x: center + r * Math.cos(angle),
    y: center + r * Math.sin(angle)
  }
}

const axisPoints = computed(() => metrics.map((_, i) => getCoordinates(i, 100)))
const dataPoints = computed(() => metrics.map((m, i) => getCoordinates(i, m.value)))
const polygonPoints = computed(() => dataPoints.value.map(p => `${p.x},${p.y}`).join(' '))

const labelPoints = computed(() => metrics.map((m, i) => {
  const angle = (Math.PI * 2 / total) * indexToAngle(i) - Math.PI / 2
  const r = radius + 22
  return {
    text: m.name,
    x: center + r * Math.cos(angle),
    y: center + r * Math.sin(angle) + 4
  }
}))

function indexToAngle(i) { return i }
</script>

<style scoped>
.widget-card {
  background: var(--bg-card, #ffffff);
  border: 1px solid var(--border-color, #e2e8f0);
  border-radius: 16px;
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
}
.widget-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 1rem;
}
.widget-header h3 { font-size: 1.1rem; font-weight: 700; margin: 0; }
.subtitle { font-size: 0.85rem; color: #64748b; margin: 0.2rem 0 0 0; }
.badge { background: #eff6ff; color: #2563eb; font-size: 0.75rem; font-weight: 700; padding: 0.25rem 0.6rem; border-radius: 20px; }

.radar-container { width: 100%; max-width: 320px; margin: 0 auto; }
.radar-svg { width: 100%; height: auto; overflow: visible; }
.grid-ring { fill: none; stroke: #cbd5e1; stroke-dasharray: 3 3; stroke-width: 1; }
.axis-line { stroke: #e2e8f0; stroke-width: 1; }
.data-polygon { fill: rgba(37, 99, 235, 0.25); stroke: #2563eb; stroke-width: 2.5; }
.data-point { fill: #2563eb; }
.axis-label { font-size: 10px; font-weight: 600; fill: #475569; }
</style>
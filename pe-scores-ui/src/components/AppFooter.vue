<template>
  <footer class="minimal-footer">
    <div class="footer-content">
      <p>&copy; {{ currentYear }} PE Scores. {{ $t('footer.rights') || 'Всички права запазени.' }}</p>
    </div>
  </footer>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const currentYear = computed(() => new Date().getFullYear())
</script>

<style scoped>
.minimal-footer {
  width: 100%;
  padding: 1.5rem 2rem;
  text-align: center;
  background: var(--nav-bg, var(--bg-card));
  backdrop-filter: blur(12px);
  -webkit-backdrop-filter: blur(12px);
  border-top: 1px solid var(--border-color);
  margin-top: auto;
  transition: background-color 0.3s ease, border-color 0.3s ease;
}

.footer-content p {
  margin: 0;
  font-size: 0.9rem;
  font-weight: 500;
  color: var(--text-main);
  opacity: 0.8;
}
</style>
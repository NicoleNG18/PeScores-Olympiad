<template>
  <footer class="minimal-footer">
    <div class="footer-content">
      <p>&copy; {{ currentYear }} PE Scores. {{ $t('footer.rights') }}</p>
    </div>
  </footer>
</template>

<script setup>
import { computed } from 'vue'

const currentYear = computed(() => new Date().getFullYear())
</script>

<style scoped>
.minimal-footer {
  padding: 2rem;
  text-align: center;
  border-top: 1px solid var(--border-color);
  margin-top: auto;
}

.footer-content p {
  margin: 0;
  font-size: 0.9rem;
  color: var(--text-muted);
}
</style>
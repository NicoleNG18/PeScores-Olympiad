<template>
  <footer class="minimal-footer">
    <div class="footer-content">
      <p>&copy; {{ currentYear }} PE Scores. {{ $t('footer.rights') || 'Всички права запазени.' }}</p>
      <div class="footer-contacts">
        <a href="mailto:support@pescores.bg" class="contact-link">support@pescores.bg</a>
        <span class="dot">•</span>
        <a href="tel:+359888123456" class="contact-link">+359 888 123 456</a>
      </div>
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

.footer-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
}

.footer-content p {
  margin: 0;
  font-size: 0.88rem;
  font-weight: 500;
  color: var(--text-main);
  opacity: 0.8;
}

.footer-contacts {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  font-size: 0.82rem;
}

.contact-link {
  color: var(--text-main);
  text-decoration: none;
  opacity: 0.7;
  transition: opacity 0.2s ease, color 0.2s ease;
}

.contact-link:hover {
  opacity: 1;
  color: var(--primary-color);
}

.dot {
  opacity: 0.4;
  color: var(--text-main);
}
</style>
<template>
  <nav class="navbar">
    <router-link to="/" class="brand-section">
      <img src="/images/pe-scores-final.png" alt="PE Scores Logo" class="logo-img" />
      <span class="brand-title">PE <span class="accent-text">Scores</span></span>
    </router-link>

    <div class="nav-right">
      <div class="nav-links">
        <slot name="links">
          <router-link to="/login" class="nav-link-btn">
            <span>{{ $t('nav.login') }}</span>
          </router-link>
        </slot>
      </div>

      <div class="controls-pill">
        <button @click="toggleTheme" class="control-btn" :title="isDark ? 'Light Mode' : 'Dark Mode'">
          <span class="icon">{{ isDark ? '☀️' : '🌙' }}</span>
        </button>
        <span class="divider"></span>
        <button @click="toggleLang" class="control-btn lang-btn" title="Смяна на езика / Change language">
          <!-- Когато е EN показва BG (за превключване към BG) и обратно -->
          <span>{{ locale === 'en' ? '🇧🇬 BG' : '🇬🇧 EN' }}</span>
        </button>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { useI18n } from 'vue-i18n'
import { useTheme } from '../composables/useTheme'

const { locale } = useI18n()
const { isDark, toggleTheme } = useTheme()

const toggleLang = () => {
  locale.value = locale.value === 'en' ? 'bg' : 'en'
  localStorage.setItem('lang', locale.value)
}
</script>

<style scoped>
.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0.8rem 2rem;
  background: var(--nav-bg, var(--bg-card));
  backdrop-filter: blur(12px);
  -webkit-backdrop-filter: blur(12px);
  border-bottom: 1px solid var(--border-color);
  position: sticky;
  top: 0;
  z-index: 100;
  transition: background-color 0.3s ease, border-color 0.3s ease;
}

.brand-section {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  text-decoration: none;
  transition: opacity 0.2s ease;
}

.brand-section:hover {
  opacity: 0.9;
}

.logo-img {
  height: 38px;
  width: auto;
  object-fit: contain;
}

.brand-title {
  font-size: 1.35rem;
  font-weight: 800;
  color: var(--text-main);
  letter-spacing: -0.02em;
}

.accent-text {
  color: var(--primary-color);
}

.nav-right {
  display: flex;
  align-items: center;
  gap: 1.25rem;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

/* Използваме :deep за линковете, които идват през слота */
:deep(.nav-link-btn),
.nav-link-btn {
  color: var(--text-main);
  text-decoration: none;
  font-weight: 600;
  font-size: 0.92rem;
  padding: 0.55rem 1rem;
  border-radius: 10px;
  transition: all 0.2s ease;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

:deep(.nav-link-btn:hover),
.nav-link-btn:hover {
  background-color: var(--border-color);
  color: var(--primary-color);
}

:deep(.nav-link-btn.active),
.nav-link-btn.active {
  background-color: rgba(37, 99, 235, 0.1);
  color: var(--primary-color);
}

.controls-pill {
  display: flex;
  align-items: center;
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  border-radius: 999px;
  padding: 0.2rem 0.4rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

.control-btn {
  background: transparent;
  border: none;
  color: var(--text-main);
  cursor: pointer;
  padding: 0.4rem 0.75rem;
  font-size: 0.88rem;
  font-weight: 700;
  border-radius: 999px;
  display: flex;
  align-items: center;
  gap: 0.3rem;
  transition: background-color 0.2s ease, transform 0.1s ease;
}

.control-btn:hover {
  background: var(--border-color);
}

.control-btn:active {
  transform: scale(0.95);
}

.lang-btn span {
  font-size: 0.85rem;
  letter-spacing: 0.03em;
}

.divider {
  width: 1px;
  height: 18px;
  background: var(--border-color);
  margin: 0 0.15rem;
}

@media (max-width: 640px) {
  .navbar {
    padding: 0.8rem 1rem;
  }

  .brand-title {
    font-size: 1.15rem;
  }

  .nav-right {
    gap: 0.5rem;
  }
}
</style>
<template>
  <nav class="navbar">
    <router-link to="/dashboard" class="brand-section">
      <img src="/images/pe-scores-final.png" alt="PE Scores Logo" class="logo-img" />
      <span class="brand-title">PE <span class="accent-text">Scores</span></span>
    </router-link>

    <div class="nav-right">
      <div class="nav-links">
        <router-link to="/dashboard" class="nav-link-btn" active-class="active">
          <span>{{ $t('nav.dashboard') || 'Табло' }}</span>
        </router-link>

        <router-link to="/standards" class="nav-link-btn" active-class="active">
          <span>{{ $t('nav.standards') || 'Стандарти' }}</span>
        </router-link>

        <slot name="extra-links"></slot>

        <button v-if="isAuthenticated" @click="handleLogout" class="nav-link-btn logout-btn">
          <span>{{ $t('nav.logout') || 'Изход' }}</span>
        </button>
        <router-link v-else to="/auth" class="nav-link-btn" active-class="active">
          <span>{{ $t('nav.login') || 'Вход' }}</span>
        </router-link>
      </div>

      <div class="controls-pill">
        <button @click="toggleTheme" class="control-btn" :title="isDark ? 'Light Mode' : 'Dark Mode'">
          <span class="icon">{{ isDark ? '☀️' : '🌙' }}</span>
        </button>
        <span class="divider"></span>
        <button @click="toggleLang" class="control-btn lang-btn" title="Смяна на езика / Change language">
          <span>{{ currentLang === 'en' ? '🇧🇬 BG' : '🇬🇧 EN' }}</span>
        </button>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { computed, ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { useTheme } from '../composables/useTheme'

const router = useRouter()
const { locale } = useI18n()
const { isDark, toggleTheme } = useTheme()

const isAuthenticated = ref<boolean>(false)

const checkAuth = (): void => {
  isAuthenticated.value = !!localStorage.getItem('token')
}

onMounted(() => {
  checkAuth()
})

const currentLang = computed<string>(() => String(locale.value))

const toggleLang = (): void => {
  const nextLang: string = currentLang.value === 'en' ? 'bg' : 'en'
  locale.value = nextLang
  localStorage.setItem('lang', nextLang)
}

const handleLogout = (): void => {
  localStorage.removeItem('token')
  isAuthenticated.value = false
  router.push('/auth')
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

.nav-link-btn {
  color: var(--text-main);
  text-decoration: none;
  font-weight: 600;
  font-size: 0.92rem;
  padding: 0.55rem 1rem;
  border-radius: 10px;
  background: transparent;
  border: none;
  cursor: pointer;
  transition: all 0.2s ease;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.nav-link-btn:hover {
  background-color: var(--border-color);
  color: var(--primary-color);
}

.nav-link-btn.router-link-active,
.nav-link-btn.active {
  background-color: rgba(37, 99, 235, 0.1);
  color: var(--primary-color);
}

.logout-btn {
  color: #ef4444;
}

.logout-btn:hover {
  background-color: rgba(239, 68, 68, 0.1);
  color: #dc2626;
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
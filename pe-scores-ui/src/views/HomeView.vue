<template>
  <div class="home-page">
    <AppNavbar :is-dark="isDark" @toggle-theme="toggleTheme" />

    <section class="hero-section">
      <div class="hero-content">
        <h1>
          {{ $t('hero.title1') }}
          <span class="gradient-text">{{ $t('hero.titleGradient') }}</span>
        </h1>
        <p class="subtitle">{{ $t('hero.subtitle') }}</p>

        <div class="hero-action">
          <router-link to="/login" class="cta-button">
            {{ $t('hero.cta') }}
            <span class="arrow">→</span>
          </router-link>
        </div>
      </div>
    </section>

    <section class="features-section">
      <div class="features-grid">
        <div class="feature-card">
          <div class="card-icon-wrapper">
            <span class="card-icon">📊</span>
          </div>
          <h3>{{ $t('features.f1Title') }}</h3>
          <p>{{ $t('features.f1Desc') }}</p>
        </div>

        <div class="feature-card">
          <div class="card-icon-wrapper">
            <span class="card-icon">📈</span>
          </div>
          <h3>{{ $t('features.f2Title') }}</h3>
          <p>{{ $t('features.f2Desc') }}</p>
        </div>

        <div class="feature-card">
          <div class="card-icon-wrapper">
            <span class="card-icon">📋</span>
          </div>
          <h3>{{ $t('features.f3Title') }}</h3>
          <p>{{ $t('features.f3Desc') }}</p>
        </div>
      </div>
    </section>

    <AppFooter />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const isDark = ref(false)

const toggleTheme = () => {
  isDark.value = !isDark.value
  const theme = isDark.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-theme', theme)
  localStorage.setItem('theme', theme)
}

onMounted(() => {
  const savedTheme = localStorage.getItem('theme') || 'light'
  isDark.value = savedTheme === 'dark'
  document.documentElement.setAttribute('data-theme', savedTheme)
})
</script>

<style scoped>
:global(:root) {
  --bg-main: #f8fafc;
  --bg-card: #ffffff;
  --text-main: #1e293b;
  --text-muted: #64748b;
  --border-color: rgba(226, 232, 240, 0.8);
  --nav-bg: rgba(255, 255, 255, 0.85);
}

:global([data-theme='dark']) {
  --bg-main: #0b0f19;
  --bg-card: #151c2c;
  --text-main: #f1f5f9;
  --text-muted: #94a3b8;
  --border-color: rgba(51, 65, 85, 0.6);
  --nav-bg: rgba(15, 23, 42, 0.85);
}

.home-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: var(--bg-main);
  color: var(--text-main);
  font-family: 'Inter', system-ui, -apple-system, sans-serif;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.hero-section {
  padding: 5rem 2rem 4rem;
  text-align: center;
  max-width: 950px;
  margin: 0 auto;
}

.hero-content h1 {
  font-size: 3.2rem;
  font-weight: 800;
  color: var(--text-main);
  margin-bottom: 1.2rem;
}

.gradient-text {
  background: linear-gradient(135deg, #2563eb 0%, #7c3aed 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.subtitle {
  font-size: 1.2rem;
  color: var(--text-muted);
  line-height: 1.6;
  margin-bottom: 2.5rem;
}

.cta-button {
  display: inline-flex;
  align-items: center;
  gap: 0.6rem;
  padding: 1.1rem 2.4rem;
  font-size: 1.1rem;
  font-weight: 700;
  color: #ffffff;
  background: linear-gradient(135deg, #2563eb 0%, #1d4ed8 100%);
  border-radius: 12px;
  text-decoration: none;
  box-shadow: 0 10px 25px -5px rgba(37, 99, 235, 0.4);
  transition: all 0.3s ease;
}

.cta-button:hover {
  transform: translateY(-3px);
}

.features-section {
  padding: 2rem 2rem 6rem;
  max-width: 1200px;
  margin: 0 auto;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
}

.feature-card {
  background: var(--bg-card);
  padding: 2.5rem 2rem;
  border-radius: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-6px);
  border-color: #2563eb;
}

.card-icon-wrapper {
  width: 56px;
  height: 56px;
  background: var(--border-color);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1.5rem;
}

.card-icon {
  font-size: 1.8rem;
}

.feature-card h3 {
  font-size: 1.3rem;
  color: var(--text-main);
  margin-bottom: 0.6rem;
}

.feature-card p {
  color: var(--text-muted);
  line-height: 1.6;
}

@media (max-width: 992px) {
  .features-grid {
    grid-template-columns: 1fr;
  }
}
</style>
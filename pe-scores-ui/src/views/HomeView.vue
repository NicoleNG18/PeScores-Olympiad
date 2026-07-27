<template>
  <div class="home-page">
    <!-- Навигационна лента -->
    <nav class="navbar">
      <div class="brand-section">
        <img src="/images/pe-scores-final.png" alt="PE Scores Logo" class="logo-img" />
        <span class="brand-title">PE <span class="accent-text">Scores</span></span>
      </div>

      <div class="nav-right">
        <!-- Леки бутони без твърд фон -->
        <div class="nav-links">
          <router-link to="/dashboard" class="nav-link-btn">
            <span>{{ t('nav.system') }}</span>
          </router-link>
          <router-link to="/profile" class="nav-link-btn">
            <span>{{ t('nav.profile') }}</span>
          </router-link>
          <button @click="logout" class="nav-link-btn danger">
            <span>{{ t('nav.logout') }}</span>
          </button>
        </div>

        <!-- Компактен панел за Тема и Език -->
        <div class="controls-pill">
          <button @click="toggleTheme" class="control-btn" :title="isDark ? 'Light Mode' : 'Dark Mode'">
            <span class="icon">{{ isDark ? '☀️' : '🌙' }}</span>
          </button>
          <span class="divider"></span>
          <button @click="toggleLang" class="control-btn lang-btn">
            <span>{{ currentLang === 'bg' ? '🇧🇬 BG' : '🇬🇧 EN' }}</span>
          </button>
        </div>
      </div>
    </nav>

    <!-- Основна Hero Секция -->
    <section class="hero-section">
      <div class="hero-content">
        <h1>
          {{ t('hero.title1') }}
          <span class="gradient-text">{{ t('hero.titleGradient') }}</span>
        </h1>
        <p class="subtitle">{{ t('hero.subtitle') }}</p>

        <div class="hero-action">
          <router-link to="/dashboard" class="cta-button">
            {{ t('hero.cta') }}
            <span class="arrow">→</span>
          </router-link>
        </div>
      </div>
    </section>

    <!-- Карти с функционалности (3 на един ред) -->
    <section class="features-section">
      <div class="features-grid">
        <div class="feature-card">
          <div class="card-icon-wrapper">
            <span class="card-icon">📊</span>
          </div>
          <h3>{{ t('features.f1Title') }}</h3>
          <p>{{ t('features.f1Desc') }}</p>
        </div>

        <div class="feature-card">
          <div class="card-icon-wrapper">
            <span class="card-icon">📈</span>
          </div>
          <h3>{{ t('features.f2Title') }}</h3>
          <p>{{ t('features.f2Desc') }}</p>
        </div>

        <div class="feature-card">
          <div class="card-icon-wrapper">
            <span class="card-icon">📋</span>
          </div>
          <h3>{{ t('features.f3Title') }}</h3>
          <p>{{ t('features.f3Desc') }}</p>
        </div>
      </div>
    </section>

    <!-- Футер с достатъчно отстояния -->
    <footer class="app-footer">
      <div class="footer-container">
        <div class="footer-brand">
          <div class="footer-logo">
            <img src="/images/pe-scores-final.png" alt="PE Scores Logo" class="logo-img-footer" />
            <span class="footer-title">PE Scores</span>
          </div>
          <p class="footer-desc">{{ t('footer.desc') }}</p>
        </div>

        <div class="footer-links-group">
          <h4>{{ t('footer.quickLinks') }}</h4>
          <ul class="footer-nav">
            <li><router-link to="/dashboard">{{ t('nav.dashboard') }}</router-link></li>
            <li><router-link to="/profile">{{ t('nav.profile') }}</router-link></li>
            <li><router-link to="/contact">{{ t('footer.contact') }}</router-link></li>
          </ul>
        </div>

        <div class="footer-info">
          <h4>{{ t('footer.school') }}</h4>
          <p>{{ t('footer.schoolName') }}</p>
          <p>{{ t('footer.schoolCity') }}</p>
          <p class="footer-copy">© {{ currentYear }} PE Scores. {{ t('footer.rights') }}</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const currentYear = ref(new Date().getFullYear())

// ТЕМА (Dark / Light)
const isDark = ref(false)

const toggleTheme = () => {
  isDark.value = !isDark.value
  const theme = isDark.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-theme', theme)
  localStorage.setItem('theme', theme)
}

// ПРЕВОДИ (i18n)
const currentLang = ref('bg')

const translations = {
  bg: {
    nav: { system: 'Към системата', profile: 'Моят Профил', logout: 'Изход', dashboard: 'Табло' },
    hero: {
      title1: 'Управление на спортни постижения с ',
      titleGradient: 'лекота',
      subtitle: 'Платформа от ново поколение за бързо въвеждане, анализ и проследяване на нормативи по физическо възпитание.',
      cta: 'Започни сега'
    },
    features: {
      f1Title: 'Въвеждане на резултати',
      f1Desc: 'Бързо и лесно нанасяне на точки и нормативи за всеки ученик с моментална валидация.',
      f2Title: 'Графики и анализи',
      f2Desc: 'Визуализация на индивидуалния и груповия прогрес през целия учебен период.',
      f3Title: 'Генератор на справки',
      f3Desc: 'Автоматично генериране на официални отчети за училищната администрация.'
    },
    footer: {
      desc: 'Система за дигитализация на часовете по физическо възпитание и спорт.',
      quickLinks: 'Бързи връзки',
      contact: 'Контакти',
      school: 'Училище',
      schoolName: 'ПМГ "Проф. Емануил Иванов"',
      schoolCity: 'гр. Кюстендил',
      rights: 'Всички права запазени.'
    }
  },
  en: {
    nav: { system: 'To System', profile: 'My Profile', logout: 'Logout', dashboard: 'Dashboard' },
    hero: {
      title1: 'Manage physical performance with ',
      titleGradient: 'ease',
      subtitle: 'Next-generation platform for rapid entry, analysis, and tracking of physical education standards.',
      cta: 'Get Started'
    },
    features: {
      f1Title: 'Score Entry',
      f1Desc: 'Fast and easy recording of points and standards for every student with instant validation.',
      f2Title: 'Charts & Analytics',
      f2Desc: 'Visualization of individual and group progress throughout the academic term.',
      f3Title: 'Report Generator',
      f3Desc: 'Automated generation of official reports for school administration.'
    },
    footer: {
      desc: 'A system for digitalizing physical education and sports classes.',
      quickLinks: 'Quick Links',
      contact: 'Contacts',
      school: 'School',
      schoolName: 'Prof. Emanuil Ivanov High School of Math & Science',
      schoolCity: 'Kyustendil, Bulgaria',
      rights: 'All rights reserved.'
    }
  }
}

const t = (path) => {
  const keys = path.split('.')
  let res = translations[currentLang.value]
  for (const k of keys) {
    if (res) res = res[k]
  }
  return res || path
}

const toggleLang = () => {
  currentLang.value = currentLang.value === 'bg' ? 'en' : 'bg'
  localStorage.setItem('lang', currentLang.value)
}

onMounted(() => {
  const savedTheme = localStorage.getItem('theme') || 'light'
  isDark.value = savedTheme === 'dark'
  document.documentElement.setAttribute('data-theme', savedTheme)

  const savedLang = localStorage.getItem('lang')
  if (savedLang) currentLang.value = savedLang
})

const logout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<style scoped>
:global(:root) {
  --bg-main: #f8fafc;
  --bg-card: #ffffff;
  --text-main: #1e293b;
  --text-muted: #64748b;
  --border-color: rgba(226, 232, 240, 0.8);
  --nav-bg: rgba(255, 255, 255, 0.85);
  --footer-bg: #0f172a;
  --footer-text: #94a3b8;
}

:global([data-theme='dark']) {
  --bg-main: #0b0f19;
  --bg-card: #151c2c;
  --text-main: #f1f5f9;
  --text-muted: #94a3b8;
  --border-color: rgba(51, 65, 85, 0.6);
  --nav-bg: rgba(15, 23, 42, 0.85);
  --footer-bg: #070a12;
  --footer-text: #64748b;
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

/* Навигация */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 3rem;
  background: var(--nav-bg);
  backdrop-filter: blur(12px);
  border-bottom: 1px solid var(--border-color);
  position: sticky;
  top: 0;
  z-index: 100;
}

.brand-section {
  display: flex;
  align-items: center;
  gap: 0.8rem;
}

.logo-img {
  height: 42px;
  width: auto;
}

.brand-title {
  font-size: 1.5rem;
  font-weight: 800;
  color: var(--text-main);
}

.accent-text {
  color: #2563eb;
}

.nav-right {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

/* БЕЗ ФОН - ИЗЧИСТЕНИ ЛИНК БУТОНИ (GHOST BUTTONS) */
.nav-link-btn {
  background: transparent;
  border: none;
  padding: 0.6rem 1rem;
  border-radius: 8px;
  font-weight: 600;
  font-size: 0.95rem;
  color: var(--text-main);
  text-decoration: none;
  cursor: pointer;
  transition: all 0.2s ease;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.nav-link-btn:hover {
  background: rgba(37, 99, 235, 0.08);
  color: #2563eb;
}

.nav-link-btn.danger {
  color: var(--text-muted);
}

.nav-link-btn.danger:hover {
  background: rgba(239, 68, 68, 0.08);
  color: #ef4444;
}

/* КОМПАКТЕН ПАНЕЛ ЗА ТЕМА И ЕЗИК */
.controls-pill {
  display: flex;
  align-items: center;
  background: var(--bg-card);
  padding: 0.25rem 0.5rem;
  border-radius: 30px;
  border: 1px solid var(--border-color);
}

.control-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  color: var(--text-main);
  font-size: 0.88rem;
  font-weight: 700;
  padding: 0.35rem 0.6rem;
  border-radius: 20px;
  display: flex;
  align-items: center;
  gap: 0.3rem;
  transition: all 0.2s ease;
}

.control-btn:hover {
  background: rgba(37, 99, 235, 0.1);
  color: #2563eb;
}

.divider {
  width: 1px;
  height: 16px;
  background-color: var(--border-color);
  margin: 0 0.2rem;
}

/* Hero Секция */
.hero-section {
  padding: 5rem 2rem 4rem;
  text-align: center;
  max-width: 950px;
  margin: 0 auto;
}

.badge {
  display: inline-block;
  padding: 0.4rem 1rem;
  background: #dbeafe;
  color: #1e40af;
  border-radius: 20px;
  font-size: 0.8rem;
  font-weight: 700;
  margin-bottom: 1.5rem;
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

/* КАРТИЧКИ НА ЕДИН РЕД (3-Column Layout) */
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

/* ФУТЕР - РАЗДАЛЕЧЕНИ ЛИНКОВЕ И КОЛОНИ */
.app-footer {
  margin-top: auto;
  background: var(--footer-bg);
  color: var(--footer-text);
  padding: 5rem 2rem 3rem;
  border-top: 1px solid var(--border-color);
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 2fr 1.2fr 1.2fr;
  gap: 5rem;
}

.footer-logo {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  margin-bottom: 1rem;
}

.logo-img-footer {
  height: 36px;
  width: auto;
}

.footer-title {
  font-size: 1.3rem;
  font-weight: 700;
  color: #ffffff;
}

.footer-links-group h4,
.footer-info h4 {
  color: #ffffff;
  font-size: 1.1rem;
  font-weight: 700;
  margin-bottom: 1.5rem;
  letter-spacing: 0.3px;
}

.footer-nav {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
}

.footer-nav a {
  color: var(--footer-text);
  text-decoration: none;
  font-size: 0.95rem;
  transition: all 0.2s ease;
  display: inline-block;
}

.footer-nav a:hover {
  color: #38bdf8;
  transform: translateX(4px);
}

.footer-info p {
  margin-bottom: 0.6rem;
  line-height: 1.5;
}

.footer-copy {
  margin-top: 1.5rem;
  font-size: 0.85rem;
  color: #64748b;
}

/* Мобилна адаптация */
@media (max-width: 992px) {
  .features-grid {
    grid-template-columns: 1fr;
  }
  .navbar {
    flex-direction: column;
    gap: 1rem;
  }
  .footer-container {
    grid-template-columns: 1fr;
    gap: 2rem;
  }
}
</style>
<template>
  <div class="auth-page">
    <AppNavbar :is-dark="isDark" @toggle-theme="toggleTheme" />

    <main class="auth-main">
      <div class="auth-card">
        <div class="card-header">
          <h2>{{ isActivation ? $t('auth.activateTitle') : $t('auth.loginTitle') }}</h2>
          <p class="subtitle">
            {{ isActivation ? $t('auth.activateSubtitle') : $t('auth.loginSubtitle') }}
          </p>
        </div>

        <div v-if="errorMessage" class="error-alert">
          <span>⚠️ {{ errorMessage }}</span>
        </div>

        <form v-if="!isActivation" @submit.prevent="handleLogin" class="auth-form">
          <div class="form-group">
            <label for="email">{{ $t('auth.email') }}</label>
            <input
                type="email"
                id="email"
                v-model="loginForm.email"
                :placeholder="$t('auth.emailPlaceholder')"
                required
            />
          </div>

          <div class="form-group">
            <label for="password">{{ $t('auth.password') }}</label>
            <input
                type="password"
                id="password"
                v-model="loginForm.password"
                :placeholder="$t('auth.passwordPlaceholder')"
                required
            />
          </div>

          <button type="submit" class="submit-btn">
            {{ $t('auth.loginBtn') }} →
          </button>

          <div class="form-footer">
            <span>{{ $t('auth.noAccount') }}</span>
            <button type="button" class="btn-link" @click="toggleMode(true)">
              {{ $t('auth.activateLink') }}
            </button>
          </div>
        </form>

        <form v-else @submit.prevent="handleActivation" class="auth-form">
          <div class="form-group">
            <label for="code">{{ $t('auth.schoolCode') }}</label>
            <input
                type="text"
                id="code"
                v-model="activateForm.code"
                placeholder="напр. PMG-KNS-2026"
                required
            />
          </div>

          <div class="form-group">
            <label for="fullName">{{ $t('auth.fullName') }}</label>
            <input
                type="text"
                id="fullName"
                v-model="activateForm.fullName"
                placeholder="Иван Иванов"
                required
            />
          </div>

          <div class="form-group">
            <label for="regEmail">{{ $t('auth.email') }}</label>
            <input
                type="email"
                id="regEmail"
                v-model="activateForm.email"
                :placeholder="$t('auth.emailPlaceholder')"
                required
            />
          </div>

          <div class="form-group">
            <label for="regPassword">{{ $t('auth.password') }}</label>
            <input
                type="password"
                id="regPassword"
                v-model="activateForm.password"
                :placeholder="$t('auth.passwordPlaceholder')"
                required
            />
          </div>

          <button type="submit" class="submit-btn">
            {{ $t('auth.activateBtn') }} →
          </button>

          <div class="form-footer">
            <span>{{ $t('auth.hasAccount') }}</span>
            <button type="button" class="btn-link" @click="toggleMode(false)">
              {{ $t('auth.loginLink') }}
            </button>
          </div>
        </form>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const { t } = useI18n()

const isDark = ref<boolean>(false)
const isActivation = ref<boolean>(false)
const errorMessage = ref<string>('')

const loginForm = ref({
  email: '',
  password: ''
})

const activateForm = ref({
  code: '',
  fullName: '',
  email: '',
  password: ''
})

const toggleTheme = (): void => {
  isDark.value = !isDark.value
  const theme = isDark.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-theme', theme)
  localStorage.setItem('theme', theme)
}

const toggleMode = (activation: boolean): void => {
  isActivation.value = activation
  errorMessage.value = ''
}

const handleLogin = (): void => {
  errorMessage.value = ''
  if (loginForm.value.password.length < 4) {
    errorMessage.value = t('auth.badCredentials')
    return
  }
  localStorage.setItem('token', 'mock-jwt-token')
  router.push('/dashboard')
}

const handleActivation = (): void => {
  errorMessage.value = ''
  localStorage.setItem('token', 'mock-jwt-token')
  router.push('/dashboard')
}

onMounted(() => {
  const savedTheme = localStorage.getItem('theme') || 'light'
  isDark.value = savedTheme === 'dark'
  document.documentElement.setAttribute('data-theme', savedTheme)
})
</script>

<style scoped>
.auth-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #f1f5f9;
}

:global(body.dark) .auth-page,
:root[data-theme="dark"] .auth-page {
  background-color: var(--bg-main, #0b0f19);
}

.auth-main {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 3rem 1.5rem;
}

.auth-card {
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 20px;
  padding: 2.5rem;
  width: 100%;
  max-width: 420px;
  box-shadow: 0 20px 35px -10px rgba(0, 0, 0, 0.1), 0 4px 6px -4px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

:global(body.dark) .auth-card,
:root[data-theme="dark"] .auth-card {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
  box-shadow: 0 20px 40px -15px rgba(0, 0, 0, 0.3);
}

.card-header {
  text-align: center;
  margin-bottom: 1.8rem;
}

.card-header h2 {
  font-size: 1.6rem;
  font-weight: 800;
  color: #0f172a;
  margin-bottom: 0.4rem;
}

:global(body.dark) .card-header h2,
:root[data-theme="dark"] .card-header h2 {
  color: var(--text-main, #f1f5f9);
}

.subtitle {
  font-size: 0.9rem;
  color: #64748b;
}

:global(body.dark) .subtitle,
:root[data-theme="dark"] .subtitle {
  color: var(--text-muted, #94a3b8);
}

.error-alert {
  background: rgba(239, 68, 68, 0.1);
  border: 1px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
  padding: 0.75rem 1rem;
  border-radius: 10px;
  font-size: 0.88rem;
  font-weight: 600;
  margin-bottom: 1.2rem;
  text-align: center;
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
  text-align: left;
}

label {
  font-size: 0.88rem;
  font-weight: 600;
  color: #0f172a;
}

:global(body.dark) label,
:root[data-theme="dark"] label {
  color: var(--text-main, #f1f5f9);
}

input {
  padding: 0.8rem 1rem;
  border: 1px solid #cbd5e1;
  border-radius: 10px;
  font-size: 0.95rem;
  background: #f8fafc;
  color: #0f172a;
  outline: none;
  transition: border-color 0.2s ease, box-shadow 0.2s ease, background-color 0.2s ease;
}

:global(body.dark) input,
:root[data-theme="dark"] input {
  background: var(--bg-main, #0b0f19);
  border-color: var(--border-color, #334155);
  color: var(--text-main, #f1f5f9);
}

input:focus {
  border-color: var(--primary-color, #2563eb);
  background: #ffffff;
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
}

:global(body.dark) input:focus,
:root[data-theme="dark"] input:focus {
  background: var(--bg-card, #151c2c);
}

.submit-btn {
  margin-top: 0.4rem;
  padding: 0.9rem;
  background: linear-gradient(135deg, var(--primary-color, #2563eb) 0%, var(--primary-hover, #1d4ed8) 100%);
  color: #ffffff;
  border: none;
  border-radius: 10px;
  font-weight: 700;
  font-size: 1rem;
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.submit-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px -4px rgba(37, 99, 235, 0.4);
}

.form-footer {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.3rem;
  margin-top: 1rem;
  font-size: 0.88rem;
  color: #64748b;
}

:global(body.dark) .form-footer,
:root[data-theme="dark"] .form-footer {
  color: var(--text-muted, #94a3b8);
}

.btn-link {
  background: none;
  border: none;
  color: var(--primary-color, #2563eb);
  font-weight: 700;
  cursor: pointer;
  padding: 0;
  font-size: 0.88rem;
}

.btn-link:hover {
  text-decoration: underline;
}
</style>
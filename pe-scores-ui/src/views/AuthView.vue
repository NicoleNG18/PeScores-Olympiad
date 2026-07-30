<template>
  <div class="auth-page">
    <AppNavbar>
      <template #links>
        <router-link to="/" class="nav-link-btn">
          <span>{{ $t('nav.home') }}</span>
        </router-link>
      </template>
    </AppNavbar>

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

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { useTheme } from '../composables/useTheme'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const router = useRouter()
const { t } = useI18n()
const { initTheme } = useTheme()

const isActivation = ref(false)
const errorMessage = ref('')

const loginForm = ref({ email: '', password: '' })
const activateForm = ref({ code: '', fullName: '', email: '', password: '' })

const toggleMode = (activation) => {
  isActivation.value = activation
  errorMessage.value = ''
}

const handleLogin = () => {
  errorMessage.value = ''
  if (loginForm.value.password.length < 4) {
    errorMessage.value = t('auth.badCredentials')
    return
  }
  localStorage.setItem('token', 'mock-jwt-token')
  router.push('/dashboard')
}

const handleActivation = () => {
  errorMessage.value = ''
  localStorage.setItem('token', 'mock-jwt-token')
  router.push('/dashboard')
}

onMounted(() => {
  initTheme()
})
</script>

<style scoped>
.auth-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.auth-main {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 3rem 1.5rem;
}

.auth-card {
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  border-radius: 20px;
  padding: 2.5rem;
  width: 100%;
  max-width: 420px;
  box-shadow: 0 20px 40px -15px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.card-header {
  text-align: center;
  margin-bottom: 1.8rem;
}

.card-header h2 {
  font-size: 1.6rem;
  font-weight: 800;
  color: var(--text-main);
  margin-bottom: 0.4rem;
}

.subtitle {
  font-size: 0.9rem;
  color: var(--text-muted);
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
  color: var(--text-main);
}

input {
  padding: 0.8rem 1rem;
  border: 1px solid var(--border-color);
  border-radius: 10px;
  font-size: 0.95rem;
  background: var(--bg-main);
  color: var(--text-main);
  outline: none;
  transition: border-color 0.2s ease, box-shadow 0.2s ease;
}

input:focus {
  border-color: var(--primary-color);
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
}

.submit-btn {
  margin-top: 0.4rem;
  padding: 0.9rem;
  background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-hover) 100%);
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
  color: var(--text-muted);
}

.btn-link {
  background: none;
  border: none;
  color: var(--primary-color);
  font-weight: 700;
  cursor: pointer;
  padding: 0;
  font-size: 0.88rem;
}

.btn-link:hover {
  text-decoration: underline;
}
</style>
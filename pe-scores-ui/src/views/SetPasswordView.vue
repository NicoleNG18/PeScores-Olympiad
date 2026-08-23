<template>
  <div class="set-password-page">
    <div class="password-card">
      <div class="brand-header">
        <div class="logo-icon">🔐</div>
        <h2>{{ $t('setPassword.title') }}</h2>
        <p>{{ $t('setPassword.subtitle') }}</p>
      </div>

      <form @submit.prevent="handleSetPassword" class="password-form">
        <div class="form-group">
          <label>{{ $t('setPassword.newPasswordLabel') }}</label>
          <input
              type="password"
              v-model="form.password"
              placeholder="••••••••"
              required
          />
        </div>

        <div class="form-group">
          <label>{{ $t('setPassword.confirmPasswordLabel') }}</label>
          <input
              type="password"
              v-model="form.confirmPassword"
              placeholder="••••••••"
              required
          />
        </div>

        <div v-if="errorMessage" class="error-msg">
          ⚠️ {{ errorMessage }}
        </div>

        <button type="submit" class="submit-btn" :disabled="isLoading">
          {{ isLoading ? $t('setPassword.activating') : $t('setPassword.submitBtn') }}
        </button>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'

const route = useRoute()
const router = useRouter()
const { t } = useI18n()

const isLoading = ref(false)
const errorMessage = ref('')
const token = ref<string | null>(null)

const form = reactive({
  password: '',
  confirmPassword: ''
})

onMounted(() => {
  token.value = route.query.token as string
  if (!token.value) {
    errorMessage.value = t('setPassword.errorMissingToken')
  }
})

const handleSetPassword = async () => {
  errorMessage.value = ''

  if (!token.value) {
    errorMessage.value = t('setPassword.errorMissingToken')
    return
  }

  if (form.password !== form.confirmPassword) {
    errorMessage.value = t('setPassword.errorMismatch')
    return
  }

  if (form.password.length < 6) {
    errorMessage.value = t('setPassword.errorLength')
    return
  }

  isLoading.value = true

  try {
    await new Promise(resolve => setTimeout(resolve, 800))
    localStorage.setItem('token', 'mock-activated-token')
    router.push('/dashboard')
  } catch (err) {
    errorMessage.value = t('setPassword.errorGeneral')
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
.set-password-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f1f5f9;
  padding: 1.5rem;
}

:global(body.dark) .set-password-page,
:root[data-theme="dark"] .set-password-page {
  background-color: var(--bg-main, #0b0f19);
}

.password-card {
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 20px;
  padding: 2.5rem;
  width: 100%;
  max-width: 420px;
  box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.05);
}

:global(body.dark) .password-card,
:root[data-theme="dark"] .password-card {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
}

.brand-header {
  text-align: center;
  margin-bottom: 2rem;
}

.logo-icon {
  font-size: 2.5rem;
  margin-bottom: 0.5rem;
}

.brand-header h2 {
  font-size: 1.4rem;
  font-weight: 800;
  color: #0f172a;
  margin-bottom: 0.3rem;
}

:global(body.dark) .brand-header h2,
:root[data-theme="dark"] .brand-header h2 {
  color: var(--text-main, #f1f5f9);
}

.brand-header p {
  font-size: 0.85rem;
  color: #64748b;
}

.password-form {
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.35rem;
}

.form-group label {
  font-size: 0.83rem;
  font-weight: 700;
  color: #475569;
}

:global(body.dark) .form-group label,
:root[data-theme="dark"] .form-group label {
  color: #94a3b8;
}

.form-group input {
  padding: 0.75rem 1rem;
  border: 1px solid #cbd5e1;
  border-radius: 10px;
  background: #f8fafc;
  font-size: 0.95rem;
  color: var(--text-main);
  outline: none;
}

:global(body.dark) .form-group input,
:root[data-theme="dark"] .form-group input {
  background: var(--bg-main, #0b0f19);
  border-color: var(--border-color, #334155);
}

.form-group input:focus {
  border-color: var(--primary-color, #2563eb);
  background: #ffffff;
}

.error-msg {
  background: rgba(239, 68, 68, 0.1);
  color: #ef4444;
  padding: 0.75rem;
  border-radius: 8px;
  font-size: 0.85rem;
  font-weight: 600;
  text-align: center;
}

.submit-btn {
  background: var(--primary-color, #2563eb);
  color: #ffffff;
  border: none;
  padding: 0.85rem;
  border-radius: 10px;
  font-weight: 700;
  font-size: 1rem;
  cursor: pointer;
  transition: all 0.2s ease;
  box-shadow: 0 4px 12px rgba(37, 99, 235, 0.3);
}

.submit-btn:hover {
  filter: brightness(1.1);
  transform: translateY(-1px);
}
</style>
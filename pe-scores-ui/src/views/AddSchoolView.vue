<template>
  <div class="add-school-page">
    <AppNavbar :is-dark="isDark" @toggle-theme="toggleTheme" />

    <main class="main-section">
      <div class="auth-card">
        <div class="card-header">
          <h2>{{ $t('addSchool.title') }}</h2>
          <p class="subtitle">{{ $t('addSchool.subtitle') }}</p>
        </div>

        <div v-if="errorMessage" class="error-alert">
          <span>⚠️ {{ errorMessage }}</span>
        </div>

        <div v-if="successMessage" class="success-alert">
          <span>✅ {{ successMessage }}</span>
        </div>

        <form @submit.prevent="handleSubmit" class="auth-form">
          <div class="form-group">
            <label for="schoolName">{{ $t('addSchool.name') }}</label>
            <input
                type="text"
                id="schoolName"
                v-model="schoolForm.schoolName"
                :placeholder="$t('addSchool.namePlaceholder')"
                required
            />
            <span v-if="errors.schoolName" class="field-error">{{ errors.schoolName }}</span>
          </div>

          <div class="form-group">
            <label for="city">{{ $t('addSchool.city') }}</label>
            <input
                type="text"
                id="city"
                v-model="schoolForm.city"
                :placeholder="$t('addSchool.cityPlaceholder')"
                required
            />
            <span v-if="errors.city" class="field-error">{{ errors.city }}</span>
          </div>

          <div class="form-group">
            <label for="website">{{ $t('addSchool.website') }}</label>
            <input
                type="url"
                id="website"
                v-model="schoolForm.website"
                placeholder="https://www.example-school.com"
            />
            <span class="field-hint">{{ $t('addSchool.websiteHint') }}</span>
          </div>

          <div class="form-group">
            <label for="directorName">{{ $t('addSchool.director') }}</label>
            <input
                type="text"
                id="directorName"
                v-model="schoolForm.directorName"
                :placeholder="$t('addSchool.directorPlaceholder')"
                required
            />
            <span v-if="errors.directorName" class="field-error">{{ errors.directorName }}</span>
          </div>

          <div class="form-group">
            <label for="position">{{ $t('addSchool.position') }}</label>
            <input
                type="text"
                id="position"
                v-model="schoolForm.position"
                :placeholder="$t('addSchool.positionPlaceholder')"
                required
            />
          </div>

          <div class="form-group">
            <label for="email">{{ $t('addSchool.email') }}</label>
            <input
                type="email"
                id="email"
                v-model="schoolForm.email"
                :placeholder="$t('addSchool.emailPlaceholder')"
                required
            />
            <span v-if="errors.email" class="field-error">{{ errors.email }}</span>
          </div>

          <div class="form-group">
            <label for="phone">{{ $t('addSchool.phone') }}</label>
            <input
                type="tel"
                id="phone"
                v-model="schoolForm.phone"
                :placeholder="$t('addSchool.phonePlaceholder')"
                required
            />
            <span v-if="errors.phone" class="field-error">{{ errors.phone }}</span>
          </div>

          <button type="submit" class="submit-btn">
            {{ $t('addSchool.submit') }} →
          </button>
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

const schoolForm = ref({
  schoolName: '',
  city: '',
  website: '',
  directorName: '',
  position: '',
  email: '',
  phone: ''
})

const errors = ref({
  schoolName: '',
  city: '',
  directorName: '',
  email: '',
  phone: ''
})

const errorMessage = ref<string>('')
const successMessage = ref<string>('')

const toggleTheme = (): void => {
  isDark.value = !isDark.value
  const theme = isDark.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-theme', theme)
  localStorage.setItem('theme', theme)
}

const handleSubmit = (): void => {
  errorMessage.value = ''
  successMessage.value = ''
  errors.value = { schoolName: '', city: '', directorName: '', email: '', phone: '' }

  if (!schoolForm.value.schoolName.trim()) {
    errors.value.schoolName = t('addSchool.errName')
    return
  }

  if (!schoolForm.value.city.trim()) {
    errors.value.city = t('addSchool.errCity')
    return
  }

  if (!schoolForm.value.directorName.trim()) {
    errors.value.directorName = t('addSchool.errDirector')
    return
  }

  if (!schoolForm.value.email.trim() || !schoolForm.value.email.includes('@')) {
    errors.value.email = t('addSchool.errEmail')
    return
  }

  if (!schoolForm.value.phone.trim()) {
    errors.value.phone = t('addSchool.errPhone')
    return
  }

  successMessage.value = t('addSchool.success')

  setTimeout(() => {
    router.push('/')
  }, 3000)
}

onMounted(() => {
  const savedTheme = localStorage.getItem('theme') || 'light'
  isDark.value = savedTheme === 'dark'
  document.documentElement.setAttribute('data-theme', savedTheme)
})
</script>

<style scoped>
.add-school-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #f1f5f9;
}

:global(body.dark) .add-school-page,
:root[data-theme="dark"] .add-school-page {
  background-color: var(--bg-main, #0b0f19);
}

.main-section {
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
  max-width: 500px;
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
  font-size: 1.5rem;
  font-weight: 800;
  color: #0f172a;
  margin-bottom: 0.4rem;
}

:global(body.dark) .card-header h2,
:root[data-theme="dark"] .card-header h2 {
  color: var(--text-main, #f1f5f9);
}

.subtitle {
  font-size: 0.88rem;
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

.success-alert {
  background: rgba(34, 197, 94, 0.1);
  border: 1px solid rgba(34, 197, 94, 0.3);
  color: #22c55e;
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
  gap: 1.1rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.35rem;
  text-align: left;
}

label {
  font-size: 0.85rem;
  font-weight: 600;
  color: #0f172a;
}

:global(body.dark) label,
:root[data-theme="dark"] label {
  color: var(--text-main, #f1f5f9);
}

input {
  padding: 0.75rem 1rem;
  border: 1px solid #cbd5e1;
  border-radius: 10px;
  font-size: 0.92rem;
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

.field-error {
  font-size: 0.76rem;
  color: #ef4444;
  margin-top: 0.1rem;
}

.field-hint {
  font-size: 0.75rem;
  color: #64748b;
}

:global(body.dark) .field-hint,
:root[data-theme="dark"] .field-hint {
  color: var(--text-muted, #94a3b8);
}

.submit-btn {
  margin-top: 0.6rem;
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
</style>
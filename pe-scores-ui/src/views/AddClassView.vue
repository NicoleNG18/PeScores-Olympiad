<template>
  <div class="add-class-page">
    <AppNavbar :is-dark="isDark" @toggle-theme="toggleTheme" />

    <main class="main-section">
      <div class="auth-card wide-card">
        <div class="card-header">
          <h2>{{ $t('addClass.title') }}</h2>
          <p class="subtitle">{{ $t('addClass.subtitle') }}</p>
        </div>

        <div v-if="errorMessage" class="error-alert">
          <span>⚠️ {{ errorMessage }}</span>
        </div>

        <div v-if="successMessage" class="success-alert">
          <span>✅ {{ successMessage }}</span>
        </div>

        <form @submit.prevent="handleSubmit" class="auth-form">
          <div class="form-row">
            <div class="form-group">
              <label for="grade">{{ $t('addClass.gradeLabel') }}</label>
              <select id="grade" v-model="classForm.grade" required>
                <option value="" disabled>{{ $t('addClass.selectGrade') }}</option>
                <option v-for="g in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]" :key="g" :value="g">
                  {{ g }} {{ $t('addClass.gradeSuffix') }}
                </option>
              </select>
            </div>

            <div class="form-group">
              <label for="section">{{ $t('addClass.sectionLabel') }}</label>
              <input
                  type="text"
                  id="section"
                  v-model="classForm.section"
                  placeholder="напр. А, Б, В"
                  maxlength="3"
                  required
              />
            </div>
          </div>

          <div class="form-group">
            <label for="teacher">{{ $t('addClass.teacherLabel') }}</label>
            <select id="teacher" v-model="classForm.teacherId" required>
              <option value="" disabled>{{ $t('addClass.selectTeacher') }}</option>
              <option v-for="teacher in teachersList" :key="teacher.id" :value="teacher.id">
                {{ teacher.fullName }}
              </option>
            </select>
          </div>

          <div class="csv-section">
            <div class="csv-info">
              <h4>{{ $t('addClass.csvInstructionTitle') }}</h4>
              <p>{{ $t('addClass.csvInstructionDesc') }}</p>
              <code>Number,First Name,Second Name,Last Name<br>1,Иван,Георгиев,Иванов<br>2,Петър,Димитров,Петров</code>
            </div>

            <div class="form-group">
              <label for="csvFile">{{ $t('addClass.csvFileLabel') }}</label>
              <input
                  type="file"
                  id="csvFile"
                  accept=".csv"
                  @change="handleFileUpload"
                  required
              />
            </div>
          </div>

          <div v-if="parsedStudents.length > 0" class="preview-container">
            <h4>{{ $t('addClass.previewTitle') }} ({{ parsedStudents.length }})</h4>
            <div class="table-wrapper">
              <table class="preview-table">
                <thead>
                <tr>
                  <th>№</th>
                  <th>{{ $t('addClass.studentName') }}</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(student, index) in parsedStudents" :key="index">
                  <td>{{ student.number }}</td>
                  <td>{{ student.fullName }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>

          <button type="submit" class="submit-btn" :disabled="parsedStudents.length === 0">
            {{ $t('addClass.submitBtn') }} →
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
const errorMessage = ref<string>('')
const successMessage = ref<string>('')

const classForm = ref({
  grade: '',
  section: '',
  teacherId: ''
})

const teachersList = ref([
  { id: '1', fullName: 'Иван Георгиев (Учител по ФВС)' },
  { id: '2', fullName: 'Мария Стоянова (Учител по ФВС)' }
])

interface StudentRow {
  number: string;
  fullName: string;
}
const parsedStudents = ref<StudentRow[]>([])

const toggleTheme = (): void => {
  isDark.value = !isDark.value
  const theme = isDark.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-theme', theme)
  localStorage.setItem('theme', theme)
}

const handleFileUpload = (event: Event): void => {
  const target = event.target as HTMLInputElement
  if (!target.files || target.files.length === 0) return

  const file = target.files[0]
  const reader = new FileReader()

  reader.onload = (e: ProgressEvent<FileReader>) => {
    const text = e.target?.result as string
    if (!text) return

    const lines = text.split('\n')
    const students: StudentRow[] = []

    for (let i = 1; i < lines.length; i++) {
      const line = lines[i].trim()
      if (!line) continue

      const cols = line.split(',')
      if (cols.length >= 4) {
        const number = cols[0].trim()
        const firstName = cols[1].trim()
        const secondName = cols[2].trim()
        const lastName = cols[3].trim()

        const fullName = [firstName, secondName, lastName].filter(Boolean).join(' ')

        students.push({
          number,
          fullName
        })
      }
    }

    parsedStudents.value = students
    if (students.length === 0) {
      errorMessage.value = t('addClass.errEmptyCsv') || 'Грешка в CSV файла или празни редове.'
    } else {
      errorMessage.value = ''
    }
  }

  reader.readAsText(file)
}

const handleSubmit = (): void => {
  errorMessage.value = ''
  successMessage.value = ''

  if (!classForm.value.grade || !classForm.value.section || !classForm.value.teacherId) {
    errorMessage.value = t('addClass.errFields') || 'Моля, попълнете всички задължителни полета.'
    return
  }

  if (parsedStudents.value.length === 0) {
    errorMessage.value = t('addClass.errNoStudents') || 'Моля, качете валиден CSV файл с ученици.'
    return
  }

  successMessage.value = t('addClass.success') || 'Класен комплект и ученици бяха създадени успешно!'

  setTimeout(() => {
    router.push('/dashboard')
  }, 2500)
}

onMounted(() => {
  const savedTheme = localStorage.getItem('theme') || 'light'
  isDark.value = savedTheme === 'dark'
  document.documentElement.setAttribute('data-theme', savedTheme)
})
</script>

<style scoped>
.add-class-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #f1f5f9;
}

:global(body.dark) .add-class-page,
:root[data-theme="dark"] .add-class-page {
  background-color: var(--bg-main, #0b0f19);
}

.main-section {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 3rem 1.5rem;
}

.auth-card.wide-card {
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 20px;
  padding: 2.5rem;
  width: 100%;
  max-width: 650px;
  box-shadow: 0 20px 35px -10px rgba(0, 0, 0, 0.1), 0 4px 6px -4px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

:global(body.dark) .auth-card.wide-card,
:root[data-theme="dark"] .auth-card.wide-card {
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

.error-alert, .success-alert {
  padding: 0.75rem 1rem;
  border-radius: 10px;
  font-size: 0.88rem;
  font-weight: 600;
  margin-bottom: 1.2rem;
  text-align: center;
}

.error-alert {
  background: rgba(239, 68, 68, 0.1);
  border: 1px solid rgba(239, 68, 68, 0.3);
  color: #ef4444;
}

.success-alert {
  background: rgba(34, 197, 94, 0.1);
  border: 1px solid rgba(34, 197, 94, 0.3);
  color: #22c55e;
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
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

input, select {
  padding: 0.75rem 1rem;
  border: 1px solid #cbd5e1;
  border-radius: 10px;
  font-size: 0.92rem;
  background: #f8fafc;
  color: #0f172a;
  outline: none;
  transition: all 0.2s ease;
}

:global(body.dark) input,
:global(body.dark) select,
:root[data-theme="dark"] input,
:root[data-theme="dark"] select {
  background: var(--bg-main, #0b0f19);
  border-color: var(--border-color, #334155);
  color: var(--text-main, #f1f5f9);
}

input:focus, select:focus {
  border-color: var(--primary-color, #2563eb);
  background: #ffffff;
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
}

:global(body.dark) input:focus,
:global(body.dark) select:focus,
:root[data-theme="dark"] input:focus,
:root[data-theme="dark"] select:focus {
  background: var(--bg-card, #151c2c);
}

.csv-section {
  background: rgba(37, 99, 235, 0.03);
  border: 1px dashed var(--border-color, #cbd5e1);
  padding: 1.2rem;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.csv-info h4 {
  font-size: 0.9rem;
  font-weight: 700;
  color: var(--text-main, #0f172a);
  margin-bottom: 0.2rem;
}

.csv-info p {
  font-size: 0.82rem;
  color: #64748b;
  margin-bottom: 0.5rem;
}

.csv-info code {
  display: block;
  background: rgba(0, 0, 0, 0.05);
  padding: 0.5rem;
  border-radius: 6px;
  font-family: monospace;
  font-size: 0.78rem;
  overflow-x: auto;
}

:global(body.dark) .csv-info code,
:root[data-theme="dark"] .csv-info code {
  background: rgba(255, 255, 255, 0.05);
}

.preview-container {
  margin-top: 0.5rem;
}

.preview-container h4 {
  font-size: 0.9rem;
  font-weight: 700;
  margin-bottom: 0.5rem;
  color: var(--text-main, #0f172a);
}

.table-wrapper {
  max-height: 180px;
  overflow-y: auto;
  border: 1px solid #cbd5e1;
  border-radius: 8px;
}

:global(body.dark) .table-wrapper,
:root[data-theme="dark"] .table-wrapper {
  border-color: var(--border-color, #334155);
}

.preview-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.85rem;
}

.preview-table th, .preview-table td {
  padding: 0.5rem 0.75rem;
  text-align: left;
  border-bottom: 1px solid #cbd5e1;
}

:global(body.dark) .preview-table th,
:global(body.dark) .preview-table td,
:root[data-theme="dark"] .preview-table th,
:root[data-theme="dark"] .preview-table td {
  border-bottom-color: var(--border-color, #334155);
}

.preview-table th {
  background: rgba(0, 0, 0, 0.02);
  font-weight: 600;
}

:global(body.dark) .preview-table th,
:root[data-theme="dark"] .preview-table th {
  background: rgba(255, 255, 255, 0.02);
}

.submit-btn {
  margin-top: 0.5rem;
  padding: 0.9rem;
  background: linear-gradient(135deg, var(--primary-color, #2563eb) 0%, var(--primary-hover, #1d4ed8) 100%);
  color: #ffffff;
  border: none;
  border-radius: 10px;
  font-weight: 700;
  font-size: 1rem;
  cursor: pointer;
  transition: all 0.2s ease;
}

.submit-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.submit-btn:not(:disabled):hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px -4px rgba(37, 99, 235, 0.4);
}
</style>
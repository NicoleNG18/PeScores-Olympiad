<template>
  <div class="class-details-page">
    <AppNavbar :is-dark="isDark" @toggle-theme="toggleTheme" />

    <main class="main-section">
      <div class="class-header-card">
        <div class="left-info">
          <div class="class-badge-large">8 А</div>
          <div>
            <h2>8 А клас — ПГЕЕ "Ап. Попов"</h2>
            <p>👤 {{ $t('classDetails.teacherLabel') || 'Учител' }}: Иван Георгиев | 📚 {{ $t('classDetails.studentsCount') || 'Общо ученици' }}: 26</p>
          </div>
        </div>

        <div class="view-switcher">
          <button :class="['switch-btn', { active: activeTab === 'students' }]" @click="activeTab = 'students'">
            👥 {{ $t('classDetails.tabStudents') || 'Ученици и управление' }}
          </button>
          <button :class="['switch-btn', { active: activeTab === 'standards' }]" @click="activeTab = 'standards'">
            🏆 {{ $t('classDetails.tabStandards') || 'Въвеждане на нормативи' }}
          </button>
        </div>
      </div>

      <div v-if="activeTab === 'students'" class="tab-content">
        <div class="section-actions">
          <h3>{{ $t('classDetails.sectionTitleStudents') || 'Списък на учениците' }}</h3>
          <button class="primary-btn" @click="openAddStudentModal">+ {{ $t('classDetails.addStudentBtn') || 'Добави ученик' }}</button>
        </div>

        <div class="students-grid">
          <div v-for="student in studentsList" :key="student.id" class="student-card">
            <div class="student-num">№ {{ student.number }}</div>
            <div class="student-details">
              <h4>{{ student.fullName }}</h4>
              <span class="student-score-summary">Резултати: Активен</span>
            </div>
            <div class="student-actions">
              <button class="icon-btn" :title="$t('classDetails.editTitle') || 'Редактирай'">✏️</button>
              <button class="icon-btn delete" :title="$t('classDetails.deleteTitle') || 'Изтрий'" @click="deleteStudent(student.id)">🗑️</button>
            </div>
          </div>
        </div>
      </div>

      <div v-else class="tab-content">
        <div class="standards-toolbar">
          <div class="exercise-selector">
            <label>{{ $t('classDetails.selectExercise') || 'Изберете норматив / дисциплина:' }}</label>
            <select v-model="selectedExercise" class="styled-select">
              <option value="run30">🏃‍♂️ 30 метра бягане (сек)</option>
              <option value="run200">🏃‍♂️ 200 метра бягане (сек)</option>
              <option value="longJump">🦘 Скок дължина от място (см)</option>
              <option value="medicineBall">🏐 Хвърляне на плътна топка (м)</option>
              <option value="tTest">⚡ Т-тест (сек)</option>
            </select>
          </div>
          <button class="save-changes-btn" @click="saveAllScores">💾 {{ $t('classDetails.saveBtn') || 'Запиши всички резултати' }}</button>
        </div>

        <div class="scores-table-container">
          <table class="scores-table">
            <thead>
            <tr>
              <th style="width: 50px;">№</th>
              <th>Име на ученика</th>
              <th style="width: 100px;">Предишен</th>
              <th style="width: 80px;">Опит 1</th>
              <th style="width: 80px;">Опит 2</th>
              <th style="width: 80px;">Опит 3</th>
              <th style="width: 95px;">Най-добър</th>
              <th style="width: 110px;">Оценка</th>
              <th>Забележка / Статус</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="student in studentsList" :key="student.id">
              <td class="font-bold">#{{ student.number }}</td>
              <td class="font-semibold">{{ student.fullName }}</td>

              <td>
                  <span class="history-badge" title="Резултат от предно изпитване">
                    📌 {{ student.previousBest || '-' }}
                  </span>
              </td>

              <td><input type="number" step="0.1" v-model="student.attempts[0]" class="score-input" placeholder="Ор. 1" /></td>
              <td><input type="number" step="0.1" v-model="student.attempts[1]" class="score-input" placeholder="Ор. 2" /></td>
              <td><input type="number" step="0.1" v-model="student.attempts[2]" class="score-input" placeholder="Ор. 3" /></td>

              <td>
                <span class="best-score-text">{{ getBestAttempt(student.attempts) }}</span>
              </td>

              <td>
                <select v-model="student.grade" class="grade-select">
                  <option value="">-</option>
                  <option value="6">Отличен (6)</option>
                  <option value="5">Мн. добър (5)</option>
                  <option value="4">Добър (4)</option>
                  <option value="3">Среден (3)</option>
                  <option value="2">Слаб (2)</option>
                </select>
              </td>

              <td>
                <input type="text" v-model="student.note" class="note-input" placeholder="напр. болен, освободен..." />
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </main>

    <AppFooter />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import AppNavbar from '../components/AppNavbar.vue'
import AppFooter from '../components/AppFooter.vue'

const { t } = useI18n()
const isDark = ref<boolean>(false)
const activeTab = ref<'students' | 'standards'>('standards')
const selectedExercise = ref('run30')

const studentsList = ref([
  {
    id: '1',
    number: 1,
    fullName: 'Иван Георгиев Иванов',
    previousBest: '4.8',
    attempts: ['', '', ''],
    grade: '',
    note: ''
  },
  {
    id: '2',
    number: 2,
    fullName: 'Петър Димитров Петров',
    previousBest: '5.2',
    attempts: ['5.1', '5.0', ''],
    grade: '5',
    note: 'Болен'
  },
  {
    id: '3',
    number: 3,
    fullName: 'Мария Стоянова Георгиева',
    previousBest: '4.6',
    attempts: ['', '', ''],
    grade: '',
    note: ''
  }
])

const getBestAttempt = (attempts: string[]) => {
  const validNumbers = attempts.map(a => parseFloat(a)).filter(n => !isNaN(n))
  if (validNumbers.length === 0) return '-'
  return Math.max(...validNumbers)
}

const toggleTheme = (): void => {
  isDark.value = !isDark.value
  const theme = isDark.value ? 'dark' : 'light'
  document.documentElement.setAttribute('data-theme', theme)
  localStorage.setItem('theme', theme)
}

const openAddStudentModal = () => {
}

const deleteStudent = (id: string) => {
  studentsList.value = studentsList.value.filter(s => s.id !== id)
}

const saveAllScores = () => {
  alert(t('classDetails.successSave') || 'Резултатите бяха записани успешно!')
}

onMounted(() => {
  const savedTheme = localStorage.getItem('theme') || 'light'
  isDark.value = savedTheme === 'dark'
  document.documentElement.setAttribute('data-theme', savedTheme)
})
</script>

<style scoped>
.class-details-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #f1f5f9;
}

:global(body.dark) .class-details-page,
:root[data-theme="dark"] .class-details-page {
  background-color: var(--bg-main, #0b0f19);
}

.main-section {
  flex: 1;
  max-width: 1350px;
  width: 100%;
  margin: 0 auto;
  padding: 2.5rem 1.5rem;
}

.class-header-card {
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 16px;
  padding: 1.5rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
  box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05);
  flex-wrap: wrap;
  gap: 1.5rem;
}

:global(body.dark) .class-header-card,
:root[data-theme="dark"] .class-header-card {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
}

.left-info {
  display: flex;
  align-items: center;
  gap: 1.25rem;
}

.class-badge-large {
  width: 65px;
  height: 65px;
  background: linear-gradient(135deg, var(--primary-color, #2563eb) 0%, #1e40af 100%);
  color: #ffffff;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 800;
  font-size: 1.3rem;
  box-shadow: 0 4px 12px rgba(37, 99, 235, 0.3);
}

.class-header-card h2 {
  font-size: 1.4rem;
  font-weight: 800;
  color: #0f172a;
  margin-bottom: 0.2rem;
}

:global(body.dark) .class-header-card h2,
:root[data-theme="dark"] .class-header-card h2 {
  color: var(--text-main, #f1f5f9);
}

.class-header-card p {
  font-size: 0.88rem;
  color: #64748b;
}

.view-switcher {
  display: flex;
  background: #f8fafc;
  padding: 0.3rem;
  border-radius: 12px;
  border: 1px solid #cbd5e1;
}

:global(body.dark) .view-switcher,
:root[data-theme="dark"] .view-switcher {
  background: rgba(255, 255, 255, 0.03);
  border-color: var(--border-color, #334155);
}

.switch-btn {
  padding: 0.6rem 1.2rem;
  background: transparent;
  border: none;
  border-radius: 9px;
  font-weight: 600;
  font-size: 0.9rem;
  color: #64748b;
  cursor: pointer;
  transition: all 0.2s ease;
}

.switch-btn.active {
  background: var(--primary-color, #2563eb);
  color: #ffffff;
  box-shadow: 0 4px 12px rgba(37, 99, 235, 0.3);
}

.standards-toolbar {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  margin-bottom: 1.5rem;
  gap: 1rem;
  flex-wrap: wrap;
}

.exercise-selector {
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.exercise-selector label {
  font-size: 0.85rem;
  font-weight: 600;
  color: var(--text-main);
}

.styled-select {
  padding: 0.7rem 1rem;
  border: 1px solid #cbd5e1;
  border-radius: 10px;
  background: #ffffff;
  color: var(--text-main);
  font-weight: 600;
  outline: none;
}

:global(body.dark) .styled-select,
:root[data-theme="dark"] .styled-select {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
}

.save-changes-btn {
  background: #22c55e;
  color: #ffffff;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 10px;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.2s ease;
  box-shadow: 0 4px 12px rgba(34, 197, 94, 0.3);
}

.save-changes-btn:hover {
  background: #16a34a;
  transform: translateY(-2px);
}

.scores-table-container {
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 14px;
  overflow-x: auto;
  box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05);
}

:global(body.dark) .scores-table-container,
:root[data-theme="dark"] .scores-table-container {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
}

.scores-table {
  width: 100%;
  border-collapse: collapse;
  min-width: 950px;
}

.scores-table th, .scores-table td {
  padding: 0.85rem 1rem;
  text-align: left;
  border-bottom: 1px solid #e2e8f0;
}

:global(body.dark) .scores-table th,
:global(body.dark) .scores-table td,
:root[data-theme="dark"] .scores-table th,
:root[data-theme="dark"] .scores-table td {
  border-bottom-color: var(--border-color, #334155);
}

.scores-table th {
  background: #f8fafc;
  font-weight: 700;
  font-size: 0.82rem;
  color: #64748b;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}

:global(body.dark) .scores-table th,
:root[data-theme="dark"] .scores-table th {
  background: rgba(255, 255, 255, 0.02);
}

.score-input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #cbd5e1;
  border-radius: 6px;
  background: #f8fafc;
  font-weight: 700;
  font-size: 0.9rem;
  text-align: center;
  color: var(--text-main);
  outline: none;
}

:global(body.dark) .score-input,
:root[data-theme="dark"] .score-input {
  background: var(--bg-main, #0b0f19);
  border-color: var(--border-color, #334155);
}

.score-input:focus {
  border-color: var(--primary-color);
  background: #ffffff;
}

.grade-select {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #cbd5e1;
  border-radius: 6px;
  background: #f8fafc;
  font-weight: 700;
  font-size: 0.85rem;
  color: var(--text-main);
  outline: none;
}

:global(body.dark) .grade-select,
:root[data-theme="dark"] .grade-select {
  background: var(--bg-main, #0b0f19);
  border-color: var(--border-color, #334155);
}

.grade-select:focus {
  border-color: var(--primary-color);
  background: #ffffff;
}

.note-input {
  width: 100%;
  padding: 0.5rem 0.75rem;
  border: 1px solid #cbd5e1;
  border-radius: 6px;
  background: #f8fafc;
  font-size: 0.85rem;
  color: var(--text-main);
  outline: none;
}

:global(body.dark) .note-input,
:root[data-theme="dark"] .note-input {
  background: var(--bg-main, #0b0f19);
  border-color: var(--border-color, #334155);
}

.history-badge {
  font-size: 0.8rem;
  font-weight: 600;
  color: #64748b;
  background: rgba(100, 116, 139, 0.1);
  padding: 0.3rem 0.6rem;
  border-radius: 6px;
  display: inline-block;
}

.best-score-text {
  font-weight: 800;
  color: var(--primary-color);
  font-size: 0.95rem;
}

.section-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.2rem;
}

.section-actions h3 {
  font-size: 1.2rem;
  font-weight: 700;
  color: var(--text-main);
}

.students-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 1rem;
}

.student-card {
  background: #ffffff;
  border: 1px solid #cbd5e1;
  border-radius: 12px;
  padding: 1rem 1.25rem;
  display: flex;
  align-items: center;
  gap: 1rem;
}

:global(body.dark) .student-card,
:root[data-theme="dark"] .student-card {
  background: var(--bg-card, #151c2c);
  border-color: var(--border-color, #334155);
}

.student-num {
  font-weight: 800;
  font-size: 1rem;
  color: var(--primary-color);
  background: rgba(37, 99, 235, 0.1);
  padding: 0.4rem 0.6rem;
  border-radius: 8px;
}

.student-details h4 {
  font-size: 0.95rem;
  font-weight: 700;
  color: var(--text-main);
  margin-bottom: 0.1rem;
}

.student-score-summary {
  font-size: 0.75rem;
  color: #64748b;
}

.student-actions {
  margin-left: auto;
  display: flex;
  gap: 0.4rem;
}

.icon-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  font-size: 0.9rem;
  padding: 0.3rem;
  border-radius: 6px;
}

.icon-btn.delete:hover {
  background: rgba(239, 68, 68, 0.1);
}

.primary-btn {
  background: var(--primary-color);
  color: #ffffff;
  border: none;
  padding: 0.6rem 1rem;
  border-radius: 8px;
  font-weight: 700;
  cursor: pointer;
}
</style>
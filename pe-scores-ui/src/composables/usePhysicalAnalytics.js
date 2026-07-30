import { ref, computed } from 'vue'

export function usePhysicalAnalytics() {
    const selectedClass = ref('5A')
    const selectedTask = ref('50m')
    const selectedStudent = ref('1')
    const rawResult = ref('')

    const calculatedPercent = computed(() => {
        if (!rawResult.value || isNaN(rawResult.value)) return null
        const val = parseFloat(rawResult.value)

        switch (selectedTask.value) {
            case '50m': // По-малкото време е по-добро
                if (val <= 7.2) return 100
                if (val <= 8.0) return 85
                return 65
            case 'jump': // По-голямото разстояние е по-добро
                if (val >= 210) return 100
                if (val >= 180) return 85
                return 65
            case 'ball':
                if (val >= 10) return 100
                if (val >= 8) return 85
                return 60
            default:
                return 50
        }
    })

    return {
        selectedClass,
        selectedTask,
        selectedStudent,
        rawResult,
        calculatedPercent
    }
}
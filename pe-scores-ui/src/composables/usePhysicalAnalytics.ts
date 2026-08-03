import { ref, computed, type Ref, type ComputedRef } from 'vue'

export type TaskType = '50m' | 'jump' | 'ball' | string;

export interface UsePhysicalAnalyticsReturn {
    selectedClass: Ref<string>;
    selectedTask: Ref<TaskType>;
    selectedStudent: Ref<string>;
    rawResult: Ref<string>;
    calculatedPercent: ComputedRef<number | null>;
}

export function usePhysicalAnalytics(): UsePhysicalAnalyticsReturn {
    const selectedClass = ref<string>('5A')
    const selectedTask = ref<TaskType>('50m')
    const selectedStudent = ref<string>('1')
    const rawResult = ref<string>('')

    const calculatedPercent = computed<number | null>(() => {
        if (!rawResult.value || isNaN(Number(rawResult.value))) return null
        const val: number = parseFloat(rawResult.value)

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
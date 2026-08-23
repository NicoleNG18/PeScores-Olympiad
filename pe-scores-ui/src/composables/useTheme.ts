import { ref, type Ref } from 'vue'

export interface UseThemeReturn {
    isDark: Ref<boolean>;
    initTheme: () => void;
    toggleTheme: () => void;
}

const isDark = ref<boolean>(false)

export function useTheme(): UseThemeReturn {
    const initTheme = (): void => {
        const savedTheme: string = localStorage.getItem('theme') || 'light'
        isDark.value = savedTheme === 'dark'
        document.documentElement.setAttribute('data-theme', savedTheme)
    }

    const toggleTheme = (): void => {
        isDark.value = !isDark.value
        const theme: string = isDark.value ? 'dark' : 'light'
        document.documentElement.setAttribute('data-theme', theme)
        localStorage.setItem('theme', theme)
    }

    return {
        isDark,
        initTheme,
        toggleTheme
    }
}
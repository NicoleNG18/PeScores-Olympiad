import { ref } from 'vue'

const isDark = ref(false)

export function useTheme() {
    const initTheme = () => {
        const savedTheme = localStorage.getItem('theme') || 'light'
        isDark.value = savedTheme === 'dark'
        document.documentElement.setAttribute('data-theme', savedTheme)
    }

    const toggleTheme = () => {
        isDark.value = !isDark.value
        const theme = isDark.value ? 'dark' : 'light'
        document.documentElement.setAttribute('data-theme', theme)
        localStorage.setItem('theme', theme)
    }

    return {
        isDark,
        initTheme,
        toggleTheme
    }
}
import { createI18n } from 'vue-i18n'
import bg from './locales/bg.json'
import en from './locales/en.json'

const savedLocale = localStorage.getItem('lang') || 'bg'

const i18n = createI18n({
    legacy: false, // Използваме Composition API
    locale: savedLocale,
    fallbackLocale: 'bg',
    messages: {
        bg,
        en
    }
})

export default i18n
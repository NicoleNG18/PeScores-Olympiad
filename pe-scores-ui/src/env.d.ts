/// <reference types="vite/client" />

declare module '*.vue' {
    import type { DefineComponent } from 'vue'
    const component: DefineComponent<{}, {}, any>
    export default component
}

declare module '*.json' {
    const value: Record<string, any>
    export default value
}

declare module './i18n' {
    const i18n: any
    export default i18n
}
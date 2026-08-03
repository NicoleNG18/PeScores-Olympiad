import { createRouter, createWebHistory, type RouteRecordRaw, type Router } from 'vue-router'
import AuthView from '../views/AuthView.vue'
import DashboardView from '../views/DashboardView.vue'

declare module 'vue-router' {
    interface RouteMeta {
        requiresAuth?: boolean
    }
}

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        redirect: '/dashboard'
    },
    {
        path: '/auth',
        name: 'Auth',
        component: AuthView,
        meta: { requiresAuth: false }
    },
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: DashboardView,
        meta: { requiresAuth: true }
    },
    {
        path: '/standards',
        name: 'Standards',
        component: () => import('../views/StandardsView.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/login',
        redirect: '/auth'
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/dashboard'
    }
]

const router: Router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

router.beforeEach((to, _from, next) => {
    const token = localStorage.getItem('token')
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth !== false)

    if (requiresAuth && !token) {
        next('/auth')
    } else if (to.path === '/auth' && token) {
        next('/dashboard')
    } else {
        next()
    }
})

export default router
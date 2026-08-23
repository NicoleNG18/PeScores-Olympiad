import { createRouter, createWebHistory, type RouteRecordRaw, type Router } from 'vue-router'
import AuthView from '../views/AuthView.vue'
import DashboardView from '../views/DashboardView.vue'
import HomeView from '../views/HomeView.vue'
import AddSchoolView from '../views/AddSchoolView.vue'
import StandardsView from '../views/StandardsView.vue'
import AddClassView from '../views/AddClassView.vue'
import ClassesView from '../views/ClassesView.vue'
import ClassDetailsView from '../views/ClassDetailsView.vue'
import SetPasswordView from '../views/SetPasswordView.vue'

declare module 'vue-router' {
    interface RouteMeta {
        requiresAuth?: boolean
    }
}

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'Home',
        component: HomeView,
        meta: { requiresAuth: false }
    },
    {
        path: '/auth',
        name: 'Auth',
        component: AuthView,
        meta: { requiresAuth: false }
    },
    {
        path: '/set-password',
        name: 'set-password',
        component: SetPasswordView
    },
    {
        path: '/classes',
        name: 'Classes',
        component: ClassesView,
        meta: { requiresAuth: false }
    },
    {
        path: '/classes/:id',
        name: 'class-details',
        component: ClassDetailsView,
        meta: { requiresAuth: true },
        props: true
    },
    {
        path: '/add-class',
        name: 'AddClass',
        component: AddClassView,
        meta: { requiresAuth: false }
    },
    {
        path: '/add-school',
        name: 'AddSchool',
        component: AddSchoolView,
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
        component: StandardsView,
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
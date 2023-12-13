import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from '../views/LoginForm.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginForm
    },
    {
      path: '/:catchAll(.*)',
      redirect: '/login'
    }
  ]
})

export default router

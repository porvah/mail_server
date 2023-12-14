import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from '../views/LoginForm.vue'
import SignUpForm from '../views/SignUpForm.vue'
import Home from '../views/Home.vue'
import Inbox from '../views/Inbox.vue'
import Contacts from '../views/Contacts.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginForm
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignUpForm
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      children: [
        {
          path: 'inbox',
          name: 'inbox',
          component: Inbox
        },
        {
          path: 'sent',
          // name: 'inbox',
          component: Inbox
        },
        {
          path: 'contacts',
          // name: 'inbox',
          component: Contacts
        },
        {
          path: 'draft',
          // name: 'inbox',
          component: Inbox
        },
        {
          path: 'starred',
          // name: 'inbox',
          component: Inbox
        },
        {
          path: 'folders',
          // name: 'inbox',
          component: Inbox
        },
        {
          path: 'trash',
          // name: 'inbox',
          component: Inbox
        }
      ]
    },
    {
      path: '/:catchAll(.*)',
      redirect: '/login'
    }
  ]
})

export default router

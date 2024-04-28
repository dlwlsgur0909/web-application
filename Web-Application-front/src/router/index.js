import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

import HomeView from '../views/HomeView.vue'
import ChangePasswordView from '../views/user/ChangePasswordView.vue'
import userProjectManagement from '../views/userProject/UserProjectManagementView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/auth/LoginView.vue')
    },
    // {
    //   path: '/findAccount',
    //   name: 'findAccount',
    //   component: () => import('../views/auth/FindAccount.vue')
    // },
    {
      path: '/changePassword',
      name: 'changePassword',
      component: ChangePasswordView
    },
    {
      path: '/userProject',
      name: 'userProjectManagement',
      component: userProjectManagement
    }
  ]
})

export default router

router.beforeEach(async (to) => {
  const auth = useAuthStore();
  const loginPage = ['/login'];
  const toPathIsLoginPage = loginPage.includes(to.path);

  if(toPathIsLoginPage ^ !auth.user) {
    if(!auth.user){
      return '/login';
    } else {
      return '/';
    }
  }
})

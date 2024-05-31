<script setup>

import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router'

const auth = useAuthStore();
const router = useRouter()


const logout = () => {
  auth.logout();
  router.push('/login');  
}


</script>

<template>
  <main>
    <h1>
      안녕😍 {{ auth.user.nickname }} 
    </h1>
    <button @click="logout()">로그아웃</button>
  </main>
</template>

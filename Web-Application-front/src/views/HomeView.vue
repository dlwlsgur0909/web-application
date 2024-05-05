<script setup>

import { useAuthStore } from '@/stores/auth';

const auth = useAuthStore();



</script>

<template>
  <main>
    <h1>
      안녕😍 {{ auth.user.nickname }} 
    </h1>
    <img src="../assets/subin.jpg" alt="" width="300px" height="300px">
  </main>
</template>

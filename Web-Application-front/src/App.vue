<script setup>
import { inject } from 'vue'
import { RouterView } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const axios = inject('axios')
const auth = useAuthStore()

</script>

<template>
  <div>
    <header>
    </header>

    <RouterView />
  </div>

</template>

<style scoped>
</style>

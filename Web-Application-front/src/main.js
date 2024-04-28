import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import customAxios from './axios/interceptors.js'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.provide('axios', customAxios())

app.mount('#app')

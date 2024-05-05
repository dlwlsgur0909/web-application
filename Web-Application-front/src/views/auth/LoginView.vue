<script setup>
import { ref } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'
import { inject } from 'vue';

const axios = inject('axios');
const router = useRouter()
const auth = useAuthStore()

const username = ref('');
const password = ref('');

const login = () => {
    const requestBody = {
        username: username.value,
        password: password.value,
    };

    auth.login(
        requestBody, 
        () => { 
            router.push('/');
        }, 
        () => {}
    );
}

const joinPage = () => {
    console.log('회원가입 클릭!');
}

const findPassword = () => {
    console.log('비밀번호 찾기 클릭');
}

</script>
<template>
    <div>
        <h1>로그인</h1>
        <div>
            <label for="username">아이디 : </label>
            <input type="text" name="username" v-model="username"/>
        </div>
        <div>
            <label for="password">비밀번호 : </label>
            <input type="text" name="password" v-model="password"/>
        </div>
        <button @click="login()">로그인</button>
        <button @click="joinPage()">회원가입</button>
        <div>
            <a @click="findPassword()">비밀번호 찾기</a>
        </div>
    </div>
</template>
<style scoped>
</style>
<script setup>
import { ref } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'
import { inject } from 'vue';

const axios = inject('axios');
const router = useRouter()
const auth = useAuthStore()

const phoneNumber = ref('01011111111');
const password = ref('testPassword123');

const loginClicked = () => {
    const requestBody = {
        userMobileNumber: phoneNumber.value,
        userPassword: password.value,
    };

    auth.login(requestBody, () => { router.push('/'); test() }, () => {});
}

function test() {
    const requestBody = {
        currentPassword: 'testPassword123',
        newPassword: 'testPassword1234'
    }
    axios.patch('v1/mobile/wfm/users/password', requestBody)
    .then((res) => {
        console.log(res);
    })
    .catch((err) => {
        console.log(err);
    })
}

</script>
<template>
    <div>
        <h1>로그인</h1>
        <div>
            <label for="phoneNumber">핸드폰번호 : </label>
            <input type="text" name="phoneNumber" v-model="phoneNumber"/>
        </div>
        <div>
            <label for="password">비밀번호 : </label>
            <input type="text" name="password" v-model="password"/>
        </div>
        <button @click="loginClicked()">로그인</button>
        <button @click="">사용등록</button>
        <div>
            <a @click="">forgot your password?</a>
        </div>
    </div>
</template>
<style scoped>
</style>
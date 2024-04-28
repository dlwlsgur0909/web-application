<script setup>
import { ref } from 'vue'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()

const mode = ref('LOGIN')

const phoneNumber = ref('01011111111');
const password = ref('testPassword123');
const username = ref('테스트김유림');
const residentNumber = ref('240418-1******');

const loginClicked = () => {
    auth.isLogin = true;
}

const residentNumberCheckClicked = (currentMode) => {
    window.alert("실명인증 되었습니다.");
    if(currentMode === 'REGISTER'){
        mode.value='LOGIN';
    }
}

const changePhoneNumberClicked = () => {
    window.alert('휴대전화번호가 변경되었습니다.');
    mode.value ='LOGIN';
}

const resetPasswordClicked = () => {
    window.alert('비밀번호가 재설정 되었습니다.');
    mode.value = 'LOGIN';
}

</script>
<template>
    <div>
        <div v-if="mode === 'LOGIN'">
            <h1>로그인</h1>
            <div>
                <label for="phoneNumber">핸드폰번호 : </label>
                <input type="text" name="phoneNumber" v-model="phoneNumber"/>
            </div>
            <div>
                <label for="password">비밀번호 : </label>
                <input type="text" name="password" v-model="password"/>
            </div>
            <button @click="auth.isLogin = !auth.isLogin">로그인</button>
            <button @click="mode='REGISTER'">사용등록</button>
            <div>
                <a @click="mode='FIND_ACCOUNT'">forgot your password?</a>
            </div>
        </div>

        <div v-else-if="mode === 'REGISTER'">
            <h1>
                <button @click="mode='LOGIN'">뒤로</button>
                사용등록
            </h1>
            <div>
                <label for="username">이름 : </label>
                <input type="text" name="username" v-model="username"/>
            </div>
            <div>
                <label for="residentNumber">주민등록 번호 : </label>
                <input type="text" name="residentNumber" v-model="residentNumber"/>
            </div>
            <button @click="residentNumberCheckClicked('REGISTER')">실명인증</button>
        </div>

        <div v-else-if="mode === 'FIND_ACCOUNT'">
            <h1>
                <button @click="mode='LOGIN'">뒤로</button>
                사용자 계정 확인
            </h1>
            <div>
                <label for="username">이름 : </label>
                <input type="text" name="username" v-model="username"/>
            </div>
            <div>
                <label for="residentNumber">주민등록 번호 : </label>
                <input type="text" name="residentNumber" v-model="residentNumber"/>
            </div>
            <div>
                <button @click="changePhoneNumberClicked()">휴대전화번호 변경</button>
                <button @click="resetPasswordClicked()">비밀번호 재설정</button>
            </div>
            <button @click="residentNumberCheckClicked('FIND_ACCOUNT')">실명인증</button>
        </div>

        <div v-else-if="mode === 'REGISTER'">
            <h1>
                <button @click="mode='FIND_ACCOUNT'">뒤로</button>
                휴대전화번호 변경
            </h1>
            <div>
                <label for="username">이름 : </label>
                <input type="text" name="username" v-model="username"/>
            </div>
            <div>
                <label for="residentNumber">주민등록 번호 : </label>
                <input type="text" name="residentNumber" v-model="residentNumber"/>
            </div>
            <button @click="residentNumberCheckClicked('REGISTER')">실명인증</button>
        </div>
    </div>
</template>
<style scoped>
</style>
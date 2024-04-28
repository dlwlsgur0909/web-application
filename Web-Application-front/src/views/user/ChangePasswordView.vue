<script setup>
import axios from 'axios';
import { ref } from 'vue';

// 현재 비밀번호 변수
const currentPassword = ref('');
const showCurrentPassword = ref(false);
// 새 비밀번호 변수
const newPassword = ref('');
const showNewPassword = ref(false);
// 비밀번호 확인 변수
const newPasswordCheck = ref('');
const showNewPasswordCheck = ref(false);

// 비밀번호 10자리 이상여부
const checkPasswordLength = ref(false);
// 비밀번호 영어/숫자/특수문자 조합
const checkPasswordPattern = ref(false);

// 현재 비밀번호 표시 on/off
const currentPasswordOnOff = () => {
    showCurrentPassword.value = !showCurrentPassword.value;
};
// 새로운 비밀번호 표시 on/off
const newPasswordOnOff = () => {
    showNewPassword.value = !showNewPassword.value;
};
// 새로운 비밀번호 확인 표시 on/off
const newPasswordCheckOnOff = () => {
    showNewPasswordCheck.value = !showNewPasswordCheck.value;
};

// 새로운 비밀번호 update 함수
const updateNewPassword = (value) => {
    // 비밀번호 10자이상
    checkPasswordLength.value = value.length >= 10;
    // 비밀번호 정규식(영문/숫자/특수문자 10자리 이상)
    const check = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$^@$!%*#?&])[A-Za-z\d$^@$!%*#?&]{10,}$/;
    checkPasswordPattern.value = check.test(value);
};

// 확인 버튼 클릭 이벤트
const checkPassword = () => {
    if(currentPassword.value.trim() === '' || newPassword.value.trim() === '' || newPasswordCheck.value.trim() === ''){
        console.log('입력란 모두 입력해주세요.');
    }
    else if(!checkPasswordLength.value){
        console.log('비밀번호 10자이상 입력해주세요.');
    }
    else if(!checkPasswordPattern.value){
        console.log('비밀번호는 영문/숫자/특수문자 조합으로 입력해주세요.');
    }
    else if(newPassword.value !== newPasswordCheck.value){
        console.log('비밀번호와 비밀번호 확인란 입력이 다릅니다!');
    }
    else{
        const sendData = {
            currentPassword: currentPassword.value.trim(),
            newPassword : newPassword.value.trim(),
        };
        
        // axios interceptor 설정 후 import 변경 필요
        // 비밀번호 변경 axios patch
        axios.patch(`/v1/mobile/wfm/users/password`, sendData)
        .then((res) => {
            console.log('변경 성공!')
        })
        .catch((err) => {
            console.log(err)
        })
    }
};

</script>

<template>
    <div>
        <div>
            <label for="currentPassword">
                현재 비밀번호
            </label>
            <input
                id="currentPassword"
                :type="showCurrentPassword ? 'text' : 'password'"
                v-model="currentPassword"
            />
            <span @click="currentPasswordOnOff()">👁️‍🗨️</span>
        </div>
        <div>
            <label for="newPassword">
                비밀번호
            </label>
            <input
                id="newPassword"
                :type="showNewPassword ? 'text' : 'password'"
                v-model="newPassword"
                @input="updateNewPassword($event.target.value)"
            />
            <span @click="newPasswordOnOff()">👁️‍🗨️</span>
        </div>
        <div>
            <div v-if="!checkPasswordLength">10자리 이상</div>
            <div v-if="!checkPasswordPattern">영문/숫자/특수문자 조합</div>
        </div>
        <div>
            <label for="newPasswordCheck">
                비밀번호 확인
            </label>
            <input
                id="newPasswordCheck"
                :type="showNewPasswordCheck ? 'text' : 'password'"
                v-model="newPasswordCheck"
            />
            <span @click="newPasswordCheckOnOff()">👁️‍🗨️</span>
        </div>
        <button @click="checkPassword()">
            확인
        </button>
    </div>
</template>

<style>
    
</style>
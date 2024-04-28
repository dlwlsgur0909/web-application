<script setup>
import axios from 'axios';
import { ref } from 'vue';

// 선택한 고객사 (select box) 변수
const selectedCustomer = ref('');
// 고객사 options
const customerOptions = ref([]);
// 선택한 프로젝트 (radio button) 변수
const selectedProject = ref('');
// 프로젝트 options
const projectOptions = ref([]);

// 고객사 options get
const getCustomerOptions = async() => {
    try{
        const res = await axios.get(`/v1/mobile/wfm/users/customers`);
        customerOptions.value = res.data;
        selectedCustomer.value = customerOptions.value[0].customerCode;
    }
    catch(err) {
        console.log(err);
    }
};

// 프로젝트 options get
const getProjectOptions = async() => {
    try{
        const res = await axios.get(`/v1/mobile/wfm/users/projects?customerCode=${selectedCustomer.value}`);
        projectOptions.value = res.data;
        // selected project 는 user가 token에 가지고 있는 값으로 판단 -> registerCode

    }
    catch(err){
        console.log(err)
    }
};

// 고객사 options get & 프로젝트 options get 순서 지정 필요에 의한 함수
const setOptions = async() => {
    await getCustomerOptions();
    if(selectedProject.value){
        await getProjectOptions();
    }
};
setOptions();

// 선택버튼 클릭 이벤트
const updateSelectedProject = () => {
    const sendData = {
        registerCode: selectedProject.value
    };
    
    // 회사선택 변경 axios patch
    axios.patch(`/v1/mobile/wfm/users/projects`,sendData)
    .then((res) => {
        // 토큰 재발급 관련 코드 필요
    })
    .catch((err) => {
        console.log(err)
    })
};
</script>

<template>
    <div>
        <div>
            <label for="selectedCustomer">
                고객선택
            </label>
            <select 
                id="selectedCustomer" 
                v-model="selectedCustomer" 
                @change="getProjectOptions()"
            >
                <option 
                    v-for="customer in customerOptions" 
                    :key="customer.customerCode" 
                    :value="customer.customerCode"
                > 
                    {{ customer.customerName }}
                </option>
            </select>
        </div>
        <div>
            <span>
                프로젝트(법인) 선택
            </span>
            <div 
                v-for="project in projectOptions" 
                :key="project.registerCode"
            >
                <input 
                    type="radio" 
                    :id="project.registerCode" 
                    v-model="selectedProject" 
                    :value="project.registerCode"
                />
                <label 
                    :for="project.registerCode"
                >
                    ({{ project.corporationName }}) - {{ project.projectName }}
                </label>
            </div>
            {{ selectedProject }}
        </div>
        <button @click="updateSelectedProject()">선택</button>
    </div>
</template>

<style>
    
</style>
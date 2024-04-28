import axios from 'axios';
import { useAuthStore } from "@/stores/auth";

const interceptors = () => {
    const auth = useAuthStore();

    const instance = axios.create();

    // 요청 인터셉터
    instance.interceptors.request.use(
        (config) => {
            const accessToken = auth.user.accessToken;
            
            config.headers['Content-Type'] = 'application/json; charset=utf-8';
            config.headers['Authorization'] =  `Bearer ${accessToken}`;
            console.log(config.headers)
            
            return config;
        },
        async (error) => {
            return Promise.reject(error);
        }
    )


    // 응답 인터셉터
    instance.interceptors.response.use(
        (response) => {
            return response;
        },
        async (error) => {
            console.log(error);
            return Promise.reject(error);
        }
    )

    return instance;
}

export default interceptors;
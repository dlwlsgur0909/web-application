import { defineStore } from 'pinia'
import axios from 'axios';


export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    user: JSON.parse(localStorage.getItem('user')),
    isLogin: false,
  }),
  actions: {
    login(data, onSuccess, onError) {
      axios.post('/api/v1/auth/login', data)
      .then((res) => {
        this.setUser(res.data);
        onSuccess();
      })
      .catch((err) => {
        onError();
      })
    },

    logout() {
      localStorage.removeItem('user');
      this.user = null;
    },

    setUser(user) {
      if(user !== null){
        this.user = user;
        localStorage.setItem('user', JSON.stringify(user));
        this.isLogin = true;
      } else {
        localStorage.removeItem('user');
        this.isLogin = false;
      }
    },

    tokenRefresh() {

    },

  }
})

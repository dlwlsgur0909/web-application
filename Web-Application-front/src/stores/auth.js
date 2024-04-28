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
      axios.post('/v1/mobile/wfm/auth/login', data)
      .then((res) => {
        this.setUser(res.data);
        onSuccess();
      })
      .catch((err) => {
        onError();
      })
    },

    logout(data, onSuccess, onError) {
      this.setUser(null);
      onSuccess();
    },

    setUser(user) {
      if(user != null){
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

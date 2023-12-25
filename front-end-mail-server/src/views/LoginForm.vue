<template>
  <h2 id="welcome">Welcome to Mail Pulse</h2>

  <form @submit.prevent="login">
    <h2 id="login">Login</h2>

    <label>Email:</label>
    <input v-model="email" type="email" name="email" required />

    <label>Password:</label>
    <input v-model="password" type="password" name="password" required />

    <div v-if="errorMsg" id="error">{{ errorMsg }}</div>

    <button type="submit">Login</button>

    <p>Don't have an account? <span @click="goToSignUp">Sign up</span></p>
  </form>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default {
  setup() {
    const router = useRouter()
    const store = useStore()

    const email = ref('')
    const password = ref('')
    const errorMsg = ref('')

    const login = async () => {
      try {
        await store.dispatch('login', { email: email.value, password: password.value })
        router.push('/home/inbox')
      } catch (e) {
        errorMsg.value = JSON.parse(e).msg
      }
    }

    const goToSignUp = () => {
      router.push('/signup')
    }

    return { login, goToSignUp, email, password, errorMsg }
  }
}
</script>

<style scoped>
#login {
  border-bottom: 1px solid #555;
  background: transparent;
}

#welcome {
  padding: 15px 40px;
  width: 420px;
  color: green;
  background: #eee;
  border-radius: 12px;
  margin: 0 auto 20px auto;
  box-sizing: content-box;
}

form {
  width: 420px;
  margin: 0 auto;
  background: white;
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}

label {
  color: #aaa;
  display: inline-block;
  margin: 25px 0 15px;
  font-size: 12px;
  letter-spacing: 1px;
  font-weight: bold;
  background: white;
}

input {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  color: #555;
  background: white;
}

button {
  display: block;
  margin-top: 15px;
  padding: 15px;
  width: 100%;
  color: white;
  background: green;
  border: none;
  border-radius: 12px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
}

p {
  color: #555;
  margin-top: 12px;
  text-align: center;
  background: white;
}

span {
  color: green;
  cursor: pointer;
  background: white;
}

#error {
  color: red;
  padding: 10px;
  text-align: center;
  font-weight: bold;
}
</style>

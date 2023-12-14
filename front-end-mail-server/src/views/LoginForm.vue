<template>
  <h2 id="welcome">Welcome to Mail Pulse</h2>

  <form @submit.prevent>
    <h2 id="login">Login</h2>

    <label>Email:</label>
    <input type="email" name="email" required />

    <label>Password:</label>
    <input type="password" name="password" required />

    <button @click="login">Login</button>

    <p>Don't have an account? <span @click="goToSignUp">Sign up</span></p>
  </form>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  setup() {
    const router = useRouter()

    const login = () => {
      router.push('/home/inbox')
    }

    const goToSignUp = () => {
      router.push('/signup')
    }

    return { login, goToSignUp }
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
</style>

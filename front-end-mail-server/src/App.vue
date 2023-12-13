<template>
  <div>
    <router-view />
  </div>
</template>

<script>
import LoginForm from './views/LoginForm.vue'

export default {
  components: { LoginForm }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

body {
  margin: 0px;
  padding: 0px;
  background: #eee;
}
</style>

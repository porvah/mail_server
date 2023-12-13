<template>
  <!-- <div> -->
  <router-view />
  <!-- </div> -->
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
  background: transparent;
  /* width: 100%;
  height: 100%; */
  background-image: url('./assets/background-image.jpg');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  min-height: 100vh;
}

* {
  margin: 0px;
  padding: 0px;
}
</style>

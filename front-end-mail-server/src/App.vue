<template>
  <div>
    <RegisterBar />
  </div>
</template>

<script>
import RegisterBar from './components/RegisterBar.vue'

export default {
  components: { RegisterBar }
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

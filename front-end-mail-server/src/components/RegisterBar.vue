<template>
  <div>
    <div class="bar">
      <router-link to="/">Login</router-link> |
      <router-link to="/signup">Sign Up</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
a.router-link-exact-active {
  color: white;
  background: green;
  padding: 10px;
  border-radius: 12px;
  border: none;
}

.bar {
  margin: 20px auto;
  left: 50%;
  position: relative;
}
</style>

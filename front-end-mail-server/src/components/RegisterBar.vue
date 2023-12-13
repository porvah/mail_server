<template>
  <div>
    <div class="bar">
      <h2>Welcome to Mail Pulse</h2>
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
  margin: 10px;
  border-radius: 12px;
  border: none;
}

.bar {
  margin: 20px auto;
  left: 50%;
  position: relative;
}

h2 {
  margin: 15px;
  padding: 15px;
  color: white;
  background: #999;
  border-radius: 12px;
}
</style>

<template>
  <form @submit.prevent>
    <h2>Sign Up</h2>

    <label>Name:</label>
    <input type="text" name="name" required />

    <label>Email:</label>
    <input type="email" name="email" required />

    <label>Password:</label>
    <input type="password" name="password" required />

    <button>Sign Up</button>
  </form>
</template>

<script>
export default {}
</script>

<style scoped>
h2 {
  border-bottom: 1px solid #555;
}

form {
  width: 420px;
  left: 50%;
  position: relative;
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
}

input {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  color: #555;
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
}

p {
  color: #555;
  margin-top: 12px;
  text-align: center;
}

span {
  color: green;
  cursor: pointer;
}
</style>

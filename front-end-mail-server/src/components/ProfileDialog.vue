<template>
  <dialog open>
    <h2>Profile</h2>

    <div class="content">
      <label>Name:</label>
      <h3>{{ name }}</h3>

      <label>Email:</label>
      <h3>{{ email }}</h3>
    </div>

    <button @click="closeProfile" id="close-btn" type="button">
      <span class="material-symbols-outlined"> cancel </span>
      Close
    </button>
  </dialog>
</template>

<script>
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()
    const user = store.getters.user
    const name = user.name
    const email = user.email

    const closeProfile = () => {
      store.commit('closeProfileDialog')
    }

    return { name, email, closeProfile }
  }
}
</script>

<style scoped>
dialog {
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -25vh;
  margin-top: -25vh;
  width: 300px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  border: 1px solid gray;
  border-radius: 12px;
}

h2 {
  border-bottom: 1px solid gray;
  margin-bottom: 14px;
  padding-bottom: 4px;
}
.content {
  padding: 10px;
  text-align: left;
}

label,
h3 {
  margin: 10px 0;
}

button {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin: 10px 0;
  padding: 10px;
  width: 100%;
  border: none;
  border-radius: 12px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
}

#close-btn {
  color: green;
  background: white;
  border: 1px solid green;
}
</style>

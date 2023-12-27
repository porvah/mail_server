<template>
  <dialog open>
    <h2>Settings</h2>

    <button @click="logoutUser" id="logout-btn" type="button">
      <span class="material-symbols-outlined"> logout </span>

      Logout
    </button>

    <!-- <button @click="closeCompose" id="delete-btn" type="button">
      <span class="material-symbols-outlined"> delete_forever </span>

      Delete Account
    </button> -->

    <button @click="closeSettings" id="close-btn" type="button">
      <span class="material-symbols-outlined"> cancel </span>

      Close
    </button>
  </dialog>
</template>

<script>
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()
    const router = useRouter()

    const logoutUser = async () => {
      await store.dispatch('logout', { token: store.getters.token })
      closeSettings()
      router.push({ name: 'login' })
    }

    const closeSettings = () => {
      store.commit('closeSettingsDialog')
    }

    return { logoutUser, closeSettings }
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

#logout-btn {
  color: white;
  background: gray;
}

#delete-btn {
  color: white;
  background: red;
}

#close-btn {
  color: green;
  background: white;
  border: 1px solid green;
}
</style>

<template>
  <div id="header">
    <div class="left">
      <span class="material-symbols-outlined logo"> mail </span>
      <p class="title">Mail Pulse</p>
    </div>
    <div class="middle">
      <span class="material-symbols-outlined"> refresh </span>
      <p id="user">Registered as ...</p>
    </div>
    <div class="right">
      <span @click="openProfileDialog" class="material-symbols-outlined"> account_circle </span>
      <span @click="openSettingsDialog" class="material-symbols-outlined"> settings </span>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()

    const openProfileDialog = () => {
      store.commit('openProfileDialog')
    }

    const openSettingsDialog = () => {
      store.commit('openSettingsDialog')
    }

    return { openProfileDialog, openSettingsDialog }
  }
}
</script>

<style scoped>
#header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #aaa;
  background: transparent;
}

.left {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.left span {
  padding: 10px;
  font-size: 50px;
  color: green;
}

.title {
  font-weight: bold;
  font-size: 20px;
  color: green;
}

.middle {
  display: flex;
  align-items: center;
  padding: 10px;
}

.middle span {
  padding: 6px;
  margin-right: 10px;
  cursor: pointer;
  background-color: green;
  color: white;
  border: none;
  border-radius: 10px;
}

.right {
  display: flex;
  padding-right: 20px;
}

.right span {
  padding: 10px;
  cursor: pointer;
  font-size: 30px;
}

#user {
  border: 2px solid green;
  border-radius: 10px;
  padding: 10px;
}
</style>

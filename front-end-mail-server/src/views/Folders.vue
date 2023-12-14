<template>
  <div id="folders">
    <div id="add-folder">
      <span class="material-symbols-outlined"> folder </span>
      <input type="text" placeholder="Add new folder" />
      <button>+ Add folder</button>
    </div>

    <div v-for="item in folders" class="item">
      <span class="material-symbols-outlined"> folder </span>
      <h2 @click="goToFolder(item)">
        {{ item }}
      </h2>
      <div id="btns">
        <span class="material-symbols-outlined edit"> edit </span>
        <span class="material-symbols-outlined delete"> delete </span>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

export default {
  setup() {
    const router = useRouter()
    const folders = ref(['College', 'Programming', 'Sports', 'Others'])

    const goToFolder = (item) => {
      router.push({ name: 'folder-details', params: { name: item } })
    }
    return { folders, goToFolder }
  }
}
</script>

<style scoped>
#folders {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}

#add-folder {
  display: flex;
  width: 400px;
  align-items: center;
  background-color: #eee;
  padding: 10px;
  margin: 10px;
  border-radius: 18px;
}

#add-folder span {
  color: #777;
  background-color: transparent;
}

#add-folder input {
  border: none;
  width: 60%;
  padding: 10px;
  outline: none;
  font-size: medium;
  background-color: transparent;
}

#add-folder button {
  padding: 10px;
  border: none;
  border-radius: 10px;
  color: white;
  background-color: green;
  cursor: pointer;
}

.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: gray;
  padding: 10px;
  margin: 10px;
  border-radius: 12px;
}

h2 {
  color: #eee;
  cursor: pointer;
}

.delete {
  background-color: red;
  color: white;
  padding: 5px;
  margin-left: 10px;
  border-radius: 8px;
}

.edit {
  background-color: white;
  color: gray;
  padding: 5px;
  margin-left: 10px;
  border-radius: 8px;
}
</style>

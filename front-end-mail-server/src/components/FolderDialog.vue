<template>
  <dialog open>
    <h2>Add to folder</h2>

    <div class="content">
      <label>Select folder:</label>
      <select>
        <option value="" selected disabled hidden>Folder name</option>
        <option v-for="folder in folders" :value="folder" :key="folder">{{ folder }}</option>
      </select>
    </div>

    <div id="btns">
      <button @click="closeFolder" id="close-btn" type="button">
        <span class="material-symbols-outlined"> cancel </span>
        Close
      </button>

      <button @click="addFolder" id="save-btn">
        <span class="material-symbols-outlined"> add </span>
        Add
      </button>
    </div>
  </dialog>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()

    const folders = ref(['College', 'Programming', 'Sports', 'Others'])

    const closeFolder = () => {
      store.commit('closeFolderDialog')
    }

    const addFolder = () => {}

    return { folders, closeFolder, addFolder }
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
  padding: 0px 10px 10px 10px;
  text-align: left;
  display: grid;
}

label {
  margin: 10px 0;
  font-weight: bold;
  font-size: 18px;
}

select {
  padding: 10px;
  font-weight: bold;
  font-size: 16px;
}

#btns {
  display: flex;
}

button {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin: 10px 5px;
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

#save-btn {
  color: white;
  background: green;
}
</style>

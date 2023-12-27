<template>
  <div id="folders">
    <div id="add-folder">
      <span class="material-symbols-outlined"> folder </span>
      <input type="text" v-model="folderInput" placeholder="Add new folder" />
      <button @click="addFolder">+ Add folder</button>
    </div>

    <div v-for="item in folders" class="item" :key="item">
      <span class="material-symbols-outlined"> folder </span>
      <h2 @click="goToFolder(item)">
        {{ item }}
      </h2>

      <span @click="deleteFolder(item)" class="material-symbols-outlined delete"> delete </span>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import api from '@/api'

export default {
  setup() {
    const store = useStore()
    const router = useRouter()
    const folders = ref([])
    const folderInput = ref('')

    const goToFolder = (item) => {
      router.push({ name: 'folder-details', params: { name: item } })
    }

    const getFolders = async () => {
      await store.dispatch('getFolders', { token: store.getters.token })
      folders.value = store.getters.foldersNames
    }

    const addFolder = async () => {
      if (folderInput.value == null || folderInput.value.trim().length == 0) return

      await api.folder.createFolder(store.getters.token, folderInput.value)

      await getFolders()

      folderInput.value = ''
    }

    const deleteFolder = async (foldername) => {
      await api.folder.deleteFolder(store.getters.token, foldername)
      await getFolders()
    }

    onMounted(async () => {
      await getFolders()
    })

    store.watch(
      (state, getters) => getters.foldersNames,
      () => {
        folders.value = store.getters.foldersNames
      }
    )

    return { folders, folderInput, goToFolder, addFolder, deleteFolder }
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
  cursor: pointer;
}
</style>

<template>
  <div id="folder-details">
    <h1>
      <span class="material-symbols-outlined"> folder </span>
      {{ folderName }}
    </h1>

    <div id="header">
      <SearchBar
        :searchValue="searchValue"
        @update:searchValue="(val) => (searchValue = val)"
        :filterValue="filterValue"
        @update:filterValue="(val) => (filterValue = val)"
        :priorityValue="priorityValue"
        @update:priorityValue="(val) => (priorityValue = val)"
        @onSort="getFolderEmails"
        title="Search mail"
      />

      <span @click="addFolder" class="material-symbols-outlined folder"> create_new_folder </span>

      <span @click="deleteEmails" class="material-symbols-outlined delete"> delete </span>
    </div>

    <div id="page">
      <button @click="getPreviousPage">
        <span class="material-symbols-outlined"> arrow_back_ios </span>

        Previous
      </button>

      <div id="page-info">{{ current }} / {{ total }}</div>

      <button @click="getNextPage">
        <span class="material-symbols-outlined"> arrow_forward_ios </span>

        Next
      </button>
    </div>

    <ListEmails
      :emails="filterEmails"
      :checkedEmails="selectedEmails"
      @selectEmail="handleSelectEmail"
      page="folder-mail-details"
      :key="selectedEmails"
    />
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
import Email from '@/components/Email.vue'
import SearchBar from '@/components/SearchBar.vue'
import ListEmails from '@/components/ListEmails.vue'
import api from '@/api'

export default {
  components: { Email, SearchBar, ListEmails },
  props: ['name'],
  setup(props) {
    const store = useStore()
    const folderName = props.name
    const emails = ref([])
    const selectedEmails = ref([])
    const current = ref(0)
    const total = ref(0)

    const searchValue = ref('')
    const filterValue = ref('')
    const priorityValue = ref('Any Priority')
    const sortValue = ref(0)

    const filterCategory = (email) => {
      switch (filterValue.value) {
        case 'Sender':
          return email.sender
        case 'Subject':
          return email.subject
        case 'Description':
          return email.body
        default:
          return email.subject
      }
    }

    const filterEmails = computed(() => {
      return emails.value.filter((e) => {
        return (
          (priorityValue.value.substring(0, 1) === 'A' ||
            priorityValue.value.substring(0, 1) == `${e.priority}`) &&
          filterCategory(e).toLowerCase().includes(searchValue.value.toLowerCase())
        )
      })
    })

    const getFolderEmails = async (sort, page) => {
      await store.dispatch('getFolderMails', {
        token: store.getters.token,
        sort: sort,
        curFolderName: props.name,
        page: page
      })
      emails.value = store.getters.folderMails
      current.value = store.getters.curFolderMails
      total.value = store.getters.totalFolderMails
      sortValue.value = sort
    }

    const handleSelectEmail = (eamilId) => {
      if (selectedEmails.value.includes(eamilId)) {
        selectedEmails.value = selectedEmails.value.filter((id) => id != eamilId)
      } else {
        selectedEmails.value.push(eamilId)
      }
    }

    const addFolder = async () => {
      store.commit('openFolderDialog', selectedEmails.value)
      await getFolderEmails(0, 0)
    }

    const deleteEmails = async () => {
      const emailService = api.emailService
      await emailService.deleteEmail(store.getters.token, selectedEmails.value)
      await store.dispatch('updateAllFolders', { token: store.getters.token, sort: 0 })
    }

    const getNextPage = async () => {
      await getFolderEmails(sortValue.value, 1)
    }

    const getPreviousPage = async () => {
      await getFolderEmails(sortValue.value, 2)
    }

    onMounted(async () => {
      await getFolderEmails(0, 0)
    })

    store.watch(
      (state, getters) => getters.folderMails,
      () => {
        emails.value = store.getters.folderMails
      }
    )
    store.watch(
      (state, getters) => getters.curFolderMails,
      () => {
        current.value = store.getters.curFolderMails
      }
    )
    store.watch(
      (state, getters) => getters.totalFolderMails,
      () => {
        total.value = store.getters.totalFolderMails
      }
    )

    return {
      folderName,
      emails,
      selectedEmails,
      current,
      total,
      filterEmails,
      searchValue,
      filterValue,
      priorityValue,
      getFolderEmails,
      handleSelectEmail,
      addFolder,
      deleteEmails,
      getNextPage,
      getPreviousPage
    }
  }
}
</script>

<style scoped>
#folder-details {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}

h1 {
  border-bottom: 1px solid gray;
  padding: 10px;
}

#header {
  display: flex;
  justify-content: center;
  align-items: center;
}

.folder {
  background-color: green;
  color: white;
  padding: 12px 5px;
  margin-left: 10px;
  border-radius: 8px;
  cursor: pointer;
}

.delete {
  background-color: red;
  color: white;
  padding: 12px 5px;
  margin: 10px;
  border-radius: 8px;
  cursor: pointer;
}

#page {
  display: flex;
  justify-content: center;
  align-items: center;
}

#page button {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  padding: 6px;
  margin: 0 6px;
  color: white;
  background-color: green;
  border: none;
  border-radius: 12px;
  cursor: pointer;
}

#page-info {
  padding: 6px;
  border-radius: 12px;
  color: white;
  background-color: gray;
  font-weight: bold;
  font-size: 16px;
}
</style>

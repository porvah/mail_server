<template>
  <div id="inbox">
    <div id="header">
      <SearchBar
        :searchValue="searchValue"
        @update:searchValue="(val) => (searchValue = val)"
        :filterValue="filterValue"
        @update:filterValue="(val) => (filterValue = val)"
        :priorityValue="priorityValue"
        @update:priorityValue="(val) => (priorityValue = val)"
        @onSort="getInbox"
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
      page="inbox-detail"
      :key="selectedEmails"
    />
  </div>
</template>

<script>
import { onMounted, computed, ref } from 'vue'
import { useStore } from 'vuex'
import ListEmails from '@/components/ListEmails.vue'
import SearchBar from '@/components/SearchBar.vue'
import api from '@/api'

export default {
  components: { SearchBar, ListEmails },
  setup() {
    const store = useStore()
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

    const getInbox = async (sort, page) => {
      await store.dispatch('getInbox', { token: store.getters.token, sort: sort, page })
      emails.value = store.getters.inboxMails
      current.value = store.getters.curInbox
      total.value = store.getters.totalInbox
      sortValue.value = sort
    }

    const handleSelectEmail = (eamilId) => {
      if (selectedEmails.value.includes(eamilId)) {
        selectedEmails.value = selectedEmails.value.filter((id) => id != eamilId)
      } else {
        selectedEmails.value.push(eamilId)
      }
    }

    const addFolder = () => {
      store.commit('openFolderDialog', selectedEmails.value)
    }

    const deleteEmails = async () => {
      const emailService = api.emailService
      await emailService.deleteEmail(store.getters.token, selectedEmails.value)
      await store.dispatch('updateAllFolders', { token: store.getters.token, sort: 0 })
    }

    const getNextPage = async () => {
      await getInbox(sortValue.value, 1)
    }

    const getPreviousPage = async () => {
      await getInbox(sortValue.value, 2)
    }

    onMounted(async () => {
      await getInbox(0, 0)
    })

    store.watch(
      (state, getters) => getters.inboxMails,
      () => {
        emails.value = store.getters.inboxMails
      }
    )
    store.watch(
      (state, getters) => getters.curInbox,
      () => {
        current.value = store.getters.curInbox
      }
    )
    store.watch(
      (state, getters) => getters.totalInbox,
      () => {
        total.value = store.getters.totalInbox
      }
    )

    return {
      emails,
      selectedEmails,
      current,
      total,
      filterEmails,
      searchValue,
      filterValue,
      priorityValue,
      getInbox,
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
#inbox {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
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

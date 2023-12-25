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

    const searchValue = ref('')
    const filterValue = ref('')
    const priorityValue = ref('Any Priority')

    const filterCategory = (email) => {
      switch (filterValue.value) {
        case 'Sender':
          return email.sender
        case 'Subject':
          return email.subject
        case 'Description':
          return email.body
        default:
          return email.body
      }
    }

    const filterEmails = computed(() => {
      return emails.value.filter((e) =>
        filterCategory(e).toLowerCase().includes(searchValue.value.toLowerCase())
      )
    })

    const getInbox = async (sort) => {
      await store.dispatch('getInbox', { token: store.getters.token, sort: sort })
      emails.value = store.getters.inboxMails
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

    onMounted(async () => {
      await getInbox(0)
    })

    store.watch(
      (state, getters) => getters.inboxMails,
      () => {
        emails.value = store.getters.inboxMails
      }
    )

    return {
      emails,
      selectedEmails,
      filterEmails,
      searchValue,
      filterValue,
      priorityValue,
      getInbox,
      handleSelectEmail,
      addFolder,
      deleteEmails
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
</style>

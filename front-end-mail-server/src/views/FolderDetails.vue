<template>
  <div id="folder-details">
    <h1>
      <span class="material-symbols-outlined"> folder </span>
      {{ folderName }}
    </h1>

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

    <ListEmails :emails="filterEmails" page="sent-detail" />
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

    const getFolderEmails = async (sort) => {
      emails.value = await api.folder.getFolderEmails(store.getters.token, sort, folderName)
    }

    onMounted(async () => {
      await getFolderEmails(0)
    })

    return {
      folderName,
      emails,
      filterEmails,
      searchValue,
      filterValue,
      priorityValue,
      getFolderEmails
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
</style>

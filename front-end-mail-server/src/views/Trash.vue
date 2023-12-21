<template>
  <div id="trash">
    <SearchBar
      :searchValue="searchValue"
      @update:searchValue="(val) => (searchValue = val)"
      :filterValue="filterValue"
      @update:filterValue="(val) => (filterValue = val)"
      :priorityValue="priorityValue"
      @update:priorityValue="(val) => (priorityValue = val)"
      @onSort="getTrash"
      title="Search mail"
    />
    <ListEmails :emails="filterEmails" />
  </div>
</template>

<script>
import { onMounted, computed, ref } from 'vue'
import ListEmails from '@/components/ListEmails.vue'
import SearchBar from '@/components/SearchBar.vue'
import { useStore } from 'vuex'

export default {
  components: { SearchBar, ListEmails },
  setup() {
    const store = useStore()
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

    const getTrash = async (sort) => {
      await store.dispatch('getTrash', { token: store.getters.token, sort: sort })
      emails.value = store.getters.trashMails
    }

    onMounted(async () => {
      await getTrash(0)
    })

    store.watch(
      (state, getters) => getters.trashMails,
      () => {
        emails.value = store.getters.trashMails
      }
    )

    return { emails, filterEmails, searchValue, filterValue, priorityValue, getTrash }
  }
}
</script>

<style scoped>
#trash {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}
</style>

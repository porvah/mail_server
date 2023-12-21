<template>
  <div id="inbox">
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
    <ListEmails :emails="filterEmails" page="inbox-detail" />
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

    const getInbox = async (sort) => {
      await store.dispatch('getInbox', { token: store.getters.token, sort: sort })
      emails.value = store.getters.inboxMails
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

    return { emails, filterEmails, searchValue, filterValue, priorityValue, getInbox }
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
</style>

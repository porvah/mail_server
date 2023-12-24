<template>
  <div id="contacts">
    <h2>Contacts</h2>

    <Contact v-for="contact in contacts" :person="contact" :key="contact" />
  </div>
</template>

<script>
import Contact from '@/components/Contact.vue'
import SearchBar from '@/components/SearchBar.vue'
import { ref, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  components: { SearchBar, Contact },
  setup() {
    const store = useStore()
    const contacts = ref([])

    const getContacts = async () => {
      await store.dispatch('getAllContacts', { token: store.getters.token })
      contacts.value = store.getters.allContacts
    }

    onMounted(async () => {
      await getContacts()
    })

    store.watch(
      (state, getters) => getters.allContacts,
      () => {
        contacts.value = store.getters.allContacts
      }
    )

    return {
      contacts
    }
  }
}
</script>

<style scoped>
#contacts {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}

h2 {
  padding: 10px;
  margin-bottom: 16px;
  border-bottom: 1px solid gray;
}
</style>

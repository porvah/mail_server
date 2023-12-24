<template>
  <div id="contacts">
    <SearchBar title="Search contact" />
    <Contact :person="person" />
    <Contact :person="person" />
    <Contact :person="person" />
    <Contact :person="person" />
  </div>
</template>

<script>
import Contact from '@/components/Contact.vue'
import SearchBar from '@/components/SearchBar.vue'

export default {
  components: { SearchBar, Contact },
  setup() {
    return {
      person: { contactId: 0, name: 'mohamed', emails: ['mohamed@test.com', 'test@test.com'] }
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
</style>

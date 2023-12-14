<template>
  <div id="inbox">
    <SearchBar title="Search mail" />
    <ListEmails />
  </div>
</template>

<script>
import ListEmails from '@/components/ListEmails.vue'
import SearchBar from '@/components/SearchBar.vue'

export default {
  components: { SearchBar, ListEmails }
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

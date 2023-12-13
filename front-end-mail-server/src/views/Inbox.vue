<template>
  <div class="inbox">
    <SearchBar />
  </div>
</template>

<script>
import SearchBar from '@/components/SearchBar.vue'

export default { components: { SearchBar } }
</script>

<style scoped>
.inbox {
  flex: 0.8;
}
</style>

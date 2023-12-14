<template>
  <div class="home">
    <Header />
    <MainBody />
  </div>
</template>

<script>
import Header from '@/components/Header.vue'
import MainBody from '@/components/MainBody.vue'

export default {
  components: { Header, MainBody }
}
</script>

<style scoped></style>

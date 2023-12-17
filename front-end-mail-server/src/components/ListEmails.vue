<template>
  <div id="list-emails"></div>
  <Email v-for="email in emails" :email="email" :key="email.id" />
</template>

<script>
import Email from '@/components/Email.vue'

export default {
  props: ['emails'],
  components: { Email }
}
</script>

<style scoped></style>

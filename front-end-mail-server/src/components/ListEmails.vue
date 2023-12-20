<template>
  <div id="list-emails"></div>
  <div v-for="email in emails" class="email-div" :key="email">
    <input type="checkbox" v-model="checkedEmails" :value="email" class="email-checkbox" />

    <Email :email="email" class="email-item" :key="email.id" />
  </div>
</template>

<script>
import { ref } from 'vue'
import Email from '@/components/Email.vue'

export default {
  props: ['emails'],
  components: { Email },
  setup() {
    const checkedEmails = ref([])

    return { checkedEmails }
  }
}
</script>

<style scoped>
.email-div {
  display: flex;
  align-items: center;
  padding-left: 10px;
}

.email-item {
  width: 95%;
}

.email-checkbox {
  cursor: pointer;
  height: 20px;
  width: 20px;
  background-color: #eee;
}
</style>

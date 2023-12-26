<template>
  <div id="list-emails"></div>
  <div v-for="email in emails" class="email-div" :key="email.id">
    <input
      type="checkbox"
      v-model="selectedEmails"
      @change="$emit('selectEmail', email.id)"
      :value="email.id"
      class="email-checkbox"
    />

    <Email :email="email" :page="page" class="email-item" :key="email.id" />
  </div>
</template>

<script>
import { ref } from 'vue'
import Email from '@/components/Email.vue'

export default {
  props: ['emails', 'page', 'checkedEmails'],
  components: { Email },
  emits: ['selectEmail'],
  setup(props) {
    const selectedEmails = ref(props.checkedEmails)

    return {
      selectedEmails
    }
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

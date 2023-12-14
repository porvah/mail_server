<template>
  <div id="folder-details">
    <h1>
      <span class="material-symbols-outlined"> folder </span>
      {{ folderName }}
    </h1>
    <Email :email="email" />
    <Email :email="email" />
    <Email :email="email" />
  </div>
</template>

<script>
import { ref } from 'vue'
import Email from '@/components/Email.vue'

export default {
  components: { Email },
  props: ['name'],
  setup(props) {
    const folderName = props.name

    const email = ref({
      id: 0,
      sender: 'mohamed@test.com',
      subject: 'ahmed@test.com',
      description: 'A simple email for testing some things!',
      date: '14/12/2023'
    })
    return { folderName, email }
  }
}
</script>

<style scoped>
#folder-details {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}

h1 {
  border-bottom: 1px solid gray;
  padding: 10px;
}
</style>

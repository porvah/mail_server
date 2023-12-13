<template>
  <div id="list-emails"></div>
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
  <Email :email="email" />
</template>

<script>
import { ref } from 'vue'
import Email from '@/components/Email.vue'

export default {
  components: { Email },
  setup() {
    const email = ref({
      sender: 'mohamed',
      subject: 'Programming',
      description:
        'Props attributes are written with a dash - to separate words (kebab-case) in the <template> tag, but kebab-case is not legal in JavaScript. So instead we need to write the attribute names as camelCase in JavaScript, and Vue understands this automatically!',
      date: '12/13/2023 12:16'
    })

    return { email }
  }
}
</script>

<style scoped></style>

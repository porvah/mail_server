<template>
  <div id="inbox">
    <SearchBar title="Search mail" />
    <ListEmails :emails="filterEmails" />
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import ListEmails from '@/components/ListEmails.vue'
import SearchBar from '@/components/SearchBar.vue'

export default {
  components: { SearchBar, ListEmails },
  setup() {
    const searchValue = ref('')
    const emails = ref([
      {
        id: 0,
        sender: 'Ahmed',
        receiver: 'mohamed@test.com',
        subject: 'College',
        description: 'Testing some subjects',
        date: '12/13/2023 12:16'
      },
      {
        id: 1,
        sender: 'mohamed',
        receiver: 'ahmed@test.com',
        subject: 'Programming',
        description:
          'Props attributes are written with a dash - to separate words (kebab-case) in the <template> tag, but kebab-case is not legal in JavaScript. So instead we need to write the attribute names as camelCase in JavaScript, and Vue understands this automatically!',
        date: '12/13/2023 12:16'
      },
      {
        id: 2,
        sender: 'Ali',
        receiver: 'ahmed@test.com',
        subject: 'Important',
        description: "We didn't finish anything and the deadline is coming for us!!!",
        date: '12/13/2023 12:16'
      }
    ])

    const filterEmails = computed(() => {
      return emails.value.filter((e) => e.description.includes(searchValue.value))
    })

    return { emails, filterEmails, searchValue }
  }
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

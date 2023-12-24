<template>
  <div id="email-details">
    <h2 id="subject">{{ email.subject }}</h2>

    <div id="header">
      <div id="info">
        <span @click="addContact" class="material-symbols-outlined"> person_add </span>

        <div id="sender-receiver">
          <h4 id="sender">{{ email.sender }}</h4>

          <div id="receiver">to: {{ email.receiver }}</div>
        </div>
      </div>

      <div id="date">{{ email.date }}</div>
    </div>

    <p>{{ email.body }}</p>

    <AddContactDialog
      v-if="showContactDialog"
      @closeContact="closeContact"
      :emails="[email.sender]"
    />
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import AddContactDialog from '@/components/AddContactDialog.vue'

export default {
  props: ['id'],
  components: { AddContactDialog, AddContactDialog },
  setup(props) {
    const store = useStore()
    const route = useRoute()
    const currentRouteName = computed(() => route.name)
    const emailId = props.id
    const showContactDialog = ref(false)

    const emailList = computed(() => {
      switch (currentRouteName.value) {
        case 'inbox-detail':
          return store.getters.inboxMails
        case 'sent-detail':
          return store.getters.sentMails
        case 'trash-detail':
          return store.getters.trashMails
        case 'draft-detail':
          return store.getters.draftMails
        default:
          return store.getters.inboxMails
      }
    })

    const email = computed(() => {
      return emailList.value.find((e) => e.id == emailId)
    })

    const addContact = () => {
      showContactDialog.value = true
    }

    const closeContact = () => {
      showContactDialog.value = false
    }

    return { email, showContactDialog, addContact, closeContact }
  }
}
</script>

<style scoped>
#email-details {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}

#subject {
  padding: 20px;
  text-align: start;
  border-bottom: 1px solid gray;
}

#header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
}

#info {
  display: flex;
  align-items: center;
  padding: 5px 0;
}

#info span {
  color: white;
  background-color: gray;
  border-radius: 50px;
  padding: 10px;
  margin-right: 10px;
  font-size: 30px;
  cursor: pointer;
}

p {
  margin: 0 10%;
  text-align: start;
}

#receiver {
  color: #444;
}

#date {
  color: #666;
}
</style>

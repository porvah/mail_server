<template>
  <div id="contact">
    <details>
      <summary>
        {{ person.name }}
      </summary>

      <ol class="emails">
        <div v-for="email in person.emails" id="email" :key="email.id">
          <li>
            {{ email }}
          </li>
        </div>
      </ol>
    </details>

    <div>
      <span @click="editContact" class="material-symbols-outlined edit"> edit_square </span>

      <span @click="deleteContact" class="material-symbols-outlined delete"> delete </span>
    </div>

    <AddContactDialog
      v-if="showContactDialog"
      @closeContact="closeContact"
      :emails="JSON.parse(JSON.stringify(person.emails))"
      update="false"
      :contactId="person.contactId"
      :contactName="person.name"
    />
  </div>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'
import AddContactDialog from './AddContactDialog.vue'
import api from '@/api'

export default {
  props: ['person'],
  components: { AddContactDialog },
  setup(props) {
    const store = useStore()
    const showContactDialog = ref(false)

    const closeContact = () => {
      showContactDialog.value = false
    }

    const editContact = async () => {
      showContactDialog.value = true
    }

    const deleteContact = async () => {
      await api.contactsService.deleteContact(store.getters.token, [props.person.contactId])
      await store.dispatch('getAllContacts', { token: store.getters.token })
    }

    return { showContactDialog, closeContact, editContact, deleteContact }
  }
}
</script>

<style scoped>
#contact {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #bbb;
  padding: 8px 18px;
  margin: 8px 0;
  border-radius: 10px;
}

details {
  align-items: start;
}

summary {
  font-weight: bold;
  font-size: 18px;
  text-align: left;
}

.delete,
.edit {
  padding: 5px;
  margin-left: 10px;
  border-radius: 8px;
}

.edit {
  background-color: green;
  color: white;
}

.delete {
  background-color: red;
  color: white;
}

ol,
li {
  margin: 8px;
}

span {
  cursor: pointer;
}
</style>

<template>
  <div id="contact">
    <details>
      <summary>
        {{ person.name }}
      </summary>

      <ol class="emails">
        <div v-for="email in person.emails" id="email" :key="email">
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
  </div>
</template>

<script>
import api from '@/api'
import { useStore } from 'vuex'

export default {
  props: ['person'],
  setup(props) {
    const store = useStore()

    const editContact = async () => {
      // await api.contactsService.updateContact(store.getters.token, props.person.contactId, )
    }

    const deleteContact = async () => {
      await api.contactsService.deleteContact(store.getters.token, [props.person.contactId])
    }

    return { editContact, deleteContact }
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

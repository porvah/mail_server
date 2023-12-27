<template>
  <dialog open>
    <h2>Add Contact</h2>
    <form action="#" @submit.prevent="">
      <label>Name:</label>
      <input type="text" v-model="contactName" required />

      <div id="contact-email">
        <label>Contact emails:</label>

        <button @click="addReceiver" id="add-receiver-btn" type="button">
          <span class="material-symbols-outlined"> add </span>
          Add
        </button>
      </div>
      <input type="text" v-model="emailField" />

      <div id="receivers">
        <div
          v-for="receiver in contactEmails"
          @click="removeReceiver(receiver)"
          class="receiver"
          :key="receiver"
        >
          {{ receiver }}
          <span class="material-symbols-outlined"> delete </span>
        </div>
      </div>

      <div v-if="errorMsg" id="error">{{ errorMsg }}</div>

      <div id="btns">
        <button @click="$emit('closeContact')" id="cancel-btn" type="button">
          <span class="material-symbols-outlined"> cancel </span>
          Cancel
        </button>

        <button type="submit" @click="submitContact" id="add-btn">
          <span class="material-symbols-outlined"> add </span>
          Add
        </button>
      </div>
    </form>
  </dialog>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'
import api from '@/api'

export default {
  props: ['emails', 'update', 'contactId', 'contactName'],
  emits: ['closeContact'],
  setup(props, ctx) {
    const store = useStore()
    const contactName = ref(props.contactName ? props.contactName : '')
    const emailField = ref('')
    const errorMsg = ref('')

    const contactEmails = ref(props.emails)

    const validName = () => {
      if (contactName.value == null || contactName.value == '' || contactName.value.length === 0) {
        errorMsg.value = 'Contact name can NOT be empty!'
        return false
      }
      return true
    }

    const validEmails = () => {
      if (contactEmails.value.length < 1) {
        errorMsg.value = 'Contact emails can NOT be empty!'
        return false
      }
      return true
    }

    const updateContact = async () => {
      try {
        await api.contactsService.updateContact(
          store.getters.token,
          props.contactId,
          contactName.value,
          contactEmails.value
        )

        ctx.emit('closeContact')
      } catch (e) {
        errorMsg.value = JSON.parse(e).msg
      }
    }

    const addNewContact = async () => {
      try {
        await api.contactsService.createContact(
          store.getters.token,
          contactName.value,
          contactEmails.value
        )

        ctx.emit('closeContact')
      } catch (e) {
        errorMsg.value = JSON.parse(e).msg
      }
    }

    const submitContact = async () => {
      if (!validName() || !validEmails()) return

      if (props.update) {
        await updateContact()
      } else {
        await addNewContact()
      }
    }

    const addReceiver = () => {
      if (emailField.value && emailField.value.length > 0) {
        const newEmail = emailField.value.split('@')
        if (newEmail.length != 2 || newEmail[0] === '' || newEmail[1] === '') {
          errorMsg.value = 'Invalid email.'
          return
        }
        if (!contactEmails.value.includes(emailField.value)) {
          contactEmails.value.push(emailField.value)
        }
        emailField.value = ''
        errorMsg.value = ''
      }
    }

    const removeReceiver = (receiver) => {
      contactEmails.value = contactEmails.value.filter((r) => receiver != r)
    }

    return {
      contactName,
      emailField,
      contactEmails,
      errorMsg,
      submitContact,
      addReceiver,
      removeReceiver
    }
  }
}
</script>

<style scoped>
dialog {
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -25vh;
  margin-top: -25vh;
  width: 400px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  border: 1px solid gray;
  border-radius: 12px;
}

h2 {
  border-bottom: 1px solid gray;
  margin-bottom: 6px;
  padding-bottom: 4px;
}

form {
  text-align: left;
  margin: 0 auto;
}

label {
  color: #555;
  display: inline-block;
  margin: 10px 0;
  font-size: 14px;
  letter-spacing: 1px;
  font-weight: bold;
  background: white;
}

input {
  display: block;
  padding: 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  color: #555;
  background: white;
}

#error {
  color: red;
  padding: 10px;
  text-align: center;
  font-weight: bold;
}

#btns {
  display: flex;
  justify-content: space-around;
}

button {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin: 15px 15px 0 15px;
  padding: 10px;
  width: 40%;
  border: none;
  border-radius: 12px;
  font-weight: bold;
  cursor: pointer;
}

#add-btn {
  color: white;
  background: green;
}

#cancel-btn {
  color: green;
  background: white;
  border: 1px solid green;
}

#contact-email {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 10px;
}

#contact-email button {
  width: 25%;
  color: white;
  background-color: rgb(21, 141, 21);
}
#receivers {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  align-items: center;
}

.receiver {
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
  color: white;
  background-color: gray;
  border-radius: 12px;
  padding: 10px;
  margin: 10px 5px;
}
</style>

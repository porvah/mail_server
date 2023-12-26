<template>
  <div id="email-draft">
    <h2>Draft Email</h2>
    <form action="#" @submit.prevent="">
      <label>From:</label>
      <input type="text" :value="emailFrom" disabled />

      <div id="email-to">
        <label>To:</label>
        <button @click="addReceiver" id="add-receiver-btn" type="button">
          <span class="material-symbols-outlined"> add </span>
          Add
        </button>
      </div>
      <input type="text" v-model="emailTo" />

      <div id="receivers">
        <div
          v-for="receiver in receivers"
          @click="removeReceiver(receiver)"
          class="receiver"
          :key="receiver"
        >
          {{ receiver }}
          <span class="material-symbols-outlined"> delete </span>
        </div>
      </div>

      <label>Subject:</label>
      <input type="text" v-model="emailSubject" required />

      <label>Description:</label>
      <textarea v-model="emailDescription" required />

      <label>Priority:</label>
      <div id="priority">
        <div v-for="p in priorityChoices" class="element" :key="p">
          <input type="radio" v-model="priorityChose" name="priority" :value="p" />
          <label>{{ p }}</label>
        </div>
      </div>

      <div v-if="errorMsg" id="error">{{ errorMsg }}</div>

      <div id="btns">
        <button type="submit" @click="updateDraftEmail" id="draft-btn">
          <span class="material-symbols-outlined"> edit_document </span>
          Draft
        </button>

        <button type="submit" @click="submitDraftEmail" id="send-btn">
          <span class="material-symbols-outlined"> send </span>
          Send
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import EmailServiceAdapter from '@/models/EmailServiceAdapter'
import api from '@/api'

export default {
  props: ['id'],
  setup(props) {
    const store = useStore()
    const router = useRouter()
    const emailId = props.id
    const email = store.getters.draftMails.find((e) => e.id == emailId)

    const emailFrom = email.sender
    const emailTo = ref(email.receiver)
    const emailSubject = ref(email.subject)
    const emailDescription = ref(email.body)
    const priorityChose = ref(email.priority)
    const receivers = ref([])
    const errorMsg = ref('')

    const emailAdapter = new EmailServiceAdapter(api.emailService)
    const priorityChoices = computed(() => ['1', '2', '3', '4', '5'])

    const addReceiver = () => {
      if (emailTo.value && emailTo.value.length > 0) {
        const newEmail = emailTo.value.split('@')
        if (newEmail.length != 2 || newEmail[0] === '' || newEmail[1] === '') {
          errorMsg.value = 'Invalid email.'
          return
        }
        if (!receivers.value.includes(emailTo.value)) {
          receivers.value.push(emailTo.value)
        }
        emailTo.value = ''
      }
    }

    const removeReceiver = (receiver) => {
      receivers.value = receivers.value.filter((r) => receiver != r)
    }

    const updateEmail = () => {
      email.receiver = receivers.value
      email.subject = emailSubject.value
      email.body = emailDescription.value
      email.priority = Number(priorityChose.value)
    }

    const submitDraftEmail = async () => {
      if (!validateInput(false)) return

      updateEmail()

      try {
        await emailAdapter.submitDraftEmail(email)
        router.push('/home/draft')
      } catch (e) {
        errorMsg.value = JSON.parse(e).msg
      }
    }

    const updateDraftEmail = async () => {
      if (!validateInput(true)) return
      updateEmail()

      try {
        await emailAdapter.updateDraftEmail(email)
        router.push('/home/draft')
      } catch (e) {
        errorMsg.value = JSON.parse(e).msg
      }
    }

    const validateInput = (emptyReceivers) => {
      if (emailSubject.value.length < 1) {
        errorMsg.value = 'Email subject can NOT be empty!'
        return false
      }
      if (emailDescription.value.length < 1) {
        errorMsg.value = 'Email description can NOT be empty!'
        return false
      }
      if (!emptyReceivers && receivers.value.length < 1) {
        errorMsg.value = 'Receivers field can NOT be empty!'
        return false
      }
      return true
    }

    return {
      emailFrom,
      emailTo,
      emailSubject,
      emailDescription,
      receivers,
      priorityChose,
      priorityChoices,
      errorMsg,
      addReceiver,
      removeReceiver,
      submitDraftEmail,
      updateDraftEmail
    }
  }
}
</script>

<style scoped>
#email-draft {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}

h2 {
  border-bottom: 1px solid gray;
  margin: 6px;
  padding: 6px;
}

form {
  text-align: left;
  margin: 0 auto;
}

label {
  color: #555;
  display: inline-block;
  margin: 10px;
  font-size: 16px;
  letter-spacing: 1px;
  font-weight: bold;
}

input,
textarea {
  display: block;
  padding: 10px;
  margin: 0 10px;
  width: 98%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  border-radius: 10px;
  color: #555;
  background: white;
}

#email-to {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 10px;
}

#email-to button {
  width: 25%;
  color: white;
  background-color: rgb(21, 141, 21);
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

#priority {
  display: flex;
  justify-content: space-around;
  padding: 10px 0;
  align-items: center;
}

#receivers {
  display: flex;
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

.element label {
  font-size: 16px;
}

.element input {
  cursor: pointer;
  width: 20px;
  height: 20px;
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
  font-size: 16px;
  cursor: pointer;
}

#send-btn {
  color: white;
  background: green;
}

#draft-btn {
  color: white;
  background-color: rgb(123, 78, 78);
}
</style>

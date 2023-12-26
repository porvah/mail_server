<template>
  <dialog open>
    <form action="#" @submit.prevent="">
      <div id="all">
        <div id="left">
          <h2>New Email</h2>
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
        </div>
        <div id="right">
          <label> Attachments </label>

          <div class="dropzone-container" @dragover="dragover" @dragleave="dragleave" @drop="drop">
            <input
              type="file"
              multiple
              name="file"
              id="fileInput"
              class="hidden-input"
              @change="onChange"
              ref="file"
              accept=".pdf,.jpg,.jpeg,.png"
            />

            <label for="fileInput" class="file-label">
              <div v-if="isDragging">Release to drop files here.</div>

              <div v-else>Drop files here or <u>click here</u> to upload.</div>
            </label>

            <div class="preview-container mt-4" v-if="files.length">
              <div v-for="file in files" :key="file.name" class="preview-card">
                <div>
                  <img class="preview-img" :src="generateThumbnail(file)" />

                  <p :title="file.name">
                    {{ makeName(file.name) }}
                  </p>
                </div>

                <div>
                  <button
                    class="ml-2"
                    type="button"
                    @click="remove(files.indexOf(file))"
                    title="Remove file"
                  >
                    <b>&times;</b>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="errorMsg" id="error">{{ errorMsg }}</div>
      </div>
      <div id="btns">
        <button @click="closeCompose" id="cancel-btn" type="button">
          <span class="material-symbols-outlined"> cancel </span>
          Cancel
        </button>

        <button type="submit" @click="draftEmail" id="draft-btn">
          <span class="material-symbols-outlined"> edit_document </span>
          Draft
        </button>

        <button type="submit" @click="sendEmail" id="send-btn">
          <span class="material-symbols-outlined"> send </span>
          Send
        </button>
      </div>
    </form>
  </dialog>
</template>

<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import EmailModel from '../models/EmailModel'
import api from '@/api'
import EmailServiceAdapter from '@/models/EmailServiceAdapter'

export default {
  setup() {
    const store = useStore()

    const emailFrom = store.getters.user.email
    const emailTo = ref('')
    const emailSubject = ref('')
    const emailDescription = ref('')
    const priorityChose = ref('1 (Low)')
    const receivers = ref([])
    const errorMsg = ref('')
    const isDragging = ref(false)
    const files = ref([])
    const file = ref(null)

    const emailAdapter = new EmailServiceAdapter(api.emailService)
    const priorityChoices = computed(() => ['1 (Low)', '2', '3', '4', '5 (High)'])

    const closeCompose = () => {
      store.commit('closeComposeDialog')
    }

    const addReceiver = () => {
      if (emailTo.value && emailTo.value.length > 0) {
        if (!receivers.value.includes(emailTo.value)) {
          receivers.value.push(emailTo.value)
        }
        emailTo.value = ''
      }
    }

    const removeReceiver = (receiver) => {
      receivers.value = receivers.value.filter((r) => receiver != r)
    }

    const createEmail = () => {
      const email = new EmailModel()
      return email
        .addSender(emailFrom)
        .addReceiver(receivers.value)
        .addSubject(emailSubject.value)
        .addBody(emailDescription.value)
        .addPriority(Number(priorityChose.value.slice(0, 1)))
        .build()
    }

    const sendEmail = async () => {
      if (!validateInput(false)) return

      const email = createEmail()
      try {
        await emailAdapter.sendEmail(email)
        await store.dispatch('getSent', { token: store.getters.token, sort: 0, page: 0 })
        closeCompose()
      } catch (e) {
        errorMsg.value = JSON.parse(e).msg
      }
    }

    const draftEmail = async () => {
      if (!validateInput(true)) return

      const email = createEmail()
      try {
        await emailAdapter.draftEmail(email)
        await store.dispatch('getDraft', { token: store.getters.token, sort: 0, page: 0 })
        closeCompose()
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

    const onChange = () => {
      files.value = [...file.value.files]
    }

    const generateThumbnail = (f) => {
      let fileSrc = URL.createObjectURL(f)
      setTimeout(() => {
        URL.revokeObjectURL(fileSrc)
      }, 1000)
      return fileSrc
    }

    const makeName = (name) => {
      return (
        name.split('.')[0].substring(0, 3) + '...' + name.split('.')[name.split('.').length - 1]
      )
    }

    const remove = (i) => {
      files.value.splice(i, 1)
    }

    const dragover = (e) => {
      e.preventDefault()
      isDragging.value = true
    }

    const dragleave = () => {
      isDragging.value = false
    }

    const drop = (e) => {
      e.preventDefault()
      file.value.files = e.dataTransfer.files
      onChange()
      isDragging.value = false
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
      isDragging,
      files,
      file,
      closeCompose,
      addReceiver,
      removeReceiver,
      sendEmail,
      draftEmail,
      onChange,
      generateThumbnail,
      makeName,
      remove,
      dragover,
      dragleave,
      drop
    }
  }
}
</script>

<style scoped>
dialog {
  position: absolute;
  left: 35%;
  top: 30%;
  margin-left: -25vh;
  margin-top: -25vh;
  width: 800px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  border: 1px solid gray;
  border-radius: 12px;
}

#all {
  display: flex;
}

#left {
  width: 700px;
  padding-right: 12px;
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

input,
textarea {
  display: block;
  padding: 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
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

#btns {
  display: flex;
  justify-content: space-around;
}

#priority {
  display: flex;
  justify-content: space-between;
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
}

#error {
  color: red;
  padding: 10px;
  text-align: center;
  font-weight: bold;
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

#send-btn {
  color: white;
  background: green;
}

#draft-btn {
  color: white;
  background-color: rgb(123, 78, 78);
}

#cancel-btn {
  color: green;
  background: white;
  border: 1px solid green;
}
.main {
  display: flex;
  flex-grow: 1;
  align-items: center;
  height: 100vh;
  justify-content: center;
  text-align: center;
}
.dropzone-container {
  padding: 4rem;
  background: #f7fafc;
  border: 1px solid #e2e8f0;
}
.hidden-input {
  opacity: 0;
  overflow: hidden;
  position: absolute;
  width: 1px;
  height: 1px;
}
.file-label {
  font-size: 20px;
  display: block;
  cursor: pointer;
}
.preview-container {
  display: flex;
  margin-top: 2rem;
}
.preview-card {
  display: flex;
  border: 1px solid #a2a2a2;
  padding: 5px;
  margin-left: 5px;
}
.preview-img {
  width: 50px;
  height: 50px;
  border-radius: 5px;
  border: 1px solid #a2a2a2;
  background-color: #a2a2a2;
}
</style>

<template>
  <div id="email-draft">
    <h2>Draft Email</h2>
    <form action="#" @submit.prevent="">
      <label>From:</label>
      <input type="text" :value="emailFrom" disabled />

      <div id="email-to">
        <label>To:</label>
        <select v-if="allContacts.length" id="my-contact" multiple v-model="receivers">
          <option v-for="contact in allContacts" :key="contact">
            {{ contact }}
          </option>
        </select>

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

      <div id="attach-wrap">
        <div v-for="file in attachments" class="attachments" :key="file.fileName">
          <button @click="getFileDownloadUrl(file)">
            <span class="material-symbols-outlined"> download </span>

            {{ file.fileName }}
          </button>
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
import { computed, onMounted, ref } from 'vue'
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
    const isDragging = ref(false)
    const files = ref([])
    const file = ref(null)
    const allContacts = ref([])
    const selectedContacts = ref([])
    const attachments = ref([])

    const emailAdapter = new EmailServiceAdapter()
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
      selectedContacts.value = selectedContacts.value.filter((r) => receiver != r)
    }

    const updateEmail = () => {
      email.receiver = receivers.value
      email.subject = emailSubject.value
      email.body = emailDescription.value
      email.priority = Number(priorityChose.value)

      email.files = files.value
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

    const getAllContacts = () => {
      const folders = store.getters.allContacts
      const contactsSet = new Set()

      for (let i = 0; i < folders.length; ++i) {
        for (let j = 0; j < folders[i].emails.length; ++j) {
          contactsSet.add(folders[i].emails[j])
        }
      }
      allContacts.value = Array.from(contactsSet)
    }

    const getAttachments = async () => {
      const response = await api.attachmentService.getAttachments(store.getters.token, emailId)
      let files = []
      for (let i = 0; i < response.length; ++i) {
        files[i] = { bytes: response[i].bytes, fileName: response[i].name }
      }

      attachments.value = files
    }

    const getFileDownloadUrl = (file) => {
      const unitArray = new Uint8Array(
        atob(file.bytes)
          .split('')
          .map((c) => c.charCodeAt(0))
      )
      const blob = new Blob([unitArray], { type: file.fileName })

      const downlaodLink = document.createElement('a')
      downlaodLink.href = window.URL.createObjectURL(blob)

      downlaodLink.download = file.fileName

      document.body.appendChild(downlaodLink)

      downlaodLink.click()

      document.body.removeChild(downlaodLink)
    }

    getAllContacts()

    onMounted(async () => {
      await getAttachments()
    })

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
      allContacts,
      selectedContacts,
      attachments,
      addReceiver,
      removeReceiver,
      submitDraftEmail,
      updateDraftEmail,
      onChange,
      generateThumbnail,
      makeName,
      remove,
      dragover,
      dragleave,
      drop,
      getFileDownloadUrl
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

#my-contact {
  border: none;
  background-color: gray;
  color: white;
  padding: 10px;
  border-radius: 6px;
}

#my-contact option {
  background-color: white;
  color: black;
  padding: 3px;
  margin-bottom: 5px;
  border-radius: 6px;
}

#attach-wrap {
  /* display: flex; */
  justify-content: center;
  align-items: center;
}

.attachments button {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px;
  margin: 10px;
  color: white;
  background-color: green;
  border: none;
  border-radius: 12px;
  cursor: pointer;
}

.attachments button span {
  padding-right: 8px;
}
</style>

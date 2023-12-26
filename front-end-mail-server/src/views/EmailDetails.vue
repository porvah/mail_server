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

    <div id="attach-wrap">
      <div v-for="file in attachments" class="attachments" :key="file.fileName">
        <button @click="getFileDownloadUrl(file)">
          <span class="material-symbols-outlined"> download </span>

          {{ file.fileName }}
        </button>
      </div>
    </div>

    <AddContactDialog
      v-if="showContactDialog"
      @closeContact="closeContact"
      :emails="[email.sender]"
    />
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import AddContactDialog from '@/components/AddContactDialog.vue'
import api from '@/api'

export default {
  props: ['id'],
  components: { AddContactDialog, AddContactDialog },
  setup(props) {
    const store = useStore()
    const route = useRoute()
    const currentRouteName = computed(() => route.name)
    const emailId = props.id
    const showContactDialog = ref(false)
    const attachments = ref([])

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
        case 'folder-mail-details':
          return store.getters.folderMails
        default:
          return store.getters.inboxMails
      }
    })

    const email = computed(() => {
      return emailList.value.find((e) => e.id == emailId)
    })

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

    const addContact = () => {
      showContactDialog.value = true
    }

    const closeContact = () => {
      showContactDialog.value = false
    }

    onMounted(async () => {
      await getAttachments()
    })

    return { email, attachments, showContactDialog, addContact, closeContact, getFileDownloadUrl }
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

#attach-wrap {
  display: flex;
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

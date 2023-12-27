<template>
  <div id="email">
    <!-- <span class="material-symbols-outlined"> star </span> -->

    <span
      v-if="page != 'sent-detail' && page != 'trash-detail'"
      @click="addFolder"
      class="material-symbols-outlined"
    >
      create_new_folder
    </span>

    <span
      v-if="page == 'trash-detail'"
      @click="restoreMail"
      class="material-symbols-outlined restore"
    >
      settings_backup_restore
    </span>

    <h3 @click="goToEmail" class="sender">{{ sender }}</h3>

    <h4 @click="goToEmail" class="subject">{{ subject }}:</h4>

    <p @click="goToEmail" class="description">{{ body }}</p>

    <div class="date">{{ sentDate }}</div>

    <span @click="deleteEmail" class="material-symbols-outlined delete"> delete </span>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import api from '@/api'

export default {
  props: ['email', 'page'],
  setup(props) {
    const store = useStore()
    const router = useRouter()

    const goToEmail = () => {
      router.push({ name: props.page, params: { id: props.email.id } })
    }

    const restoreMail = async () => {
      await api.emailService.restoreMail(store.getters.token, [props.email.id])
      await store.dispatch('updateAllFolders', { token: store.getters.token })
    }

    const addFolder = () => {
      store.commit('openFolderDialog', [props.email.id])
    }

    const deleteEmail = async () => {
      const emailService = api.emailService
      await emailService.deleteEmail(store.getters.token, [props.email.id])
      await store.dispatch('updateAllFolders', { token: store.getters.token, sort: 0 })
    }

    return {
      sender: props.email.sender,
      receiver: props.email.receiver,
      subject: props.email.subject,
      body: props.email.body,
      sentDate: props.email.sentDate,
      goToEmail,
      addFolder,
      restoreMail,
      deleteEmail
    }
  }
}
</script>

<style scoped>
#email {
  display: flex;
  align-items: center;
  padding: 10px;
}

.restore {
  color: white;
  background-color: green;
  padding: 4px;
  border-radius: 10px;
}

.sender,
.subject,
.date,
.description {
  padding: 5px;
  cursor: pointer;
}

.description {
  display: inherit;
  white-space: nowrap;
  /* width: 100vh; */
  text-overflow: ellipsis;
  overflow: hidden;
}

.delete {
  background-color: red;
  color: white;
  padding: 5px;
  margin-left: 10px;
  border-radius: 8px;
}

span {
  cursor: pointer;
}
</style>

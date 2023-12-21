<template>
  <div id="email">
    <!-- <span class="material-symbols-outlined"> star </span> -->

    <span @click="addFolder" class="material-symbols-outlined"> create_new_folder </span>

    <h3 @click="goToEmail" class="sender">@{{ sender }}</h3>

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
  props: ['email'],
  setup(props) {
    const store = useStore()
    const router = useRouter()

    const goToEmail = () => {
      router.push({ name: 'email-details', params: { id: props.email.id } })
    }

    const addFolder = () => {
      store.commit('openFolderDialog')
    }

    const deleteEmail = async () => {
      const emailService = api.emailService
      await emailService.deleteEmail(store.getters.token, [props.email.id])
    }

    return {
      sender: props.email.sender,
      receiver: props.email.receiver,
      subject: props.email.subject,
      body: props.email.body,
      sentDate: props.email.sentDate,
      goToEmail,
      addFolder,
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

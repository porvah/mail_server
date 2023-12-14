<template>
  <div id="email">
    <span class="material-symbols-outlined"> star </span>

    <span class="material-symbols-outlined"> label_important </span>

    <h3 @click="goToEmail" class="sender">@{{ sender }}</h3>

    <h4 @click="goToEmail" class="subject">{{ subject }}:</h4>

    <p @click="goToEmail" class="description">{{ description }}</p>

    <div class="date">{{ date }}</div>

    <span class="material-symbols-outlined delete"> delete </span>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  props: ['email'],
  setup(props) {
    const router = useRouter()
    const goToEmail = () => {
      router.push({ name: 'email-details', params: { id: props.email.id } })
    }

    return {
      sender: props.email.sender,
      subject: props.email.subject,
      description: props.email.description,
      date: props.email.date,
      goToEmail
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

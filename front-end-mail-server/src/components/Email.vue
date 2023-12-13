<template>
  <div id="email">
    <span class="material-symbols-outlined"> star </span>
    <span class="material-symbols-outlined"> label_important </span>
    <h3 class="sender">@{{ sender }}</h3>
    <h4 class="subject">{{ subject }}:</h4>
    <p class="description">{{ description }}</p>
    <div class="date">{{ date }}</div>
    <span class="material-symbols-outlined delete"> delete </span>
  </div>
</template>

<script>
export default {
  props: ['email'],
  setup(props) {
    return {
      sender: props.email.sender,
      subject: props.email.subject,
      description: props.email.description,
      date: props.email.date
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
.date .description {
  padding: 0 5px;
  /* background-color: gray; */
  /* color: white; */
}

/* .subject,
.date {
  padding: 5px;
  background-color: rgb(64, 59, 59);
  color: white;
} */

.description {
  display: inherit;
  white-space: nowrap;
  width: 100vh;
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

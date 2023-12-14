<template>
  <dialog open>
    <h2>New Email</h2>
    <form @submit.prevent>
      <label>From:</label>
      <input type="text" value="test@test.test" disabled />

      <label>To:</label>
      <input type="text" v-model="emailTo" required />

      <label>Subject:</label>
      <input type="text" v-model="emailSubject" required />

      <label>Description:</label>
      <textarea v-model="emailDescription" required />

      <div id="btns">
        <button @click="closeCompose" id="cancel-btn" type="button">
          <span class="material-symbols-outlined"> cancel </span>
          Cancel
        </button>

        <button type="submit" id="send-btn">
          <span class="material-symbols-outlined"> send </span>
          Send
        </button>
      </div>
    </form>
  </dialog>
</template>

<script>
import { ref } from 'vue'

export default {
  setup() {
    const emailTo = ref('')
    const emailSubject = ref('')
    const emailDescription = ref('')

    const closeCompose = () => {}

    return { emailTo, emailSubject, emailDescription, closeCompose }
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
  width: 300px;
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

#send-btn {
  color: white;
  background: green;
}

#cancel-btn {
  color: green;
  background: white;
  border: 1px solid green;
}
</style>

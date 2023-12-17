<template>
  <dialog open>
    <h2>New Email</h2>
    <form @submit.prevent>
      <label>From:</label>
      <input type="text" value="test@test.test" disabled />

      <div id="email-to">
        <label>To:</label>
        <button @click="addReceiver" id="add-receiver-btn" type="button">
          <span class="material-symbols-outlined"> add </span>
          Add
        </button>
      </div>
      <input type="text" v-model="emailTo" required />

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
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()

    const emailTo = ref('')
    const emailSubject = ref('')
    const emailDescription = ref('')
    const priorityChose = ref('1 (Low)')
    const receivers = ref([])

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

    return {
      emailTo,
      emailSubject,
      emailDescription,
      receivers,
      priorityChose,
      priorityChoices,
      closeCompose,
      addReceiver,
      removeReceiver
    }
  }
}
</script>

<style scoped>
dialog {
  position: absolute;
  left: 45%;
  top: 40%;
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

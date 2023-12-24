<template>
  <dialog open>
    <h2>Add Contact</h2>
    <form action="#" @submit.prevent="">
      <label>name:</label>
      <input type="text" v-model="contactName" required />

      <label>Contact email:</label>
      <input type="text" :value="contactEmail" disabled />

      <div id="btns">
        <button @click="$emit('closeContact')" id="cancel-btn" type="button">
          <span class="material-symbols-outlined"> cancel </span>
          Cancel
        </button>

        <button type="submit" @click="addContact" id="add-btn">
          <span class="material-symbols-outlined"> add </span>
          Add
        </button>
      </div>
    </form>
  </dialog>
</template>

<script>
import api from '@/api'
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
// import api from '@/api'

export default {
  props: ['emails'],
  emits: ['closeContact'],
  setup(props, ctx) {
    const store = useStore()
    const contactName = ref('')
    const contactEmail = ref(props.emails)

    const addContact = async () => {
      if (contactName.value == null || contactName.value == '' || contactName.value.length === 0)
        return

      await api.contactsService.createContact(store.getters.token, contactName.value, [
        contactEmail.value
      ])

      ctx.emit('closeContact')
    }

    return { contactName, contactEmail, addContact }
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

input {
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

#add-btn {
  color: white;
  background: green;
}

#cancel-btn {
  color: green;
  background: white;
  border: 1px solid green;
}
</style>

<template>
  <div class="side-bar">
    <button class="compose">
      <span class="material-symbols-outlined"> stylus </span>
      Compose
    </button>
    <div
      v-for="option in options"
      @click="chooseOption(option.title)"
      :class="{ option, activeOption: option.title === activeOption }"
      :key="option"
    >
      <span class="material-symbols-outlined"> {{ option.icon }} </span>
      <h3 class="option-title">{{ option.title }}</h3>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'

export default {
  setup() {
    const activeOption = ref('Inbox')

    const options = computed(() => [
      { title: 'Inbox', icon: 'inbox' },
      {
        title: 'Sent',
        icon: 'send'
      },
      {
        title: 'Contacts',
        icon: 'contacts'
      },
      {
        title: 'Draft',
        icon: 'draft'
      },
      {
        title: 'Starred',
        icon: 'star'
      },
      {
        title: 'Folders',
        icon: 'folder_open'
      },
      {
        title: 'Trash',
        icon: 'delete'
      }
    ])

    const chooseOption = (op) => {
      activeOption.value = op
    }

    return { activeOption, options, chooseOption }
  }
}
</script>

<style scoped>
.side-bar {
  text-align: left;
  flex: 0.3;
  max-width: 200px;
  padding-right: 20px;
}

.compose {
  display: flex;
  align-items: center;
  margin: 15px 0 10px 15px;
  padding: 15px 25px;
  border-radius: 30px;
  color: green;
  background: white;
  cursor: pointer;
  outline: none;
  border: none;
  box-shadow: 0px 3px 5px -3px rgba(0, 0, 0, 0.7);
}

.compose span {
  background: white;
  margin-right: 5px;
}

.option {
  display: flex;
  align-items: center;
  height: 40px;
  padding: 0 10px;
  margin: 5px 0;
  border-radius: 0 20px 20px 0;
  cursor: pointer;
  color: #666;
}

.option span {
  padding: 5px;
}

.option h3 {
  flex: 1;
  margin-left: 10px;
  font-size: 14px;
  font-weight: 400;
}

.option:hover,
.option:hover h3,
.option:hover span,
.activeOption,
.activeOption h3,
.activeOption span {
  color: green;
  background-color: rgb(177, 255, 228);
}

.activeOption h3,
.option:hover h3 {
  font-weight: 600;
}
</style>

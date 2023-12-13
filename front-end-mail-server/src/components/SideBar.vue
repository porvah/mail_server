<template>
  <div id="side-bar">
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
      <h3 class="">{{ option.count }}</h3>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

export default {
  setup() {
    const router = useRouter()
    const activeOption = ref('Inbox')

    const options = computed(() => [
      {
        title: 'Inbox',
        icon: 'inbox',
        count: 20
      },
      {
        title: 'Sent',
        icon: 'send',
        count: 12
      },
      {
        title: 'Contacts',
        icon: 'contacts',
        count: 3
      },
      {
        title: 'Draft',
        icon: 'draft',
        count: 2
      },
      {
        title: 'Starred',
        icon: 'star',
        count: 4
      },
      {
        title: 'Folders',
        icon: 'folder_open',
        count: 0
      },
      {
        title: 'Trash',
        icon: 'delete',
        count: 1
      }
    ])

    const chooseOption = (op) => {
      activeOption.value = op
      router.push('/home/' + op)
    }

    return { activeOption, options, chooseOption }
  }
}
</script>

<style scoped>
#side-bar {
  text-align: left;
  flex: 0.2;
  width: 200px;
  padding-right: 20px;
  border-right: 1px solid gray;
  height: 100%;
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

.compose:hover,
.compose:hover span {
  background-color: green;
  color: white;
  transition: 0.6s;
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
  transition: 0.5s;
}

.option span {
  padding: 5px;
  transition: 0.5s;
}

.option h3 {
  flex: 1;
  margin-left: 10px;
  font-size: 14px;
  font-weight: 400;
  transition: 0.5s;
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

.option:hover h3,
.option:hover span,
.activeOption h3,
.activeOption span {
  color: green;
  background-color: rgb(177, 255, 228);
  translate: 10px 0;
  transition: 0.3s;
}
</style>

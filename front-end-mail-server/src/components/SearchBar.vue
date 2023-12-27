<template>
  <div id="search-bar">
    <div class="left">
      <span class="material-symbols-outlined"> search </span>

      <input
        type="text"
        :value="searchValue"
        @input="$emit('update:searchValue', $event.target.value)"
        :placeholder="title"
      />

      <select
        :value="filterValue"
        @input="$emit('update:filterValue', $event.target.value)"
        name="search-by"
        class="dropdown"
      >
        <option value="" selected disabled hidden>Search by</option>
        <option v-for="option in searchOptions" :value="option" :key="option">{{ option }}</option>
      </select>

      <select
        :value="priorityValue"
        @input="$emit('update:priorityValue', $event.target.value)"
        name="priority-by"
        class="dropdown"
      >
        <option value="" selected disabled hidden>Any Priority</option>
        <option v-for="option in priorityOptions" :value="option" :key="option">
          {{ option }}
        </option>
      </select>
    </div>

    <div class="right">
      <select v-model="sortChose" name="sort-by" class="dropdown">
        <option value="" selected disabled hidden>Sort by</option>
        <option v-for="option in sortOptions" :value="option" :key="option">{{ option }}</option>
      </select>

      <button @click="$emit('onSort', sortIndex, 0)" class="sort-btn">
        <span class="material-symbols-outlined"> sort </span>
        Sort
      </button>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'

export default {
  props: ['title', 'searchValue', 'filterValue', 'priorityValue'],
  emits: ['update:searchValue', 'update:filterValue', 'update:priorityValue', 'onSort'],
  setup() {
    const searchOptions = computed(() => ['Subject', 'Sender', 'Description'])
    const priorityOptions = computed(() => ['Any Priority', '1 (Low)', '2', '3', '4', '5 (High)'])

    const sortChose = ref('')
    const sortOptions = computed(() => ['Ascending', 'Descending', 'Priority'])

    const sortIndex = computed(() => {
      switch (sortChose.value) {
        case 'Ascending':
          return 0
        case 'Descending':
          return 1
        case 'Priority':
          return 2
        default:
          return 0
      }
    })

    return { searchOptions, priorityOptions, sortChose, sortOptions, sortIndex }
  }
}
</script>

<style scoped>
#search-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}

.left {
  display: flex;
  flex: 0.7;
  align-items: center;
  background-color: #eee;
  padding: 10px;
  margin: 10px;
  border-radius: 18px;
}

.left span {
  color: #777;
  background-color: transparent;
}

.left input {
  border: none;
  width: 100%;
  padding: 10px;
  outline: none;
  font-size: medium;
  background-color: transparent;
}

select {
  padding: 5px;
  margin: 5px;
  border: none;
  border-bottom: 1px solid gray;
  background-color: transparent;
  outline: none;
  cursor: pointer;
}

option {
  margin: 10px;
}

.right {
  display: flex;
  flex: 0.3;
  justify-content: space-between;
  align-items: center;
  background-color: #eee;
  padding: 10px;
  margin: 10px;
  border-radius: 18px;
}

.sort-btn {
  display: flex;
  align-items: center;
  padding: 8px 20px;
  border: none;
  background-color: gray;
  color: white;
  border-radius: 12px;
  font-weight: bold;
  cursor: pointer;
}

.sort-btn span {
  margin-right: 5px;
  background-color: transparent;
}
</style>

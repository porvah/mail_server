<template>
  <div class="search-bar">
    <div class="left">
      <span class="material-symbols-outlined"> search </span>

      <input type="text" placeholder="Search mail" />

      <select v-model="searchChose" name="search-by" class="dropdown">
        <option value="" selected disabled hidden>Search by</option>
        <option v-for="option in searchOptions" :value="option">{{ option }}</option>
      </select>
    </div>

    <div class="right">
      <select v-model="sortChose" name="sort-by" class="dropdown">
        <option value="" selected disabled hidden>Sort by</option>
        <option v-for="option in sortOptions" :value="option">{{ option }}</option>
      </select>

      <button class="sort-btn">
        <span class="material-symbols-outlined"> sort </span>
        Sort
      </button>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'

export default {
  setup() {
    const searchChose = ref('')
    const searchOptions = computed(() => ['Subject', 'Sender', 'Description', 'Tag'])

    const sortChose = ref('')
    const sortOptions = computed(() => ['Ascending', 'Descending', 'Priority'])

    return { searchOptions, searchChose, sortChose, sortOptions }
  }
}
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.left {
  display: flex;
  flex: 0.7;
  align-items: center;
  background-color: #ddd;
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
  background-color: #ddd;
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

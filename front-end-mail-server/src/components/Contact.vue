<template>
  <div id="contact">
    <h3>@{{ person.name }}</h3>
    |
    <h4>
      {{ person.email }}
    </h4>
    |
    <span class="material-symbols-outlined delete"> delete </span>
  </div>
</template>

<script>
export default {
  props: ['person']
}
</script>

<style scoped>
#contact {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  background-color: #bbb;
  padding: 8px;
  margin: 8px 0;
  border-radius: 10px;
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

<template>
  <div id="main-body">
    <SideBar />
    <router-view />
  </div>
</template>

<script>
import Inbox from '../views/Inbox.vue'
import SideBar from './SideBar.vue'

export default { components: { SideBar, Inbox } }
</script>

<style scoped>
#main-body {
  display: flex;
}
</style>

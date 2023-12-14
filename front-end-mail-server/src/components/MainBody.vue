<template>
  <div id="main-body">
    <SideBar />
    <ComposeDialog />
    <router-view />
  </div>
</template>

<script>
import Inbox from '../views/Inbox.vue'
import ComposeDialog from './ComposeDialog.vue'
import SideBar from './SideBar.vue'

export default { components: { SideBar, Inbox, ComposeDialog } }
</script>

<style scoped>
#main-body {
  display: flex;
}
</style>

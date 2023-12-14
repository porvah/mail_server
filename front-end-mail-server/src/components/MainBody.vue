<template>
  <div id="main-body">
    <SideBar />
    <ProfileDialog />
    <router-view />
  </div>
</template>

<script>
import Inbox from '../views/Inbox.vue'
import ComposeDialog from './ComposeDialog.vue'
import ProfileDialog from './ProfileDialog.vue'
import SettingsDialog from './SettingsDialog.vue'
import SideBar from './SideBar.vue'

export default { components: { SideBar, Inbox, ComposeDialog, SettingsDialog, ProfileDialog } }
</script>

<style scoped>
#main-body {
  display: flex;
}
</style>

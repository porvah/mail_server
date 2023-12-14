<template>
  <div id="main-body">
    <SideBar />
    <ComposeDialog v-if="isComposeDialogOpen" />
    <ProfileDialog v-if="isProfileDialogOpen" />
    <SettingsDialog v-if="isSettingsDialogOpen" />
    <router-view />
  </div>
</template>

<script>
import { useStore } from 'vuex'

import Inbox from '../views/Inbox.vue'
import ComposeDialog from './ComposeDialog.vue'
import ProfileDialog from './ProfileDialog.vue'
import SettingsDialog from './SettingsDialog.vue'
import SideBar from './SideBar.vue'
import { computed } from 'vue'

export default {
  components: { SideBar, Inbox, ComposeDialog, SettingsDialog, ProfileDialog },
  setup() {
    const store = useStore()
    const isComposeDialogOpen = computed(() => store.state.dialogs.isComposeDialogOpen)
    const isProfileDialogOpen = computed(() => store.state.dialogs.isProfileDialogOpen)
    const isSettingsDialogOpen = computed(() => store.state.dialogs.isSettingsDialogOpen)

    return {
      isComposeDialogOpen,
      isProfileDialogOpen,
      isSettingsDialogOpen
    }
  }
}
</script>

<style scoped>
#main-body {
  display: flex;
}
</style>

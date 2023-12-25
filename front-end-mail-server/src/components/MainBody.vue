<template>
  <div id="main-body">
    <SideBar />
    <ComposeDialog v-if="isComposeDialogOpen" />

    <ProfileDialog v-if="isProfileDialogOpen" />

    <SettingsDialog v-if="isSettingsDialogOpen" />

    <FolderDialog v-if="isFolderDialogOpen" />

    <router-view />
  </div>
</template>

<script>
import { useStore } from 'vuex'

import Inbox from '../views/Inbox.vue'
import ComposeDialog from './ComposeDialog.vue'
import ProfileDialog from './ProfileDialog.vue'
import SettingsDialog from './SettingsDialog.vue'
import FolderDialog from './FolderDialog.vue'
import SideBar from './SideBar.vue'
import { computed, onMounted } from 'vue'

export default {
  components: { SideBar, Inbox, ComposeDialog, SettingsDialog, ProfileDialog, FolderDialog },
  setup() {
    const store = useStore()
    const isComposeDialogOpen = computed(() => store.getters.isComposeDialogOpen)
    const isProfileDialogOpen = computed(() => store.getters.isProfileDialogOpen)
    const isSettingsDialogOpen = computed(() => store.getters.isSettingsDialogOpen)
    const isFolderDialogOpen = computed(() => store.getters.isFolderDialogOpen)

    onMounted(async () => {
      await store.dispatch('updateAllFolders', { token: store.getters.token, sort: 0 })
    })

    return {
      isComposeDialogOpen,
      isProfileDialogOpen,
      isSettingsDialogOpen,
      isFolderDialogOpen
    }
  }
}
</script>

<style scoped>
#main-body {
  display: flex;
}
</style>

import { createStore } from 'vuex'

const store = createStore({
  state: {
    isComposeDialogOpen: false,
    isSettingsDialogOpen: false,
    isProfileDialogOpen: false
  },
  mutations: {
    openComposeDialog(state) {
      state.isComposeDialogOpen = true
      state.isSettingsDialogOpen = false
      state.isProfileDialogOpen = false
    },
    closeComposeDialog(state) {
      state.isComposeDialogOpen = false
    },
    openSettingsDialog(state) {
      state.isComposeDialogOpen = false
      state.isSettingsDialogOpen = true
      state.isProfileDialogOpen = false
    },
    closeSettingsDialog(state) {
      state.isSettingsDialogOpen = false
    },
    openProfileDialog(state) {
      state.isComposeDialogOpen = false
      state.isSettingsDialogOpen = false
      state.isProfileDialogOpen = true
    },
    closeProfileDialog(state) {
      state.isProfileDialogOpen = false
    }
  }
})

export default store

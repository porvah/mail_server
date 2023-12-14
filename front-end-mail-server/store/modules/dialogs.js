const state = {
  isComposeDialogOpen: false,
  isSettingsDialogOpen: false,
  isProfileDialogOpen: false
}

const mutations = {
  openComposeDialog(state) {
    console.log('here')
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

const getters = {
  isComposeDialogOpen: (state) => state.isComposeDialogOpen,
  isSettingsDialogOpen: (state) => state.isSettingsDialogOpen,
  isProfileDialogOpen: (state) => state.isProfileDialogOpen
}

export default {
  state,
  mutations,
  getters
}

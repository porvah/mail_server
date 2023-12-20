const state = {
  isComposeDialogOpen: false,
  isSettingsDialogOpen: false,
  isProfileDialogOpen: false,
  isFolderDialogOpen: false
}

const mutations = {
  openComposeDialog(state) {
    state.isComposeDialogOpen = true
    state.isSettingsDialogOpen = false
    state.isProfileDialogOpen = false
    state.isFolderDialogOpen = false
  },
  closeComposeDialog(state) {
    state.isComposeDialogOpen = false
  },
  openSettingsDialog(state) {
    state.isComposeDialogOpen = false
    state.isSettingsDialogOpen = true
    state.isProfileDialogOpen = false
    state.isFolderDialogOpen = false
  },
  closeSettingsDialog(state) {
    state.isSettingsDialogOpen = false
  },
  openProfileDialog(state) {
    state.isComposeDialogOpen = false
    state.isSettingsDialogOpen = false
    state.isProfileDialogOpen = true
    state.isFolderDialogOpen = false
  },
  closeProfileDialog(state) {
    state.isProfileDialogOpen = false
  },
  openFolderDialog(state) {
    state.isComposeDialogOpen = false
    state.isSettingsDialogOpen = false
    state.isProfileDialogOpen = false
    state.isFolderDialogOpen = true
  },
  closeFolderDialog(state) {
    state.isFolderDialogOpen = false
  }
}

const getters = {
  isComposeDialogOpen: (state) => state.isComposeDialogOpen,
  isSettingsDialogOpen: (state) => state.isSettingsDialogOpen,
  isProfileDialogOpen: (state) => state.isProfileDialogOpen,
  isFolderDialogOpen: (state) => state.isFolderDialogOpen
}

export default {
  state,
  mutations,
  getters
}

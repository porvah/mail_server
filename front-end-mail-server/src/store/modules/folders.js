import api from '@/api'

const state = {
  inboxMails: [],
  sentMails: [],
  trashMails: [],
  draftMails: [],
  foldersNames: []
}

const mutations = {
  getInbox(state, mails) {
    state.inboxMails = mails
  },
  getSent(state, mails) {
    state.sentMails = mails
  },
  getTrash(state, mails) {
    state.trashMails = mails
  },
  getDraft(state, mails) {
    state.draftMails = mails
  },
  getFolders(state, folders) {
    state.foldersNames = folders
  }
}

const actions = {
  async getInbox({ commit }, { token, sort }) {
    const mails = await api.folder.getInbox(token, sort)

    commit('getInbox', mails)
  },
  async getSent({ commit }, { token, sort }) {
    const mails = await api.folder.getSent(token, sort)

    commit('getSent', mails)
  },
  async getTrash({ commit }, { token, sort }) {
    const mails = await api.folder.getTrash(token, sort)

    commit('getTrash', mails)
  },
  async getDraft({ commit }, { token, sort }) {
    const mails = await api.folder.getDraft(token, sort)

    commit('getDraft', mails)
  },
  async getFolders({ commit }, { token }) {
    const folders = await api.folder.getFolders(token)

    commit('getFolders', folders)
  }
}

const getters = {
  inboxMails: (state) => state.inboxMails,
  sentMails: (state) => state.sentMails,
  trashMails: (state) => state.trashMails,
  draftMails: (state) => state.draftMails,
  foldersNames: (state) => state.foldersNames
}

export default {
  state,
  mutations,
  actions,
  getters
}

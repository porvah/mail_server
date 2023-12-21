import api from '@/api'

const state = {
  inboxMails: [],
  sentMails: [],
  trashMails: []
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
  }
}

const getters = {
  inboxMails: (state) => state.inboxMails,
  sentMails: (state) => state.sentMails,
  trashMails: (state) => state.trashMails
}

export default {
  state,
  mutations,
  actions,
  getters
}

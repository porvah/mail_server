import api from '@/api'

const state = {
  inboxMails: [],
  sentMails: []
}

const mutations = {
  getInbox(state, mails) {
    state.inboxMails = mails
  },
  getSent(state, mails) {
    state.sentMails = mails
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
  }
}

const getters = {
  inboxMails: (state) => state.inboxMails,
  sentMails: (state) => state.sentMails
}

export default {
  state,
  mutations,
  actions,
  getters
}

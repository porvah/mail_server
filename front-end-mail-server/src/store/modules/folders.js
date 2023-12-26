import api from '@/api'

const state = {
  inboxMails: [],
  sentMails: [],
  trashMails: [],
  draftMails: [],
  foldersNames: [],
  allContacts: [],
  curInbox: 0,
  totalInbox: 0,
  curDraft: 0,
  totalDraft: 0,
  curSent: 0,
  totalSent: 0,
  curTrash: 0,
  totalTrash: 0
}

const mutations = {
  getInbox(state, { mails, current, total }) {
    state.inboxMails = mails
    state.curInbox = current
    state.totalInbox = total
  },
  getSent(state, { mails, current, total }) {
    state.sentMails = mails
    state.curSent = current
    state.totalSent = total
  },
  getTrash(state, { mails, current, total }) {
    state.trashMails = mails
    state.curTrash = current
    state.totalTrash = total
  },
  getDraft(state, { mails, current, total }) {
    state.draftMails = mails
    state.curDraft = current
    state.totalDraft = total
  },
  getFolders(state, folders) {
    state.foldersNames = folders
  },
  getAllContacts(state, allContacts) {
    state.allContacts = allContacts
  }
}

const actions = {
  async getInbox({ commit }, { token, sort, page }) {
    const response = await api.folder.getInbox(token, sort, page)
    const mails = response.list
    const current = response.current
    const total = response.total

    commit('getInbox', { mails, current, total })
  },
  async getSent({ commit }, { token, sort, page }) {
    const response = await api.folder.getSent(token, sort, page)
    const mails = response.list
    const current = response.current
    const total = response.total

    commit('getSent', { mails, current, total })
  },
  async getTrash({ commit }, { token, sort, page }) {
    const response = await api.folder.getTrash(token, sort, page)
    const mails = response.list
    const current = response.current
    const total = response.total

    commit('getTrash', { mails, current, total })
  },
  async getDraft({ commit }, { token, sort, page }) {
    const response = await api.folder.getDraft(token, sort, page)
    const mails = response.list
    const current = response.current
    const total = response.total

    commit('getDraft', { mails, current, total })
  },
  async getFolders({ commit }, { token }) {
    const folders = await api.folder.getFolders(token)

    commit('getFolders', folders)
  },
  async getAllContacts({ commit }, { token }) {
    const currContacts = await api.contactsService.getContacts(token)

    commit('getAllContacts', currContacts)
  },
  async updateAllFolders({ commit, dispatch }, { token }) {
    const sort = 0
    const page = 0
    await dispatch('getInbox', { token, sort, page })
    await dispatch('getSent', { token, sort, page })
    await dispatch('getTrash', { token, sort, page })
    await dispatch('getDraft', { token, sort, page })
    await dispatch('getFolders', { token, page })
    await dispatch('getAllContacts', { token, page })
  }
}

const getters = {
  inboxMails: (state) => state.inboxMails,
  sentMails: (state) => state.sentMails,
  trashMails: (state) => state.trashMails,
  draftMails: (state) => state.draftMails,
  foldersNames: (state) => state.foldersNames,
  allContacts: (state) => state.allContacts,
  curInbox: (state) => state.curInbox,
  curDraft: (state) => state.curDraft,
  curSent: (state) => state.curSent,
  curTrash: (state) => state.curTrash,
  totalInbox: (state) => state.totalInbox,
  totalDraft: (state) => state.totalDraft,
  totalSent: (state) => state.totalSent,
  totalTrash: (state) => state.totalTrash
}

export default {
  state,
  mutations,
  actions,
  getters
}

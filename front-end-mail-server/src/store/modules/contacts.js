import api from '@/api'

const state = {
  allContacts: []
}

const mutations = {
  getAllContacts(state, allContacts) {
    state.allContacts = allContacts
  }
}

const actions = {
  async getAllContacts({ commit }, { token }) {
    const currContacts = await api.contactsService.getContacts(token)

    commit('getAllContacts', currContacts)
  }
}

const getters = {
  allContacts: (state) => state.allContacts
}

export default {
  state,
  mutations,
  actions,
  getters
}

import { createStore } from 'vuex'
import register from './modules/register'
import dialogs from './modules/dialogs'
import folders from './modules/folders'
import contacts from './modules/contacts'

const store = createStore({
  modules: {
    dialogs,
    register,
    folders,
    contacts
  }
})

export default store

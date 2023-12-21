import { createStore } from 'vuex'
import register from './modules/register'
import dialogs from './modules/dialogs'
import folders from './modules/folders'

const store = createStore({
  modules: {
    dialogs,
    register,
    folders
  }
})

export default store

import { createStore } from 'vuex'
import register from './modules/register'
import dialogs from './modules/dialogs'

const store = createStore({
  modules: {
    dialogs,
    register
  }
})

export default store

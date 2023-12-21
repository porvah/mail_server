import AuthService from './services/AuthService'
import FolderService from './services/FolderService'

const auth = AuthService.getInstance()
const folder = FolderService.getInstance()

export default { auth, folder }

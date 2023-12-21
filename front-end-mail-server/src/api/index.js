import AuthService from './services/AuthService'
import EmailService from './services/EmailService'
import FolderService from './services/FolderService'

const auth = AuthService.getInstance()
const folder = FolderService.getInstance()
const emailService = EmailService.getInstance()

export default { auth, folder, emailService }

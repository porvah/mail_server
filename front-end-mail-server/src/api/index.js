import AuthService from './services/AuthService'
import ContactsService from './services/ContactsService'
import EmailService from './services/EmailService'
import FolderService from './services/FolderService'

const auth = AuthService.getInstance()
const folder = FolderService.getInstance()
const emailService = EmailService.getInstance()
const contactsService = ContactsService.getInstance()

export default { auth, folder, emailService, contactsService }

import store from '@/store'

class EmailServiceAdapter {
  constructor(emailService) {
    this.emailService = emailService
  }

  async sendEmail(email) {
    const token = store.getters.token
    const { receiver, subject, body, priority } = email
    await this.emailService.sendEmail(token, receiver, subject, body, priority)
  }
}

export default EmailServiceAdapter

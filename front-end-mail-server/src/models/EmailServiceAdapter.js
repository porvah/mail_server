import store from '@/store'

class EmailServiceAdapter {
  constructor(emailService) {
    this.emailService = emailService
  }

  async sendEmail(email) {
    const token = store.getters.token
    const receiver = email.receiver
    const subject = email.subject
    const body = email.body
    const priority = email.priority

    await this.emailService.sendEmail(token, receiver, subject, body, priority)
  }

  async draftEmail(email) {
    const token = store.getters.token
    const subject = email.subject
    const body = email.body
    const priority = email.priority

    await this.emailService.draftEmail(token, subject, body, priority)
  }
}

export default EmailServiceAdapter

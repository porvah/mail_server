import ApiService from '@/api/services/ApiService'
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

  async updateDraftEmail(email) {
    const token = store.getters.token
    const id = email.id
    const subject = email.subject
    const body = email.body
    const priority = email.priority

    await this.emailService.updateDraftEmail(token, id, subject, body, priority)
  }

  async submitDraftEmail(email) {
    const token = store.getters.token
    const id = email.id
    const receiver = email.receiver
    const subject = email.subject
    const body = email.body
    const priority = email.priority

    await this.emailService.submitDraftEmail(token, id, receiver, subject, body, priority)
  }
}

export default EmailServiceAdapter

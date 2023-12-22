import ApiService from './ApiService'

class EmailService {
  static instance

  static getInstance() {
    if (!EmailService.instance) {
      EmailService.instance = new EmailService()
    }
    return EmailService.instance
  }

  async sendEmail(token, receiver, subject, body, priority) {
    const service = ApiService.getInstance()

    return await service.makeRequest('sendemail', 'POST', {
      token,
      receiver,
      subject,
      body,
      priority
    })
  }

  async deleteEmail(token, id) {
    const service = ApiService.getInstance()

    return await service.makeRequest('delete', 'DELETE', {
      token,
      id
    })
  }

  async draftEmail(token, subject, body, priority) {
    const service = ApiService.getInstance()

    return await service.makeRequest('draftemail', 'POST', {
      token,
      subject,
      body,
      priority
    })
  }

  async updateDraftEmail(token, id, subject, body, priority) {
    const service = ApiService.getInstance()

    return await service.makeRequest('updatedraft', 'PUT', {
      token,
      id,
      subject,
      body,
      priority
    })
  }

  async submitDraftEmail(token, id, receiver, subject, body, priority) {
    const service = ApiService.getInstance()

    return await service.makeRequest('submitdraft', 'POST', {
      token,
      id,
      receiver,
      subject,
      body,
      priority
    })
  }
}

export default EmailService

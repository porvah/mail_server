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
}

export default EmailService

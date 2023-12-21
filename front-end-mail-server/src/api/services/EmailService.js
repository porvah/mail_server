const { default: ApiService } = require('./ApiService')

class EmailService {
  static instance

  static getInstance() {
    if (!EmailService.instance) {
      EmailService.instance = new EmailService()
    }
    return EmailService.instance
  }

  async sendEmail(token, receiver, subject, body, prioriy) {
    const service = ApiService.getInstance()

    return await service.makeRequest('sendemail', 'POST', {
      token,
      receiver,
      subject,
      body,
      prioriy
    })
  }
}

export default EmailService

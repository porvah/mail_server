import ApiService from './ApiService'

class EmailService {
  static instance

  static getInstance() {
    if (!EmailService.instance) {
      EmailService.instance = new EmailService()
    }
    return EmailService.instance
  }

  async sendEmail(formdata) {
    const service = ApiService.getInstance()
    return await service.makeRequest('sendemail', 'POST', formdata, false, {})
  }

  async deleteEmail(token, id) {
    const service = ApiService.getInstance()

    return await service.makeRequest('delete', 'DELETE', {
      token,
      id
    })
  }

  async draftEmail(formdata) {
    const service = ApiService.getInstance()

    return await service.makeRequest('draftemail', 'POST', formdata, false, {})
  }

  async updateDraftEmail(formdata) {
    const service = ApiService.getInstance()

    return await service.makeRequest('updatedraft', 'PUT', formdata, false, {})
  }

  async submitDraftEmail(formdata) {
    const service = ApiService.getInstance()

    return await service.makeRequest('submitdraft', 'POST', formdata, false, {})
  }

  async moveMail(token, id, foldername) {
    const service = ApiService.getInstance()

    return await service.makeRequest('movemails', 'PUT', {
      token,
      id,
      foldername
    })
  }

  async restoreMail(token, id) {
    const service = ApiService.getInstance()

    return await service.makeRequest('restore', 'PUT', {
      token,
      id
    })
  }
}

export default EmailService

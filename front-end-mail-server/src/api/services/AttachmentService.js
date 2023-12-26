import ApiService from './ApiService'

class AttachmentService {
  static instance

  static getInstance() {
    if (!AttachmentService.instance) {
      AttachmentService.instance = new AttachmentService()
    }
    return AttachmentService.instance
  }

  async getAttachments(token, id) {
    const service = ApiService.getInstance()

    return await service.makeRequest(`getattachment?token=${token}&id=${id}`, 'GET')
  }
}

export default AttachmentService

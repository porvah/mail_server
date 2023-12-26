import ApiService from './ApiService'

class AttachmentService {
  static instance

  static getInstance() {
    if (!AttachmentService.instance) {
      AttachmentService.instance = new AttachmentService()
    }
    return AttachmentService.instance
  }

  async sendAttachment(token) {}
}

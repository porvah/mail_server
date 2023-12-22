import ApiService from './ApiService'

class FolderService {
  static instane

  static getInstance() {
    if (!FolderService.instane) {
      FolderService.instane = new FolderService()
    }
    return FolderService.instane
  }

  async getInbox(token, sort) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`inbox?token=${token}&sort=${sort}`, 'GET')
  }

  async getSent(token, sort) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`sent?token=${token}&sort=${sort}`, 'GET')
  }

  async getTrash(token, sort) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`trash?token=${token}&sort=${sort}`, 'GET')
  }

  async getDraft(token, sort) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`draft?token=${token}&sort=${sort}`, 'GET')
  }

  async getFolders(token) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`folders?token=${token}`, 'GET')
  }
}

export default FolderService

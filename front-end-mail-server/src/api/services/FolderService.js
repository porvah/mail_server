import ApiService from './ApiService'

class FolderService {
  static instane

  static getInstance() {
    if (!FolderService.instane) {
      FolderService.instane = new FolderService()
    }
    return FolderService.instane
  }

  async getInbox(token, sort, page) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`inbox?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getSent(token, sort, page) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`sent?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getTrash(token, sort, page) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`trash?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getDraft(token, sort, page) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`draft?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getFolders(token) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`folders?token=${token}`, 'GET')
  }

  async createFolder(token, foldername) {
    const service = ApiService.getInstance()
    return await service.makeRequest('createfolder', 'POST', {
      token,
      foldername
    })
  }

  async getFolderEmails(token, sort, foldername, page) {
    const service = ApiService.getInstance()
    return await service.makeRequest(
      `folderemails?token=${token}&foldername=${foldername}&sort=${sort}&required=${page}`,
      'GET'
    )
  }

  async deleteFolder(token, foldername) {
    const service = ApiService.getInstance()
    return await service.makeRequest('deletefolder', 'DELETE', {
      token,
      foldername
    })
  }
}

export default FolderService

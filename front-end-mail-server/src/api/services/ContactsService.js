import ApiService from './ApiService'

class ContactsService {
  static instane

  static getInstance() {
    if (!ContactsService.instane) {
      ContactsService.instane = new ContactsService()
    }
    return ContactsService.instane
  }

  async getContacts(token) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`contacts?token=${token}&sort=0`, 'GET')
  }

  async createContact(token, name, emails) {
    const service = ApiService.getInstance()
    return await service.makeRequest('createcontact', 'POST', {
      token,
      name,
      emails
    })
  }

  async deleteContact(token, contactIds) {
    const service = ApiService.getInstance()
    return await service.makeRequest('deletecontacts', 'DELETE', {
      token,
      contactIds
    })
  }

  async updateContact(token, contactId, name, emails) {
    const service = ApiService.getInstance()
    return await service.makeRequest('updatecontact', 'PUT', {
      token,
      contactId,
      name,
      emails
    })
  }
}

export default ContactsService

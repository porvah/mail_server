import ApiService from './ApiService'

class AuthService {
  static instane

  static getInstance() {
    if (!AuthService.instane) {
      AuthService.instane = new AuthService()
    }
    return AuthService.instane
  }

  async login(email, password) {
    const service = ApiService.getInstance()
    return await service.makeRequest('login', 'POST', { email, password })
  }

  async signUp(name, email, password) {
    const service = ApiService.getInstance()
    return await service.makeRequest('signup', 'POST', { name, email, password })
  }

  async getUser(token) {
    const service = ApiService.getInstance()
    return await service.makeRequest('getuser', 'POST', { token })
  }
}

export default AuthService

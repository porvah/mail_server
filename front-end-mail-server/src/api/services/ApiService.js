class ApiService {
  static instance

  static getInstance() {
    if (ApiService.instance == null) {
      ApiService.instance = new ApiService()
      ApiService.instance.baseUrl = 'http//localhost:8080'
    }

    return ApiService.instance
  }

  async makeRequest(endpoint, method = 'GET', data = null) {
    const url = `${this.baseUrl}/${endpoint}`
    const options = {
      method,
      headers: {
        'Content-Type': 'application/json'
      },
      body: data ? JSON.stringify(data) : null
    }

    const response = await fetch(url, options)
    const resData = await response.json()
    return resData
  }
}

// const apiService = ApiService.getInstance()

export default ApiService

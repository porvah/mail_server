class ApiService {
  static instance

  static getInstance() {
    if (ApiService.instance == null) {
      ApiService.instance = new ApiService()
      ApiService.instance.baseUrl = 'http://localhost:8080'
    }

    return ApiService.instance
  }

  async makeRequest(endpoint, method = 'GET', data = null, json = true, headers = null) {
    const url = `${this.baseUrl}/${endpoint}`

    let body = null
    if (data && json) {
      body = JSON.stringify(data)
    } else if (data) {
      body = data
    }

    const options = {
      method,
      headers: headers
        ? headers
        : {
            'Content-Type': 'application/json'
          },
      body: body
    }

    const response = await fetch(url, options)
    if (response.ok) {
      const resData = await response.json()
      return resData
    } else {
      throw await response.text()
    }
  }
}

// const apiService = ApiService.getInstance()

export default ApiService

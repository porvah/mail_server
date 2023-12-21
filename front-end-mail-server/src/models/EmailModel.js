class EmailModel {
  constructor() {
    this.email = {}
  }

  addToken(token) {
    this.email.token = token
    return this
  }

  addReceiver(receiver) {
    this.email.receiver = receiver
    return this
  }

  addSubject(subject) {
    this.email.subject = subject
    return this
  }

  addBody(body) {
    this.email.body = body
    return this
  }

  addPriority(priority) {
    this.email.priority = priority
    return this
  }

  build() {
    return this.email
  }
}

export default EmailModel

class EmailModel {
  constructor() {
    this.email = {}
  }

  addSender(sender) {
    this.email.sender = sender
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

  addAttachments(files) {
    this.email.files = files
    return this
  }

  build() {
    return this.email
  }
}

export default EmailModel

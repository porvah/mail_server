class EmailModel {
  constructor() {
    this.id = null
    this.sender = ''
    this.receiver = []
    this.subject = ''
    this.body = ''
    this.priority = 1
    this.attachments = []
  }

  getId() {
    return this.id
  }

  getSender() {
    return this.sender
  }

  getReceiver() {
    return this.receiver
  }

  getSubject() {
    return this.subject
  }

  getBody() {
    return this.body
  }

  getPriority() {
    return this.priority
  }

  getAttachments() {
    return this.attachments
  }

  setId(id) {
    this.id = id
  }

  setSender(sender) {
    this.sender = sender
  }

  setReceiver(receiver) {
    this.receiver = receiver
  }

  setSubject(subject) {
    this.subject = subject
  }

  setBody(body) {
    this.body = body
  }

  setPriority(priority) {
    this.priority = priority
  }

  setAttachments(attachments) {
    this.attachments = attachments
  }

  getEmail() {
    return {
      id: this.id,
      sender: this.sender,
      receiver: this.receiver,
      subject: this.subject,
      body: this.body,
      priority: this.priority,
      files: this.attachments
    }
  }
}

export default EmailModel

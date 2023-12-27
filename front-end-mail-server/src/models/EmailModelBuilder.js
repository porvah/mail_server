import EmailModel from './EmailModel'

class EmailModelBuilder {
  constructor() {
    this.email = new EmailModel()
  }

  addSender(sender) {
    this.email.setSender(sender)
    return this
  }

  addReceiver(receiver) {
    this.email.setReceiver(receiver)
    return this
  }

  addSubject(subject) {
    this.email.setSubject(subject)
    return this
  }

  addBody(body) {
    this.email.setBody(body)
    return this
  }

  addPriority(priority) {
    this.email.setPriority(priority)
    return this
  }

  addAttachments(attachments) {
    this.email.setAttachments(attachments)
    return this
  }

  build() {
    return this.email.getEmail()
  }
}

export default EmailModelBuilder

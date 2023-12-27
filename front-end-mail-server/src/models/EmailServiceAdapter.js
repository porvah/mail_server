import EmailService from '@/api/services/EmailService'
import store from '@/store'

class EmailServiceAdapter extends EmailService {
  // constructor(emailService) {
  //   this.emailService = emailService
  // }

  async sendEmail(email) {
    const token = store.getters.token
    const receiver = email.receiver
    const subject = email.subject
    const body = email.body
    const priority = email.priority
    const files = email.files

    var formdata = new FormData()
    formdata.append('token', `${token}`)
    formdata.append('body', `${body}`)
    formdata.append('priority', `${priority}`)
    formdata.append('subject', `${subject}`)

    for (let i = 0; i < receiver.length; ++i) {
      formdata.append('receiver', `${receiver[i]}`)
    }

    for (let i = 0; i < files.length; ++i) {
      formdata.append('files', files[i])
    }

    await super.sendEmail(formdata)
  }

  async draftEmail(email) {
    const token = store.getters.token
    const subject = email.subject
    const body = email.body
    const priority = email.priority
    const files = email.files

    var formdata = new FormData()
    formdata.append('token', `${token}`)
    formdata.append('body', `${body}`)
    formdata.append('priority', `${priority}`)
    formdata.append('subject', `${subject}`)

    for (let i = 0; i < files.length; ++i) {
      formdata.append('files', files[i])
    }

    await super.draftEmail(formdata)
  }

  async updateDraftEmail(email) {
    const token = store.getters.token
    const id = email.id
    const subject = email.subject
    const body = email.body
    const priority = email.priority
    const files = email.files

    var formdata = new FormData()
    formdata.append('token', `${token}`)
    formdata.append('id', `${id}`)
    formdata.append('body', `${body}`)
    formdata.append('priority', `${priority}`)
    formdata.append('subject', `${subject}`)

    for (let i = 0; i < files.length; ++i) {
      formdata.append('files', files[i])
    }

    await super.updateDraftEmail(formdata)
  }

  async submitDraftEmail(email) {
    const token = store.getters.token
    const id = email.id
    const receiver = email.receiver
    const subject = email.subject
    const body = email.body
    const priority = email.priority
    const files = email.files

    var formdata = new FormData()
    formdata.append('token', `${token}`)
    formdata.append('id', `${id}`)
    formdata.append('body', `${body}`)
    formdata.append('priority', `${priority}`)
    formdata.append('subject', `${subject}`)

    for (let i = 0; i < receiver.length; ++i) {
      formdata.append('receiver', `${receiver[i]}`)
    }

    for (let i = 0; i < files.length; ++i) {
      formdata.append('files', files[i])
    }

    await super.submitDraftEmail(formdata)
  }
}

export default EmailServiceAdapter

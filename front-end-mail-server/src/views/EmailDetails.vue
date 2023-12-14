<template>
  <div id="email-details">
    <h2 id="subject">{{ email.subject }}</h2>

    <div id="header">
      <div id="info">
        <span class="material-symbols-outlined"> person </span>

        <div id="sender-receiver">
          <h4 id="sender">{{ email.sender }}</h4>

          <div id="receiver">to: {{ email.receiver }}</div>
        </div>
      </div>

      <div id="date">{{ email.date }}</div>
    </div>

    <p>{{ email.description }}</p>
  </div>
</template>

<script>
export default {
  props: ['id'],
  setup(props) {
    const emailId = props.id

    const email = {
      id: 0,
      sender: 'mohamed@test.com',
      receiver: 'ahmed@test.com',
      subject: 'Testing email details',
      description:
        'Props attributes are written with a dash - to separate words (kebab-case) in the <template> tag, but kebab-case is not legal in JavaScript. So instead we need to write the attribute names as camelCase in JavaScript, and Vue understands this automatically!',
      date: '12/13/2023 12:16'
    }

    return { emailId, email }
  }
}
</script>

<style scoped>
#email-details {
  flex: 0.8;
  overflow-x: hidden;
  height: 90vh;

  background-color: #eeeeeead;
  border-radius: 12px;
}

#subject {
  padding: 20px;
  text-align: start;
  border-bottom: 1px solid gray;
}

#header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
}

#info {
  display: flex;
  align-items: center;
  padding: 5px 0;
}

#info span {
  color: white;
  background-color: gray;
  border-radius: 50px;
  padding: 10px;
  margin-right: 10px;
  font-size: 30px;
}

p {
  margin: 0 10%;
  text-align: start;
}

#receiver {
  color: #444;
}

#date {
  color: #666;
}
</style>

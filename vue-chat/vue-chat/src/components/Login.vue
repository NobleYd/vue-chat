<template>
  <div class="loginBox">
    <table>
        <tr>
          <td>NickName</td>
          <td><input type="text" v-model="nickName" placeholder="please input a nickName..."/></td>
        </tr>
        <tr>
          <td colspan="2">
            <button @click="login" type="button" >Login</button>
            {{nickName}}
          </td>
        </tr>
    </table>
  </div>
</template>

<script>
import loginApi from '@/api/login'
export default {
  name: 'hello',
  data () {
    return {
      nickName: ''
    }
  },
  methods: {
    login: function () {
      console.log('try to login with nickName: ' + this.nickName)
      loginApi({nickName: this.nickName}).then(res => {
        sessionStorage.setItem('user', JSON.stringify(res.data))
        console.log('login success')
        this.$router.push('home')
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.loginBox{
  width: 200px;
  margin: auto;
}
</style>

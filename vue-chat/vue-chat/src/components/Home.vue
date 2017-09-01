<script>

import Card from '@/components/Card'
import List from '@/components/List'
import MsgEdit from '@/components/MsgEdit'
import Message from '@/components/Message'

import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

export default {
  data () {
    return {
      // 当前登陆的user
      user: JSON.parse(sessionStorage.getItem('user')),
      // 记录好友列表（用户列表）
      users: [],
      // 记录好友列表的搜索key
      filterKey: '',
      // 当前选中的聊天用户对象(默认为null-->群聊)
      currentChatUser: null,
      stompClient: null,
      // 记录聊天记录(Map 'userId' -> msgs)
      chats: new Map(),
      currentMessages: []
    }
  },
  methods: {
    setFilterKey (filterKey) {
      this.filterKey = filterKey
    },
    chatWithUser (user) {
      console.log('home receive event: chatWithUser, userId: ' + user.id)
      this.currentChatUser = user
      if (!this.chats.has(this.currentChatUser.id)) {
        this.chats.set(this.currentChatUser.id, [])
      }
      this.currentMessages = this.chats.get(this.currentChatUser.id)
    },
    sendMsg (content) {
      console.log('home receive event: sendMsg, content: ' + content + ', currentChatUser: ' + this.currentChatUser)
      if (this.currentChatUser != null) {
        let msg = {
          type: 'chat',
          from: this.user.id,
          to: this.currentChatUser.id,
          content: content,
          date: new Date() + ''
        }
        this.stompClient.send('/app/u/' + this.currentChatUser.id, {}, JSON.stringify(msg))
        this.chats.get(this.currentChatUser.id).push(msg)
      } else {
        alert('first choose a user to chat')
      }
    }
  },
  mounted () {
    console.log('mounted')
    let $this = this
    let url = 'http://localhost:80/chat'
    let socket = new SockJS(url)
    let subscribeTo = '/u/' + this.user.id
    let subscribeToSys = '/sys/*'
    $this.stompClient = Stomp.over(socket)

    $this.stompClient.connect({}, function (frame) {
      $this.stompClient.subscribe(subscribeTo, function (frame) {
        let msg = JSON.parse(frame.body)
        if (!$this.chats.has(msg.from)) {
          $this.chats.set(msg.from, [])
        }
        $this.chats.get(msg.from).push(msg)
      })
      $this.stompClient.subscribe(subscribeToSys, function (frame) {
        let msg = JSON.parse(frame.body)
        $this.users = msg.users
      })
      // 发送一条消息到系统，用于获取好友列表。
      $this.stompClient.send('/app/sys/users', {}, null)
    })
  },
  components: { Card, List, MsgEdit, Message }
}
</script>

<template>
<div id="app">
    <div class="sidebar">
        <Card :user="user" @search="setFilterKey" />
        <List :currentChatUser="currentChatUser" :users="users" :filterKey="filterKey" @chatWithUser="chatWithUser" />
    </div>
    <div class="main">
        <Message :user="user" :currentChatUser="currentChatUser" :messages="currentMessages" />
        <MsgEdit @sendMsg="sendMsg" />
    </div>
</div>
</template>

<style lang="less" scoped>
#app {
    margin: 20px auto;
    width: 800px;
    height: 600px;

    overflow: hidden;
    border-radius: 3px;

    .sidebar, .main {
        height: 100%;
    }
    .sidebar {
        float: left;
        width: 200px;
        color: #f4f4f4;
        background-color: #2e3238;
    }
    .main {
        position: relative;
        overflow: hidden;
        background-color: #eee;
    }
    .text {
        position: absolute;
        width: 100%;
        bottom: 0;
        left: 0;
    }
    .message {
        height: ~'calc(100% - 160px)';
    }
}
</style>

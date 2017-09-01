<script>

export default {
  props: ['currentChatUser', 'users', 'filterKey'],
  computed: {
    filteredUsers () {
      return this.users.filter(user => user.nickName.includes(this.filterKey))
    }
  },
  methods: {
    chatWithUser (user) {
      this.$emit('chatWithUser', user)
    }
  }
}
</script>

<template>
<div class="list">
    <ul>
        <li v-for="user in filteredUsers" :class="{ active: currentChatUser !=null && user.id === currentChatUser.id }" @click="chatWithUser(user)">
            <img class="avatar"  width="30" height="30" :alt="user.nickName" :src="'/static/images/' + user.img">
            <p class="name">{{user.nickName}}</p>
        </li>
    </ul>
</div>
</template>

<style scoped lang="less">
.list {
    li {
        padding: 12px 15px;
        border-bottom: 1px solid #292C33;
        cursor: pointer;
        transition: background-color .1s;

        &:hover {
            background-color: rgba(255, 255, 255, 0.03);
        }
        &.active {
            background-color: rgba(255, 255, 255, 0.1);
        }
    }
    .avatar, .name {
        vertical-align: middle;
    }
    .avatar {
        border-radius: 2px;
    }
    .name {
        display: inline-block;
        margin: 0 0 0 15px;
    }
}
</style>

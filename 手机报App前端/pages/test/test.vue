<template>
  <view class="container">
    <view class="list-item" v-for="(item, index) in list" :key="index">
      <image class="avatar" :src="item.avatar"></image>
      <text class="description">{{ item.description }}</text>
    </view>
    <view class="buttons">
      <button class="followed-btn" @click="onFollowed">已关注</button>
      <button class="unfollowed-btn" @click="onUnfollowed">无关注</button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      list: [
        {
          avatar: 'path/to/avatar1.png',
          description: '美丽与才华于一身的女子'
        },
        {
          avatar: 'path/to/avatar2.png',
          description: '美丽与才华于一身的女子'
        },
        // ...其他用户数据
      ]
    };
  },
  methods: {
    onFollowed() {
      console.log('已关注');
    },
    onUnfollowed() {
      console.log('无关注');
    }
  }
};
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20rpx;
}

.list-item {
  display: flex;
  align-items: center;
  margin-bottom: 20rpx;
}

.avatar {
  width: 60rpx;
  height: 60rpx;
  border-radius: 50%;
  margin-right: 20rpx;
}

.description {
  font-size: 32rpx;
}

.buttons {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.followed-btn,
.unfollowed-btn {
  width: 45%;
  height: 80rpx;
  line-height: 80rpx;
  text-align: center;
  background-color: #1aad19;
  color: #ffffff;
  font-size: 32rpx;
  border-radius: 10rpx;
}

.unfollowed-btn {
  background-color: #cccccc;
}
</style>

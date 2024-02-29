<template>
  <view class="content">
    <u-navbar title="新闻详情" :fixed="true" :left-icon="true"></u-navbar>
    <view class="article-container">
      <view class="article-title">Objective-C简介</view>
      <view class="article-date">时间：2023-05-10 13:49:44</view>
      <image class="article-image" src="/static/bendi/QQ图片20240120130608.jpg"></image>
      <view class="article-text">
        Objective-C 是一种编程语言的高级概念，设计初衷为了以支持面向对象的编程方式集成。Objective-C 源自美国苹果公司，方法以及属性的语法，沿用自他们以前语言的精华...
        <!-- 文章内容继续 -->
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      // 数据部分
    };
  },
  methods: {
    // 方法部分
  }
};
</script>

<style scoped>
.content {
  padding: 0;
}

.article-container {
  padding: 10px;
}

.article-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

.article-date {
  font-size: 12px;
  color: #888;
  margin-bottom: 10px;
}

.article-image {
	padding-left: 20rpx;
	padding-right: 20rpx;
	width: 600rpx;
	height: 400rpx;
	margin-bottom: 10px;
}

.article-text {
  font-size: 14px;
  line-height: 1.5;
  color: #333;
}
</style>
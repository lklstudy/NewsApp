<template>
  <view class="container">
    <view class="star"></view>
  </view>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.star {
  width: 0;
  height: 0;
  border-left: 50px solid transparent;
  border-right: 50px solid transparent;
  border-bottom: 100px solid red;
  position: relative;
}

.star::after {
  content: "";
  position: absolute;
  width: 0;
  height: 0;
  top: 35px;
  left: -50px;
  border-left: 50px solid transparent;
  border-right: 50px solid transparent;
  border-top: 100px solid red;
}
</style>

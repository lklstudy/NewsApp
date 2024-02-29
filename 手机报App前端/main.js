import App from './App'


// #ifndef VUE3
import Vue from 'vue';
import './uni.promisify.adaptor'

Vue.config.productionTip = false
App.mpType = 'app'

// 引入全局uView
import uView from "uview-ui";
Vue.use(uView);
import ClUni from "cl-uni";
Vue.use(ClUni);

const app = new Vue({
  ...App
})


app.$mount()
// #endif

// 引入请求封装，将app参数传递到配置中
// require('@/utils/request.js')(app)

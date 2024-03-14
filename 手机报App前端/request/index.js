// 全局请求封装
// api.js

const baseURL = 'http://localhost:9090' // 基础URL


// 封装网络请求函数
export function request(options) {
  const { url, method = 'GET', data, header = {} } = options;
  const token = uni.getStorageSync('token'); // 从本地存储中获取token

  let requestOptions = {
    url: baseURL + url,
    method,
    header: {
      'Authorization': 'Bearer ' + token, // 在header中添加token
      ...header
    }
  };

  if (method.toUpperCase() === 'GET') {
    requestOptions.data = data; // 将数据作为请求参数
  } else {
    requestOptions.data = JSON.stringify(data); // 将数据转换为字符串并作为请求体
  }

  return uni.request(requestOptions)
    .then(res => {
      if (res.statusCode === 200) {
        return res.data;
      } else {
        throw Error('网络请求失败');
      }
    })
    .catch(err => {
      console.error(err);
      throw Error('网络请求失败');
    });
}

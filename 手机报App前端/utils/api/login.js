const http = uni.$u.http

// post请求，获取菜单
// export const postMenu = (params, config = {}) => http.post('/ebapi/public_api/index', params, config)

//收藏列表
export const getMenu = (data) => http.get('/user/getUser',{params:{email:data}})
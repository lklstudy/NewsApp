// 使用 index.js 中封装的 request 函数
import { request } from './index.js'

// userMapper
export function updateUserInfo(data) {
  return request({
    url: '/user/updateUserInfo',
    method: 'POST',
    data
  })
}

export function follower(data) {
  return request({
    url: '/user/follower',
    method: 'POST',
    data
  })
}

export function login(data) {
  return request({
    url: '/user/login',
    method: 'POST',
    data
  })
}

export function getUserInfo(userEmail) {
  return request({
    url: '/user/getUserByEmail',
    method: 'GET',
	data: {
		userEmail: userEmail
	}
	
  })
}

export function getUserAllInfo(data) {
  return request({
    url: '/user/getUserAllInfo',
    method: 'POST',
	data
  })
}

export function addUser(data) {
  return request({
    url: '/user/addUser',
    method: 'POST',
    data
  })
}


// newsMapper
export function addNews(data) {
  return request({
    url: '/news/addNews',
    method: 'POST',
	data
  })
}

export function delOrUpdateNews(data) {
  return request({
    url: '/news/delOrUpdateNews',
    method: 'POST',
	data
  })
}

export function searchCollectionNews(data) {
  return request({
    url: '/news/searchCollectionNews',
    method: 'POST',
	data
  })
}


export const getNewsByTitle = (data) => {
  // 方法实现
  return request({
    url: '/news/getNewsByTitle',
    method: 'POST',
  	data
  })
};

export function getNewsById(data) {
  return request({
    url: '/news/getNewsById',
    method: 'GET',
    data: {
    	newsID: data.newsID,
		userID: data.userID
    }
  })
}

export function getType(data) {
  return request({
    url: '/news/getType',
    method: 'GET',
    data: {
		
    }
  })
}

export function addType(typeName,userID) {
  return request({
    url: '/news/addType',
    method: 'GET',
    data: {
		typeName: typeName,
		userID: userID
    }
  })
}

export function delAndEditType(typeName,typeID) {
  return request({
    url: '/news/delAndEditType',
    method: 'GET',
    data: {
		typeName: typeName,
		typeID: typeID
    }
  })
}


//commentMapper
export function getCommentById(newsID) {
  return request({
    url: '/comment/getCommentById',
    method: 'GET',
    data: {
    	newsID: newsID
    }
  })
}

export function likeandcollection(data) {
  return request({
    url: '/comment/likeandcollection',
    method: 'GET',
    data: {
    	newsID: data.newsID,
		userID: data.userID,
		type: data.type,
		status: data.status
    }
  })
}

export function addComment(data) {
  return request({
    url: '/comment/addComment',
    method: 'POST',
	data
  })
}

//图片
export function addPhoto(data) {
  return request({
	headers: {
	  'Content-Type': 'multipart/form-data'
	},
    url: '/api/uploadImage',
    method: 'POST',
	data
  })
}

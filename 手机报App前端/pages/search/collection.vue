<template>
	<view class="page-search">
		<view class="search">
			<cl-search class="search2" placeholder="搜索文章" @search="handleEnter()" v-model="searchValue"></cl-search>
			
		</view>
		<!-- 今日热点 -->
		
		<view class="rank" v-if="list.length>0">
		<view class="head">
			<view class="icon">
				<image src="/static/icon/topic.png" />
			</view>
			<view class="title">我的收藏列表</view>
		</view>
		<u-gap height="3" bgColor="#F3531E"></u-gap>
		<view class="list">
			<view class="item" v-for="(item, index) in list" :key="index" @tap="toDetail(item)">
				<view class="det">
					<view class="title" >
						<cl-text
							:value="item.title"
							color="#000000"
							:size="32"
							:ellipsis="2"
						></cl-text>
					</view>
					<view class="count" style="display: flex;justify-content: space-between;" >
						
						<view class="count-item">{{ item.createTime }}</view>
						
					</view>
				</view>
				<view class="cover" >
					<image :src="item.newsAtar" mode="" />
				</view>
			</view>
		</view>
		</view>
	
		<!-- 弹出提示 -->
		<u-popup :show="show" mode="center"  bgColor="transparent">
			<view class="success-tips">
				删除成功！
			</view>
		</u-popup>
	</view>
</template>

<script>
	import { getNewsByTitle,searchCollectionNews } from '@/request/api.js';
export default {
	data() {
		return {
			searchValue: '',
			show: false,
			show3: false,
			user:"",
			hotTitle: "输入标题搜索",
			searchText: "",
			hot: [],
			list:[]
		};
	},onShow(){
		this.user = uni.getStorageSync('token');
		if(!this.user){
			uni.redirectTo({
				url:"/pages/login/login"
			})
		}
		this.getData();
	},

	methods:{
		handleEnter(){
				console.log(this.searchValue); 
				 this.getData()
		},
		toPublish(item) {
			uni.navigateTo({
				url: "/pages/publish/index?id="+item.newsID,
			});
		},
		getData(){
			var token = uni.getStorageSync('token');
			console.log(123);
			var _this = this;
			_this.list = []
			searchCollectionNews({userID: token,searchValue: this.searchValue})
			.then((res) => {
				console.log(res)
				if (res.code == 200) {
					_this.list = res.data
				}else{
					uni.showToast({
						title:"获取失败",
						icon:"none"
					})
				}
			  })
		},
		// getData(){
		// 	let data = {
		// 			validFlag:1,
		// 			title:this.searchText
		// 		}
		// 	if(this.user.type == 0){
		// 		data['creater'] = this.user.uid;
		// 	}	
		// 	var _this = this;
		// 	appRequest.request({
		// 		method: "POST",
		// 		url: appRequest.urlMap.findNmArticleList,
		// 		data:data,
		// 		success: function(res) {
		// 			if (res.data.code == 200) {
		// 				let data = res.data.data;
		// 				_this.list = data;
		// 				console.log(JSON.stringify(data));
		// 			}else{
		// 				uni.showToast({
		// 					title:"获取失败",
		// 					icon:"none"
		// 				})
		// 			}
		// 		},
		// 		fail: function(res) {
		// 			uni.showToast({
		// 				title:"网络异常",
		// 				icon:"none"
		// 			})
		// 		}
		// 	})
		// },
		toDetail(item) {
			uni.navigateTo({
				url: "/pages/detail/detail?id="+item.newsID,
			});
		}
		
	}
};
</script>

<style lang="scss">
.page-search {
	.search {
		display: flex;
		align-items: center;
		width: 100%;
		height: 123rpx;
		padding: 0 10rpx;
		background-color: #f95859;
		box-sizing: border-box;
		.search2{
			width: 100%;
			height: 100%;
			background-color: #F95859;
		}

	}
	.hot {
		padding: 30rpx 12rpx 10rpx 30rpx;
		.head {
			display: flex;
			align-items: center;
			margin-bottom: 41rpx;

			.icon {
				margin-right: 22rpx;
				image {
					width: 35rpx;
					height: 35rpx;
				}
			}
			.title {
				font-size: 36rpx;
				font-weight: 400;
				color: #000000;
			}
		}
		.list {
			display: flex;
			flex-wrap: wrap;
		}
		.item {
			height: 66rpx;
			margin-right: 18rpx;
			margin-bottom: 20rpx;
			padding: 0 38rpx;
			background-color: #f2f2f2;
			border-radius: 33rpx;
			line-height: 66rpx;
			font-size: 28rpx;
			font-weight: 400;
			color: #000000;
		}
	}
	.rank {
		padding: 30rpx 40rpx;
		.head {
			display: flex;
			align-items: center;
			margin-bottom: 46rpx;
			.icon {
				width: 35rpx;
				height: 35rpx;
				margin-right: 22rpx;
				image {
					width: 35rpx;
					height: 35rpx;
				}
			}
			.title {
				font-size: 40rpx;
				font-weight: 600;
				color: #F95859;
				
		}
		}
		.list {
			width: calc(100% - 60rpx);
			padding-top: 30rpx;
			margin: 0 30rpx;
			.item {
				border-bottom: 1rpx solid #eeeeee;
				display: flex;
				align-items: center;
				margin-bottom: 38rpx;
				.det {
					margin-right: 66rpx;
					flex: 1;
					/deep/.cl-text {
						line-height: 40rpx;
					}
					.count {
						display: flex;
						align-items: center;
						margin-top: 33rpx;
						.is-top {
							width: 75rpx;
							height: 32rpx;
							margin-right: 20rpx;
							background-color: #f95859;
							color: #f5c7cc;
							border-radius: 16rpx;
							font-size: 22rpx;
							text-align: center;
						}
						&-item {
							margin-right: 20rpx;
							font-size: 22rpx;
							font-weight: 400;
							color: #999999;
						}
					}
				}
				.cover {
					width: 260rpx;
					height: 147rpx;
					border-radius: 8rpx;
					overflow: hidden;
					image {
						width: 100%;
						height: 100%;
					}
				}
			}
		}
		.item {
			display: flex;
			align-items: center;
			position: relative;
			margin-bottom: 24rpx;
		}
		.num {
			margin-right: 34rpx;
			color: #ffab6a;
		}
		.is-three {
			color: #fe273f;
		}
		.name {
			width: 80%;
			font-size: 28rpx;
			font-weight: 400;
			color: #000000;
		}
		.tag {
			position: absolute;
			right: 0;
		}
		.is-hot {
			width: 34rpx;
			height: 34rpx;
			background-color: #fe273f;
			border-radius: 10rpx;
			text-align: center;
			font-size: 20rpx;
			font-weight: 400;
			color: #ffffff;
		}
		.is-new {
			width: 34rpx;
			height: 34rpx;
			background-color: #f6b37f;
			border-radius: 10rpx;
			text-align: center;
			font-size: 20rpx;
			font-weight: 400;
			color: #ffffff;
		}
	}
}
</style>

<template>
	<view class="page-search">
		<view class="search">
			<view class="input">
				<cl-input @confirm="getData()"
					prefix-icon="cl-icon-search"
					:placeholder="hotTitle"
					v-model="searchText"
				></cl-input>
			</view>
			
		</view>
		<!-- 今日热点 -->
		
		<view class="rank" v-if="list.length>0">
		<view class="head">
			<view class="icon">
				<image src="/static/icon/topic.png" />
			</view>
			<view class="title">评论列表</view>
		</view>
		<view class="list">
			<view class="item" v-for="(item, index) in list" :key="index" >
				<view class="det">
					<view class="title"  @tap="toDetail(item)">
						<cl-text
							:value="item.content"
							color="#000000"
							:size="32"
							:ellipsis="2"
						></cl-text>
					</view>
					<view class="count" style="width: 100%;display: flex;justify-content: space-between;">
						
						<view class="count-item">{{ item.createTime }}</view>
						<view @click="delComment(item)" style="color: #f95859;">删除</view>
						
					</view>
				</view>
				<view class="cover">
					<image :src="item.contentAtar" mode="" />
				</view>
			</view>
		</view>
		</view>
	</view>
</template>

<script>
	// import appRequest from "@/common/appRequestUrl.js";
export default {
	data() {
		return {
			user:"",
			hotTitle: "输入标题搜索",
			searchText: "",
			hot: [],
			list:[
				{
					content: "dawdawdaw",
					createTime: "23123-12312",
					contentAtar: "http://localhost:9090/images/20240305134008.jpg"
				}
			]
		};
	},onShow(){
		this.user = appRequest.getUserInfo();
		if(!this.user){
			uni.redirectTo({
				url:"/pages/login/index"
			})
		}
		this.getData();
	},onLoad() {
		
	},methods:{
		getPicUrl(info) {
			if(info){
				if( info.length > 10 ){
					return info;
				}else {
					return appRequest.urlMap.getPicById + info;
				}
			}else {
				return "";
			}
		
		},getData(){
			let data = {
					validFlag:1,
					content:this.searchText
				}
			if(this.user.type == 0){
				data['creater'] = this.user.uid;
			}	
			var _this = this;
			appRequest.request({
				method: "POST",
				url: appRequest.urlMap.findNmCommentList,
				data:data,
				success: function(res) {
					if (res.data.code == 200) {
						let data = res.data.data;
						_this.list = data;
						console.log(JSON.stringify(data));
					}else{
						uni.showToast({
							title:"获取失败",
							icon:"none"
						})
					}
				},
				fail: function(res) {
					uni.showToast({
						title:"网络异常",
						icon:"none"
					})
				}
			})
		},toDetail(item) {
			uni.navigateTo({
				url: "/pages/news/detail?id="+item.aid,
			});
		},delComment(item){
			var _this = this;
			appRequest.request({
				method: "POST",
				url: appRequest.urlMap.editNmComment,
				data:{
					validFlag:0,
					id:item.id
				},
				success: function(res) {
					if (res.data.code == 200) {
						uni.showToast({
							title:"删除成功",
							icon:"none"
						})
						_this.getData();
					}else{
						uni.showToast({
							title:"删除失败",
							icon:"none"
						})
					}
				},
				fail: function(res) {
					uni.showToast({
						title:"网络异常",
						icon:"none"
					})
				}
			})
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
		padding: 0 35rpx;
		background-color: #f95859;
		box-sizing: border-box;

		.input {
			flex: 1;
		}

		image {
			width: 38rpx;
			height: 35rpx;
			margin-left: 30rpx;
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
				font-size: 36rpx;
				font-weight: 400;
				color: #000000;
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
					width: 205rpx;
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

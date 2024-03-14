<template>
	<view class="page-my">
		<!-- 用户信息 -->
		<view class="info">
			<view class="avatar">
				<image :src="user.avatar" />
			</view>
			<view class="name"><cl-text :value="user.username" :size="48" color="#fff"></cl-text></view>
			<view class="introduction">
<!-- 				<cl-text
					:value="info.introduction"
					:size="24"
					color="#FF9D9D"
					:ellipsis="1"
				></cl-text > -->
			</view>
		</view>
		<!-- 统计 -->
		<view class="count">
			<view @click="jump(index)" class="item" v-for="(item, index) in count" :key="index">
				<view class="icon">
					<image :src="item.icon" />
				</view>
				<view class="name">
					{{ item.name }}
				</view>
			</view>
		</view>
		<!-- 线 -->
		<view class="line"></view>
		<!-- 导航 -->
		<view class="nav">
			<cl-list-item label="修改信息" border @click="visible=true;">
				<text slot="append" class="cl-icon-arrow-right"></text>
			</cl-list-item>
			<cl-list-item label="智能客服" border @click="xiaosi()">
				<text slot="append" class="cl-icon-arrow-right"></text>
			</cl-list-item>
			<cl-list-item label="退出登录" border @click="show3 = true">
				<text slot="append" class="cl-icon-arrow-right"></text>
			</cl-list-item>
		</view>
		
		<cl-popup
			ref="popup"
			:visible.sync="visible"
			direction="bottom"
			radius="20rpx 20rpx 0 0"
			@close="visible=false;"
		>
			<cl-form label-position="top">
				<cl-form-item label="昵称">
					<cl-input v-model="user.username"></cl-input>
				</cl-form-item>
				<text style="margin-left: 3rpx; font-family: 'Courier New', Courier, monospace;font-size: 35rpx; color: #191919;font-weight: 550;">更换图片</text>
				
				<!-- 上传 -->
				<view class="upload" style="margin-top: 26rpx;">
					<!-- 上传文章图片文字 -->
					<u--image :showLoading="true" :src="tempAtar" width="180rpx" height="180rpx" @click="uploadImage"></u--image>
				</view>
			</cl-form>
		
			<view class="footer" style="margin-top:30rpx; margin-bottom: 10vh;">
				<cl-row :gutter="20">
					<cl-col :span="12">
						<cl-button fill round @tap="visible=false;getUserInfo();">取消</cl-button>
					</cl-col>
					<cl-col :span="12">
						<cl-button fill round type="primary" @tap="submit">提交</cl-button>
					</cl-col>
				</cl-row>
			</view>
		</cl-popup>
		<u-modal :show="logonShow" :title="title" :content='content'></u-modal>
		
		<u-modal
			content="是否退出登录!"
			:show="show3"
			showCancelButton
			closeOnClickOverlay
			@confirm="confirm"
			@cancel="cancel"
		></u-modal>
		
		<u-popup :show="show" mode="center"  bgColor="transparent">
			<view class="success-tips">
				修改成功！
			</view>
		</u-popup>
	</view>
</template>

<script>
	import ygyUploadImg from '@/components/ygy-upload-img/ygy-upload-img.vue'
	// import appRequest from "@/common/appRequestUrl.js";
	import { getUserAllInfo,uploadImage,updateUserInfo } from '@/request/api.js';
export default {
	components: {
		ygyUploadImg
	},
	data() {
		return {
			show: false,
			tempAtar: '',
			show3: false,
			logonShow: false,
			visible:false,
			user:{},
			info: {
				iconCloseStyle: { //关闭图标样式
					fontSize: '60rpx',
					color: "#f40"
				}
			},
			count: [
				{ icon: require("@/static/icon/我的文章.png"), name: "我的文章" },
				{ icon: require("@/static/icon/取消收藏.png"), name: "我的收藏" },
				{ icon: require("@/static/icon/我的关注.png"), name: "我的关注" }
			],count2: [
				{ icon: require("@/static/icon/hot.png"), name: "文章管理" },
				{ icon: require("@/static/icon/hot.png"), name: "评论管理" },
				{ icon: require("@/static/icon/hot.png"), name: "分类管理" }
			],
		};
	},onShow() {
		var token = uni.getStorageSync('token');
		getUserAllInfo({userID: token})
		.then((response) => {
			let res = response.data;
			console.log(res)
			this.user = res
			this.tempAtar = res.avatar
		});
		if(!this.user){
			uni.redirectTo({
				url:"/pages/login/index"
			})
		}
		if(this.user.type == 1){
			this.count = this.count2;
		}
	},
	methods: {
		uploadImage() {
		  uni.chooseImage({
		    count: 1,
		    sizeType: ['original'],
		    sourceType: ['album', 'camera'],
		    success: (chooseRes) => {
		      const tempFilePath = chooseRes.tempFilePaths[0];
		      
		      uni.uploadFile({
		        url: 'http://localhost:9090/upload',
		        filePath: tempFilePath,
		        name: 'file',
		        formData: {},
		        success: (uploadRes) => {
					// 将字符串转换为 JSON 对象
				  var jsonObject = JSON.parse(uploadRes.data);
				  var imageUrl = jsonObject.data;
				  this.tempAtar = imageUrl;
		          console.log(imageUrl);
		        },
		        fail: (error) => {
		          console.log('图片上传失败', error);
		        }
		      });
		    }
		  });
		},
		confirm() {
			this.logOut()
		},
		cancel() {
			this.show3 = false
			console.log('cancel');
		},
		logOut(){
			var token = uni.getStorageSync('token');
			if (token) {
			    uni.removeStorageSync('token');
			}
			uni.navigateTo({
				url: "/pages/login/login",
			});
			
		},
		jump(index){
			if(index==0){
				uni.navigateTo({
					url: "/pages/search/article",
				});
			}else if(index==1){
				uni.navigateTo({
					url: "/pages/search/collection",
				});
			}else if(index==2){
				uni.navigateTo({
					url: "/pages/my/my",
				});
			}
		},
		xiaosi(){
			uni.navigateTo({
				url: "/pages/xiaoSi/xiaoSi",
			});
		},
		submit(){
			var _this = this;
			updateUserInfo({userID: _this.user.userID,userName : _this.user.username,userAtar: _this.tempAtar})
			.then((res) => {
				if (res.code == 200) {
					this.show = true
					setTimeout(() => {
						this.show = false;
						this.show3 = false
					}, 1200);
				}else{
					uni.showToast({
						title:"获取失败",
						icon:"none"
					})
				}
			  })
			
		},
		getUserInfo(){
			this.user = appRequest.getUserInfo();
		},
		// logOut(){
		// 	uni.clearStorage();
		// 	uni.navigateTo({
		// 		url: "/pages/login/index",
		// 	});
		// },
		toMessage() {
			uni.navigateTo({
				url: "/pages/message/index",
			});
		},
		getImgList(arr){
			this.user.pic = arr[0];
		},
		// myAttention() {
		// 	uni.navigateTo({
		// 		url: "/pages/login/index",
		// 	});
		// },
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
		
		}
	},
};
</script>

<style lang="scss">
.page-my {
	.info {
		width: 100%;
		padding: 38rpx 30rpx;
		background-color: #f5393b;
		box-sizing: border-box;
		.avatar {
			width: 129rpx;
			height: 129rpx;
			margin: 0 auto;
			border-radius: 50%;
			overflow: hidden;
			image {
				width: 100%;
				height: 100%;
			}
		}
		.name {
			margin-top: 16rpx;
			text-align: center;
		}
		.introduction {
			margin-top: 12rpx;
			padding: 0 30rpx;
			text-align: center;
		}
		.avatar {
			width: 129rpx;
			height: 129rpx;
			margin: 0 auto;
			border-radius: 50%;
			overflow: hidden;
			image {
				width: 100%;
				height: 100%;
			}
		}
		.name {
			margin-top: 16rpx;
			text-align: center;
		}
		.introduction {
			margin-top: 12rpx;
			padding: 0 30rpx;
			text-align: center;
		}
	}

	.count {
		display: flex;
		padding: 46rpx 34rpx;
		.item {
			display: flex;
			flex-direction: column;
			align-items: center;
			width: 84rpx;
			text-align: center;
			flex: 1;
		}
		.icon {
			display: flex;
			justify-content: center;
			align-items: center;
			width: 50rpx;
			height: 50rpx;
			margin-bottom: 28rpx;
			image {
				max-width: 50rpx;
				max-height: 50rpx;
			}
		}
		.name {
			font-size: 22rpx;
			font-weight: 400;
			color: #333333;
		}
	}
	.line {
		width: 100%;
		height: 15rpx;
		background-color: #fafafc;
	}
	.nav {
		padding: 0 32rpx;
		.cl-list-item {
			padding: 4rpx 6rpx;
		}
		.cl-list-item__label {
			font-size: 28rpx;
			font-weight: 400;
			color: #252737;
		}
		text {
			color: #cacad6;
		}
	}
}
</style>

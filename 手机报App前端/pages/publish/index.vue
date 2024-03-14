<template>
	<view class="page-publish">
		<view class="topbar">
			<cl-topbar title="发布内容" color="#fff" background-color="#F5393B">
				<template slot="append">
					
				</template>
			</cl-topbar>
		</view>
		<view class="content">
			<cl-form-item label="标题">
				<cl-input  placeholder="输入文章标题" v-model="data.title"></cl-input>
			</cl-form-item>
		</view>
	
		
		<view class="content">
			<cl-form-item label="分类">
				<cl-select :border="true" label-key="typeName" value-key="typeID" :placeholder="typeName" v-model="data.type" :options="list"></cl-select>
			</cl-form-item>
		</view>
		
		
		<!-- 文本域 -->
		<view class="content">
			<cl-form-item label="正文">
				<cl-textarea  maxlength="300" :auto-height="true" v-model="data.content" placeholder="输入文章正文"></cl-textarea>
			</cl-form-item>
		</view>
		
		<text style="margin-left: 44rpx; font-family: 'Courier New', Courier, monospace;font-size: 35rpx; color: #F5393B;font-weight: 600;">上传新闻图片</text>

		<!-- 上传 -->
		<view class="upload">
			<!-- 上传文章图片文字 -->
			<u--image :showLoading="true" :src="data.src" width="180rpx" height="180rpx" @click="uploadImage"></u--image>
		</view>

		<view style="margin-top: 30rpx;padding: 20rpx;" >
			<cl-button :fill="true" type="primary" @click="editData()">{{buttnName}}</cl-button>
		</view>
		
		<!-- 弹出提示 -->
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
	import { addPhoto, login,getType,addNews,getNewsById,delOrUpdateNews } from '@/request/api.js';
export default {
	components: {
		ygyUploadImg
	},
	data() {
		return {
			show: false,
			option: 1,
			typeName: "选择文章分类",
			buttnName: "提交",
			fileList1: [],
			id:"",
			data:{
				title:"",
				content:"",
				src: "/static/icon/发布2.png",
				pic1Str:"",
				type:"",
				userID: ""
			},
			user:"",
			info: {
				iconCloseStyle: { //关闭图标样式
					fontSize: '60rpx',
					color: "#f40"
				}
			},
			list:["awd","awdaw"],
			imgList: [], // 图片列表
		};
	},onLoad(option){
		this.getType()
		this.id = option.id || "";
		console.log("id:" + this.id)
		if(this.id){
			this.buttnName = "修改"
			var token = uni.getStorageSync('token');
			console.log(123123)
			getNewsById({ newsID: this.id, userID: token })
			.then((response) => {
				let res = response.data;
				console.log(res)
				this.data.title = res.title
				this.data.content = res.content
				this.data.src = res.newsAtar
				this.typeName = res.typeName
				this.data.type = res.type
				this.data.newsID = this.id
				this.data.option = 2
				this.option = 2
			})
		}
	
		
	},onShow() {
		this.user = appRequest.getUserInfo();
		if(!this.user){
			uni.switchTab({
				url:"/pages/index/home"
			})
		}
	},methods:{

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
				  this.data.src = imageUrl;
		          console.log(imageUrl);
		        },
		        fail: (error) => {
		          console.log('图片上传失败', error);
		        }
		      });
		    }
		  });
		},

		getImgList(arr){
			this.data.pic1Str = arr[0];
		},
		setImgList(pic){
			if(pic){
				return [this.getPicUrl(pic)];
			}else{
				return [];
			}
		},
		getType(){
			var _this = this;
			getType()
			.then((res) => {
				if (res.code == 200) {
					_this.list = res.data;
					console.log(_this.list);
				}else{
					uni.showToast({
						title:"获取失败",
						icon:"none"
					})
				}
			  })
		},
		editData(option){
			if(this.option == 1){
				if(!this.data.title || !this.data.content || !this.data.type || this.data.src == "/static/icon/发布2.png"){
					this.$api.msg("请填写完整");
					return;
				}
				var token = uni.getStorageSync('token');
				this.data.userID = token
				var _this = this;
				addNews(this.data)
				.then((res) => {
					if (res.code == 200) {
						uni.showModal({
							title:"提示",
							content:"操作成功",
							showCancel:false,
							success() {
								uni.navigateBack();
							}
						})
					}else{
						uni.showToast({
							title:"获取失败",
							icon:"none"
						})
					}
				  })
			}else{
				delOrUpdateNews(this.data)
				.then((res) => {
					console.log(res)
					if (res.code == 200) {
						this.show = true;
						setTimeout(() => {
							this.show = false;
							this.getData();
						}, 800);
					}else{
						uni.showToast({
							title:"获取失败",
							icon:"none"
						})
					}
				  })
				console.log(this.data)
			}
			
			
		},
		getData(){
			var _this = this;
			appRequest.request({
				method: "POST",
				url: appRequest.urlMap.findNmArticle,
				data:{
					id:this.id,
					validFlag:1
				},
				success: function(res) {
					if (res.data.code == 200) {
						_this.data = res.data.data;
						_this.data['pic1Str'] = res.data.data.pic1;
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
		},
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
	}
};
</script>

<style lang="scss">
page {
	height: 100%;
}
.page-publish {
	position: relative;
	height: 100%;
	.topbar {
		.cl-topbar__icon {
			padding: 0 40rpx 0 0;
		}
		/deep/ .cl-button__text {
			color: #ffffff;
			font-size: 26rpx;
		}
	}
	.content {
		padding: 46rpx 40rpx;
		.cl-textarea {
			min-height: 378rpx;
		}
	}
	.upload {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 237rpx;
		height: 237rpx;
		margin-left: 19rpx;
		border-radius: 10rpx;
	}
	.position {
		display: flex;
		justify-content: center;
		align-items: center;
		position: absolute;
		left: 40rpx;
		bottom: 112rpx;
		width: 184rpx;
		height: 57rpx;
		border: 1rpx solid #eeeeee;
		border-radius: 29rpx;
	}
	.bottom {
		display: flex;
		align-items: center;
		position: fixed;
		bottom: 0;
		width: 100%;
		height: 94rpx;
		padding: 0 34rpx;
		box-shadow: 0 6rpx 10rpx 0 rgba(0, 0, 0, 0.06);
		box-sizing: border-box;
		.keyboard {
			flex: 1;
			image {
				width: 41rpx;
				height: 45rpx;
			}
		}
		.features {
			margin-right: 58rpx;
		}
	}
	textarea {
	  width: 100%;
	  height: 100px;
	  margin-top: 10px;
	}
	
	.square {
	  width: 100px;
	  height: 100px;
	  background-color: lightgray;
	  margin-top: 20px;
	}
	
}
</style>

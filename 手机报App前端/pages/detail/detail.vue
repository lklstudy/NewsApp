<template>
	<view class="page-detail">
		<view class="title" style="margin-bottom: 30rpx;">{{ det.title }}</view>
		<view class="user"> 
			作者：{{ det.userName }} 
            <button @click="show3 = true" v-if="isShowGuanzhu" :class="guanzhuStatus" style="width: 140rpx; font-size:xx-small;">{{guanzhuContent}}</button>
		</view>
		<view class="time"> 时间：{{ det.createTime }} </view>
		<view class="cover">
			<image :src="det.img" />
		</view>
		
		<view class="content">
			{{ det.content }}
		</view>

	
		<!-- <view class="evalution" v-if="commentList.length>0"> -->
			<view class="section-tit">评论</view>
			
			<view class="eva-list" :class="{Skeleton:!loaded}">
				<view  v-for="(item, index) in commentList" :key="index"
					class="eva-item"
				>
					<image :src="item.avatar" mode="aspectFill"></image>
					<view class="eva-right" :class="index == commentList.length - 1 ? '':'border-bottom' ">
						<text>{{item.username}}</text>
						<text>{{item.commentTime}}</text>
						<text class="content">{{item.content}}</text>
					</view>
				</view>
			</view>
		<!-- </view> -->
		
		<cl-popup
			ref="popup"
			:visible.sync="visible"
			direction="bottom"
			radius="20rpx 20rpx 0 0"
			@close="content='';visible=false;"
		>
			<cl-form label-position="top">
				<cl-form-item label="评论">
					<cl-textarea v-model="content" placeholder="请输入评论"></cl-textarea>
				</cl-form-item>
			</cl-form>
		
			<view class="footer">
				<cl-row :gutter="20">
					<cl-col :span="12">
						<cl-button fill round @tap="content='';visible=false;">取消</cl-button>
					</cl-col>
					<cl-col :span="12">
						<cl-button fill round type="primary" @tap="submit">提交</cl-button>
					</cl-col>
				</cl-row>
			</view>
		</cl-popup>
		
		<!-- 底部功能区 -->
		<view class="bottom">
			<view class="input" @click="visible=true;">
				<cl-input prefix-icon="cl-icon-edit" placeholder="请输入评论" round></cl-input>
			</view>
			<view class="feature"  @click="setCollect(myLike,1)">
				<cl-icon v-if="myLike" name="cl-icon-good-fill" color="#FFE153"></cl-icon>
				<cl-icon v-else name="cl-icon-good-fill" color="#999999"></cl-icon>
				<cl-text :value="likesNumber" color="#999999" :size="28"></cl-text>
			</view>
			<view class="feature" @click="setCollect(myCollect,0)">
				<cl-icon v-if="myCollect" name="cl-icon-like-fill" color="#F95859"></cl-icon>
				<cl-icon v-else name="cl-icon-like-fill" color="#999999"></cl-icon>
				<cl-text :value="collectNumber" color="#999999" :size="28"></cl-text>
			</view>
		</view>
		
		<cl-dialog :visible="show" width='50%'>
			<text>评论成功</text>
		</cl-dialog>
		
		<u-modal
			:content=combinedMessage
			:show="show3"
			showCancelButton
			closeOnClickOverlay
			@confirm="confirm"
			@cancel="cancel"
		></u-modal>
		
	</view>
</template>

<script>
	import { follower,getNewsById,getCommentById,likeandcollection,addComment } from '@/request/api.js';
	export default {
		data() {
			return {
				show3: false,
				newUserID: '',
				isShowGuanzhu: false,
				guanzhuStatus: 'round margin-lr-sm cu-btn round bg-yellow',
				guanzhuContent: '关注',
				show: false,
				newsId: '',
				content:"",
				user:{},
				id:"",
				visible:false,
				commentList:[
				],
				det: {
				},
				likesNumber: 0,
				collectNumber:0,
				myLike:false,
				myCollect:false,
				feature: {
					like: 3568,
					comment: 356,
					forward: 368,
				},
				loaded:true
			}
		},
		computed: {
			combinedMessage() {
				var userName = this.det.userName
				if(this.guanzhuContent == "关注"){
					return "是否关注作者";
				}
				if(this.guanzhuContent == "已关注"){
					return "是否取消关注作者";
				}
			
			}
		},
		methods: {
			confirm() {
				this.followerAddOrDel()
			},
			cancel() {
				this.show3 = false
				console.log('cancel');
			},
			followerAddOrDel(){
				this.show3 = true
				var option = ''
				var token = uni.getStorageSync('token');
				if(this.guanzhuContent == '关注'){
					option = 1
					follower({followedID: this.newUserID,userID:token,content: this.content,option: option})
					.then((response) => {
						let res = response.data;
						console.log(res)
						if(response.code == 200){
							console.log(1);
							this.guanzhuContent = '已关注';
							this.guanzhuStatus = "round margin-lr-sm cu-btn round bg-gray";
							this.show3 = false
						}
					});
				}else{
					option = 2
					follower({followedID: this.newUserID,userID:token,content: this.content,option: option})
					.then((response) => {
						let res = response.data;
						console.log(res)
						if(response.code == 200){
							console.log(2);
							this.guanzhuContent = '关注'
							this.guanzhuStatus = "round margin-lr-sm cu-btn round bg-yellow"
							this.show3 = false
						}
					});
				}
			},
			submit(){
				var token = uni.getStorageSync('token');
				addComment({newsID: this.newsId,userID:token,content: this.content})
				.then((response) => {
					let res = response.data;
					console.log(res)
					setTimeout(() => {
						this.show = true,
						this.visible = false
						setTimeout(() => {
							this.show = false
							getCommentById(this.newsId)
							.then((response) => {
								let res = response.data;
								console.log(res)
								this.commentList = res
							});
						}, 1000);
					}, 500);	
				});
			},
			setCollect(status,type){
				var token = uni.getStorageSync('token');
				if(type == 1){
					if(this.myLike == false){
						this.likesNumber += 1 
						likeandcollection({ newsID: this.newsId, userID: token ,type: 1,status: 1})
					}else{
						this.likesNumber -= 1
						likeandcollection({ newsID: this.newsId, userID: token ,type: 1,status: 0})
					}
					this.myLike = !status
				}else{
					if(this.myCollect == false){
						this.collectNumber += 1 
						likeandcollection({ newsID: this.newsId, userID: token ,type: 0,status: 1})
					}else{
						this.collectNumber -= 1
						likeandcollection({ newsID: this.newsId, userID: token ,type: 0,status: 0})
					}
					this.myCollect = !status
				}
			}
			
		},
		onLoad: function(options) {
		  // 获取传递过来的新闻 id 参数
		  const newsId = options.id;
		  this.newsId = newsId
		  this.visible = false
		  var token = uni.getStorageSync('token');
		  
		  getNewsById({ newsID: newsId, userID: token })
		  .then((response) => {
			let res = response.data;
			console.log(res)
			this.newUserID = res.userID
			this.isShowGuanzhu = (res.userID == token ? false : true)
			this.myLike = (res.myLike == 1 ? true : false)
			this.myCollect = (res.myCollect == 1 ? true : false)
			this.det.title = res.title
			this.det.createTime = res.createTime
			this.det.img = res.newsAtar
			this.det.content = res.content
			this.det.userName = res.username
			this.likesNumber = res.likesNumber
			this.collectNumber = res.collectNumber
			
			return follower({followedID: this.newUserID,userID:token,content: this.content,option: 3})
		  })
		  .then((response) => {
		  	let res = response.data;
		  	if(response.code == 200){
				if(res == 0){
					this.guanzhuContent = '关注'
					this.guanzhuStatus = "round margin-lr-sm cu-btn round bg-yellow"
				}else{
					this.guanzhuContent = '已关注';
					this.guanzhuStatus = "round margin-lr-sm cu-btn round bg-gray";
				}
		  	}
		  });
		  
		  getCommentById(newsId)
		  .then((response) => {
		  			let res = response.data;
		  			console.log(res)
					this.commentList = res
		  });
		  
		
		//    follower({followedID: this.newUserID,userID:token,content: this.content,option: 3})
		//   .then((response) => {
		//   	let res = response.data;
		//   	console.log(res)
		//   	if(response.code == 200){
		//   		console.log(2);
		//   		this.guanzhuContent = '关注'
		//   		this.guanzhuStatus = "round margin-lr-sm cu-btn round bg-yellow"
		//   	}
		//   });

			
			
		  
		  // 在这里可以根据新闻 id 请求相应的新闻详情数据或进行其他操作
		}
	}
</script>

<style lang="scss">
	@import "/colorui/关注ColorUI/colorui/main.css";
	@import "/colorui/关注ColorUI/colorui/icon.css";

.page-detail {
	padding: 50rpx 0;
	.title {
		padding: 0 30rpx;
		font-size: 42rpx;
		font-weight: 400;
		color: #20263c;
		line-height: 56rpx;
	}
	.time {
		margin: 26rpx 0 62rpx 0;
		padding: 0 30rpx;
		font-size: 24rpx;
		font-weight: 400;
		color: #9b9b9b;
	}
	.user {
		margin: 12rpx 0 36rpx 0;
		padding: 0 30rpx;
		font-size: 20rpx;
		font-weight: 400;
		color: #9b9b9b;
	}
	.cover {
		margin-bottom: 65rpx;
		padding: 0 30rpx;
		image {
			width: 678rpx;
		}
	}
	.content {
    font-size: 30rpx;
		font-weight: 400;
		color: #333333;
		line-height: 65rpx;
	}

  .content {
    padding: 0 30rpx 40rpx 30rpx;
  }

  .bottom {
		display: flex;
		align-items: center;
		position: fixed;
		bottom: 0;
		width: 100%;
		height: 108rpx;
		padding: 0 30rpx;
		box-shadow: 0px 6rpx 10rpx 0px rgba(0, 0, 0, 0.06);
		box-sizing: border-box;
		.input {
			margin-right: 28rpx;
			flex: 1;
			.cl-input {
				height: 60rpx;
				background-color: #f6f5f5;
			}
		}
		.feature {
			margin-right: 28rpx;
		}
		.cl-icon {
			margin-right: 6rpx;
		}
	}
	.evalution{
		display:flex;
		flex-direction:column;
		background: #fff;
		margin-top: 16upx;
		padding: 40upx 0;
	}
	
	.eva-item{
		display:flex;
		padding: 20upx 40upx;
		image{
			width: 60upx;
			height: 60upx;
			border-radius: 50px;
			flex-shrink: 0;
			margin-right: 24upx;
		}
	}
	.eva-right{
		display:flex;
		flex-direction:column;
		flex: 1;
		font-size: $font-sm + 2upx;
		color: $font-color-light;
		position:relative;
		.zan-box{
			display:flex;
			align-items:base-line;
			position:absolute;
			top: 10upx;
			right: 10upx;
			.yticon{
				margin-left: 8upx; 
			}
		}
		.content{
			font-size: $font-base;
			color: #333;
			padding-top:20upx;
		}
	}
	
	.section-tit {
		padding-left: 40upx;
		font-size: $font-base+2upx;
		color: $font-color-dark;
		margin-bottom: 30upx;
		text-align:left;
	}
	
	.border-bottom{
		border-bottom: 0.5px solid $font-color-disabled;
	}
}
</style>

<template>
    <!-- 页面全局配置 -->
    <view class="pageconfig">
	
        <view class="flex10  padding-sm  response  flex flex-wrap justify-center  ">
			
            <view v-for="(item,index) in userInfo" class="flex11   response  flex flex-wrap justify-start  " style="align-items: center;">
                <view class=" 2 margin-right-xs  cu-avatar  lg  " :style="{'backgroundImage':'url('+item.avatar+')' }" style="background-color: unset;"></view>
                <view class="flex13  flex-sub padding-xs  response  flex flex-wrap justify-start  " style="flex-direction: column;align-content: flex-start;align-items: stretch;">
                    <text class="text-lg text-bold "> {{item.username}}</text>
                    <text class="text15 text-gray text-df "> {{item.followNum}}人关注</text>
                </view>
                <button @click="guanzhu123(item)" :class="item.btnCss">{{item.isguanzhu ? "已关注" : "关注"}}</button>
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

        <!--vuebottom-->
    </view>
</template>

<script>
	import { follower } from '@/request/api.js';
	
    import Vue from 'vue'
    export default {
        data() {
            return {
				item1 : {},
				combinedMessage: '',
				show: false,
				show3: false,
				guanzhu1: "round margin-lr-sm cu-btn round bg-gray",
				weiguanzhu: "round margin-lr-sm cu-btn round bg-yellow",
				guanzhuStatus: '',
				userInfo: 
				[
					{
						userName:'dawad',
						fans:12,
						userAtar:'http://localhost:9090/images/20240305172653.jpg',
						isguanzhu:true,
						btnCss: "round margin-lr-sm cu-btn round bg-gray"
					},
					{
						userName:'dawad',
						fans:12,
						userAtar:'http://localhost:9090/images/20240305172653.jpg',
						isguanzhu:true,
						btnCss: "round margin-lr-sm cu-btn round bg-gray"
					}
				]

            };
        },
		computed: {

		},
		onLoad: function(options) {
			var token = uni.getStorageSync('token');
			follower({userID:token,option: 4})
			.then((response) => {
				let res = response.data;
				if(response.code == 200){
					this.userInfo = res
				}
			});
        },
        methods: {
			guanzhu123(item){
				this.item1 = item
				if(!this.item1.isguanzhu){
					this.combinedMessage = "是否关注作者";
				}else{
					this.combinedMessage = "是否取消关注作者";
				}
				this.show3 = true
				
				
			},
			confirm() {
				this.item1.isguanzhu = !this.item1.isguanzhu
				this.followerAddOrDel()
				
			},
			cancel() {
				this.show3 = false
				console.log('cancel');
			},
			followerAddOrDel(){
				var option = ''
				var token = uni.getStorageSync('token');
				if(this.item1.isguanzhu){
					option = 1
					follower({followedID: this.item1.userID,userID:token,option: option})
					.then((response) => {
						let res = response.data;
						console.log(res)
						if(response.code == 200){
							console.log(1);
							this.guanzhuContent = '已关注';
							this.item1.btnCss = "round margin-lr-sm cu-btn round bg-gray";
							this.show3 = false
						}
					});
				}else{
					option = 2
					follower({followedID: this.item1.userID,userID:token,option: option})
					.then((response) => {
						let res = response.data;
						console.log(res)
						if(response.code == 200){
							console.log(2);
							this.guanzhuContent = '关注'
							this.item1.btnCss = "round margin-lr-sm cu-btn round bg-yellow"
							this.show3 = false
						}
					});
				}
			}

        }

    }
</script>

<style lang="less">
	@import "/colorui/关注ColorUI/colorui/main.css";
	@import "/colorui/关注ColorUI/colorui/icon.css";
    /**
 *
 此处必须引用colorui样式,并放入根目录，css样式可以这样写


 如需要引用colorui到全局，
 需要在App.vue 引入
 	@import "colorui/main.css";
	@import "colorui/icon.css";
 *
 */


    /**本页全局配置的代码**/
    .pageconfig {
        width: 100%;
        overflow: hidden;
        overflow-y: scroll;
        background-color: #ffffff;
        height: 100vh;
    }



    /**pagedomcss**/


    .flex1 {
        width: 100%;
    }

    .flex3 {
        width: 100%;
        flex: 1;
    }

    .flex10 {
        width: 100%;
    }

    .flex11 {
        width: 100%;
    }

    .flex13 {
        width: 100%;
        flex: 1;
    }

    .text15 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }

    .flex17 {
        width: 100%;
    }

    .flex19 {
        width: 100%;
        flex: 1;
    }

    .text21 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }

    .flex23 {
        width: 100%;
    }

    .flex25 {
        width: 100%;
        flex: 1;
    }

    .text27 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }

    .flex29 {
        width: 100%;
    }

    .flex31 {
        width: 100%;
        flex: 1;
    }

    .text33 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }

    .flex35 {
        width: 100%;
    }

    .flex37 {
        width: 100%;
        flex: 1;
    }

    .text39 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }

    .flex41 {
        width: 100%;
    }

    .flex43 {
        width: 100%;
        flex: 1;
    }

    .text45 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }

    .flex47 {
        width: 100%;
    }

    .flex49 {
        width: 100%;
        flex: 1;
    }

    .text51 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }

    .flex53 {
        width: 100%;
    }

    .flex55 {
        width: 100%;
        flex: 1;
    }

    .text57 {
        margin-top: 4rpx;
        margin-bottom: 4rpx;
        margin-left: 0rpx;
        margin-right: 0rpx;
    }
</style>
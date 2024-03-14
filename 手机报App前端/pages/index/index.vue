<template>
	<view class="page-home">

		<view class="search">
			<cl-search class="search2" placeholder="搜索文章" @search="handleEnter()" v-model="searchValue"></cl-search>
			
		</view>
		<cl-sticky is-topbar>
			<view class="tabs">
				<cl-tabs
					v-model="active"
					:labels="tabs"
					justify="start"
					unColor="#999999"
					show-dropdown
					:border="false"
					@tab-change="activeTab"
				>
				</cl-tabs>
			</view>
		</cl-sticky>
		<view class="list">
			<view class="item" v-for="(item, index) in list" :key="index" @tap="toDetail(item)">
				<view class="det">
					<view class="title">
						<cl-text
							:value="item.title"
							color="#000000"
							:size="32"
							:ellipsis="2"
						></cl-text>
					</view>
					<view class="count">
						<view class="is-top" >{{tName}}</view>
						<!-- <view class="count-item">{{ item.comment }}评</view> -->
						<view class="count-item">{{ item.createTime }}</view>
					</view>
				</view>
				<view class="cover">
					<image :src="item.newsAtar" mode="" />
				</view>
			</view>
		</view>
<!-- 		<view class="hot">
			<view class="hot-head">
				<view class="tag">热点</view>
				<cl-text :value="`实时更新于${hotTime}`" color="#999999" :size="22"></cl-text>
			</view>

			<view class="hot-list">
				<view class="item" v-for="(item, index) in hot" :Key="index">
					<cl-text
						:value="item.title"
						color="#333333"
						:size="36"
						:margin="[0, 0, 30, 0]"
						:ellipsis="1"
					></cl-text>
				</view>
			</view>
		</view> -->
		<view class="publish">
			<view class="open" @tap="onOpen">
				<image src="@/static/icon/发布.png" />
			</view>
			<view class="popup">
				<cl-popup :visible.sync="visible" size="70%" :padding="36" direction="center">
					<view class="publish-list">
						<view
							class="item"
							v-for="(item, index) in publish"
							:key="index"
							@tap="toPublish(item)"
						>
							<view class="icon">
								<image :src="item.icon" mode="aspectFit" />
							</view>
							<view class="name">
								{{ item.name }}
							</view>
						</view>
					</view>
				</cl-popup>
			</view>
		</view>
	</view>
</template>


<script>
	import { getType,getNewsByTitle } from '@/request/api.js';
export default {
  data() {
    return {
	  searchValue: '',
      // 数据部分
	  publish: [
	  	{ icon: require("@/static/icon/publish.png"), name: "创建文章" },
	  	// { icon: require("@/static/icon/publish2.png"), name: "小视频" },
	  	{ icon: require("@/static/icon/发布类型.png"), name: "发布类型" },
	  	// { icon: require("@/static/icon/publish4.png"), name: "爆料" },
	  ],
	  visible: false,
	  hotTitle: 'The_1975',
	  active: "",
	  tName:"最新",
	  tabs: [
	  	{ label: "最新", name: "" },
	  ],
	  list: [
	  	{
			title: '和非托管但如果翻跟斗翻跟啊吴大维斗人',
			tName: '为啥',
			createTime: '2019-23-21',
			pic1: '/static/bendi/QQ图片20240120130608.jpg'
		},
		{
			title: '啊达瓦伟大伟大啊吴大维',
			tName: '是的',
			createTime: '2019-23-21',
			pic1: '/static/bendi/QQ图片20240120130608.jpg'
		}
	  ],
	  
    };
  },
  mounted() {
  },
  onShow() {
  	this.getType();
  	this.getData();
  },
  methods: {
	  handleEnter(){
		console.log(this.searchValue); 
		 this.getData()
	  },
	  getData(){
		console.log(123);
		var _this = this;
		if(this.tName == "最新"){
			_this.tName = ""
		}
		getNewsByTitle({typeName: _this.tName,searchValue: this.searchValue})
		.then((res) => {
			console.log(res)
			if (res.code == 200) {
				_this.list = res.data;
				if(this.tName == ""){
					_this.tName = "最新"
				}
			}else{
				uni.showToast({
					title:"获取失败",
					icon:"none"
				})
			}
		  })
	},
	getType(){
		var _this = this;
		_this.tabs = []
		const obj2 = new Object();
		obj2.label = '最新';
		obj2.name = '';
		_this.tabs.push(obj2)
		
		getType()
		.then((res) => {
			console.log(res)
			if (res.code == 200) {
				res.data.forEach(obj => {
					// 使用new Object()创建一个空对象，然后逐个赋值
					const labelList = _this.tabs.map(obj2 => obj2.label);
					console.log(labelList);
					if(obj.typeName in labelList){
						
					}else{
						const obj2 = new Object();
						obj2.label = obj.typeName;
						obj2.name = obj.typeName;
						_this.tabs.push(obj2)
					}
				});
			}else{
				uni.showToast({
					title:"获取失败",
					icon:"none"
				})
			}
		  })
	},
	  toDetail(item) {
	  	uni.navigateTo({
	  		url: "/pages/detail/detail?id="+item.newsID,
	  	});
	  },
    // 方法部分
	toPublish(item) {
		this.visible = false;
		if(item.name == "创建文章"){
			uni.navigateTo({
				url: "/pages/publish/index",
			});
		}
		if(item.name == "发布类型"){
			uni.navigateTo({
				url: "/pages/publish/type",
			});
		}
	},
	onOpen() {
		this.visible = true;
	},
	activeTab(name){
		console.log(name)
		var _this = this;
		this.tabs.map(function(item,index){
			if(item.name == name){
				_this.tName = item.label;
			}
		});
		this.getData(name);
	},
	toSearch() {
		uni.navigateTo({
			url: "/pages/search/index",
		});
	},
  }
};
</script>




<style lang="scss">
.page-home {
	
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
	.tabs {
		background-color: #fff;
		height: 90rpx;
		line-height: 90rpx;
		border-bottom: 1rpx solid #eee;

		/deep/.cl-tabs {
			&__dropdown {
				color: #000;
			}
		}
	}
	.list {
		width: calc(100% - 60rpx);
		padding-top: 30rpx;
		border-bottom: 1rpx solid #eeeeee;
		margin: 0 30rpx;
		.item {
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
	.hot {
		width: calc(100% - 60rpx);
		margin: 33rpx 30rpx 0 30rpx;
		&-head {
			display: flex;
			.tag {
				width: 76rpx;
				height: 39rpx;
				margin-right: 15rpx;
				margin-bottom: 31rpx;
				background: #d43c3d;
				border-radius: 4rpx;
				color: #fff;
				text-align: center;
				line-height: 39rpx;
				font-size: 28rpx;
			}
		}
		&-list {
			.item {
				position: relative;
				padding-left: 35rpx;
				::after {
					content: "";
					display: inline-block;
					position: absolute;
					top: 20rpx;
					left: 0;
					width: 9rpx;
					height: 9rpx;
					background-color: #3c4166;
					border-radius: 50%;
					z-index: -1;
				}
			}
		}
	}
	.publish {
		.open {
			position: fixed;
			right: 30rpx;
			bottom: 130rpx;
			width: 120rpx;
			height: 120rpx;
			image {
				width: 60%;
				height: 60%;
			}
		}
		.popup {
			/deep/.cl-popup {
				border-radius: 50rpx;
			}
		}
		&-list {
			display: flex;
			.item {
				display: flex;
				flex-direction: column;
				align-items: center;
				flex: 1;
				.icon {
					display: flex;
					justify-content: center;
					align-items: center;
					width: 80rpx;
					height: 80rpx;
					margin-bottom: 6rpx;
					image {
						max-width: 64rpx;
						max-height: 57rpx;
					}
				}
				.name {
					font-size: 22rpx;
					font-weight: 400;
					color: #333333;
				}
			}
		}
	}
}
</style>

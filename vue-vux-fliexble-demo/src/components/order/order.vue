<template>
  
      <div>
         <div v-transfer-dom>
      <loading  text="loading"></loading>
    </div>
     <!-- <tab>
      <tab-item selected @on-item-click="onItemClick()"  >待消费</tab-item>
      <tab-item @on-item-click="onItemClick()"  >已消费</tab-item>
      <tab-item @on-item-click="onItemClick()" >已退款</tab-item>
    </tab> -->
      
  <tab :line-width=2 active-color='#fc378c' v-model="index">
    
        <tab-item      badge-label=""   class="vux-center" :selected="demo2 === item" v-for="(item, index) in list2" @click="demo2 = item" :key="index">{{item}}</tab-item>
      </tab>
      <swiper v-model="index" ref="cateSwiper"  :show-dots="false" @click.native="next"  @on-get-height="updateHeight">
        <swiper-item v-for="(list, currenIndex) in list2" :key="currenIndex"   >
   <scroller lock-x  height="100%">   
      <div class="tab-swiper vux-center">
       
        <div   class="order"   v-for="(item,keys) in orderDetailList" :key="keys" v-show="currenIndex === item.orderDetailVoList[0].payStatus" >    
            <div class="order-wapper"  >
              <div class="order-wapper-in"> 
              <div class="order-list  in-order-date">
                <span class="order-text">时间：{{item.appointWeek|moment}}&nbsp;&nbsp;&nbsp;&nbsp;{{item.appointTime}}</span>
                <span class="order-text order-text-margin">{{item.appointQuantity}}张</span>
              </div>
     
              <div class="order-list in-order-serviced">
                <span class="order-text">服务类型:</span>
                <span class="order-text order-text-margin">{{item.appointName}}</span>
              </div>
 
              <div class="order-list in-order-status">
                <span class="order-text">支付状态:</span>
                <span class="order-text order-text-margin" v-if="item.orderDetailVoList[0].payStatus === 0">待支付</span>
                <span class="order-text order-text-margin" v-else-if="item.orderDetailVoList[0].payStatus === 1">已支付</span>
                <span class="order-text order-text-margin" v-else-if="item.orderDetailVoList[0].payStatus === 2">已退款</span>
                <span class="order-text order-text-margin">{{item.appointPrice}}￥</span>
              </div>
   
              <div class="order-list in-order-time">
                <span class="order-text">下单时间:</span>
                <span class="order-text order-text-margin">{{item.orderDetailVoList[0].createTime|moment}}</span>
              </div>
 
              <div class="order-list in-orderNo">
                <span class="order-text">商户单号:</span>
                <span class="order-text order-text-margin">{{item.orderDetailVoList[0].orderId}}</span>
              </div>
              </div>
            </div>
              <div class="middle-hr">
                <hr style="height:1px;border:none;border-top:2px solid 	#E5E5E5" />
             </div> 
          </div>
     
        </div>   
          </scroller>
          <!-- </div>     -->
        </swiper-item>       
      </swiper>
      
  
      </div>



</template>

<script>
import TobeConsumed from '../order/components/tobeconsumed'
import OrderConsumed from '../order/components/consumed'
import OrderRefund from '../order/components/refund'
import axios from 'axios'
import { Tab, TabItem,Swiper, SwiperItem,Loading,TransferDomDirective as TransferDom,Scroller } from 'vux'
import { log } from 'util'
const list = () => ['待支付', '已支付', '已退款']
export default {
  directives: {
    TransferDom
  },
    components:{
    Tab,
    TabItem,
    TobeConsumed,
    OrderConsumed,
    OrderRefund,
    Swiper,
    SwiperItem,
    Loading,
    Scroller
    },
    data(){
        return{
        // index:true,
        list2: list(),
        demo2:'待支付',
        index:0,
        currenIndex:0,
        orderDetailList: [],
        num:0,
        
        }
    },
    created(){
      //跳转页面的时候加载
      this.showLoading()
         setTimeout(() => {
     this.getOrderDetail();
      }, 800)
    },
    mounted(){
      //order div 标签 高度
      // this.orderBindHeight();
    },
    watch:{
      //  orderDetailList: function (){
      //     this.$nextTick(function(){
      //       var obj = document.getElementsByClassName("tab-swiper")[0];
      //       this.BindHeight = obj.offsetHeight*2;
      //          console.log("高度为："+this.BindHeight);
      //     })
      //  }
    },
    methods:{
    //加载
    showLoading () {
      this.$vux.loading.show({
        text: ''
      })
      setTimeout(() => {
        this.$vux.loading.hide()
      }, 500)
      },
    //更新高度
    updateHeight(e){
      let topHeight = document.getElementsByClassName('tab-swiper')[0].offsetHeight,
      diffHeight = window.screen.height-topHeight - 55 ;
      this.$nextTick(()=>{
        this.$refs.cateSwiper.xheight = diffHeight + 'px'
      })
    },
       handler (index) {
       console.log("index:"+index)
      //  this.index = index
       },
  next(){
    if(this.currenIndex === this.list2.length - 1){
      this.currenIndex = 0
    }else{
      ++this.currenIndex
    }
    console.log("currenIndex",this.currenIndex)
  },

  //获取订单详情数据
  getOrderDetail(){
      axios({
          method: 'get',
          url:'http://www.gouptea.top/haircut/customer/order/api/list',
          // url:'http://localhost:8080/haircut/customer/order/api/list',
          params: {
          'openid': getCookie('openid')
          //  'openid':'oA9nMwK9OluRANG86pHl8YeCERAk'
          }
      }).then((res)=>{
          this.respone = res.data.data
          
          this.orderDetailList = this.respone;
          // console.log(this.orderDetailList[0].orderDetailVoList[0].orderId);
          // console.log(this.respone.appointTime);
          // this.time = res.data.
          // this.$router.push({path:'/customer',
          // query:{appointId:this.respone.appointId,dateTime:this.respone.appointTime,servieName:this.respone.appointName,
          // money:this.respone.appointPrice,date:this.respone.appointWeek}})
      }) 
    }
  }
}
function getCookie(name) {
    var arr;
    var reg = new RegExp('(^| )' +name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
</script>

<style>
 @import '../../assets/style/reset.css';

.order{
  width: 100%;
  height: auto;
   margin-bottom: 30px;
}

.order-wapper{
    width: 100%;
    height: 150px;
    margin: 0 auto;
    text-align: center;
    margin: 0.266667rem 0.266667rem 0.8rem 1.2rem; 
}
/*
.order-wapper-in{
 
  
} */
.middle-hr{
  width: 100%;
  height: 10px;
  float: left;
 /* margin-top: 100px; */
}

.order-list {
  width: 100%;
  height: 30px;
  line-height: 30px;
  margin-top: 5px;
  float: left;
  /* background-color: brown; */
}

.order-text{
  float: left;
 
}

.order-text-margin{
  margin-left: 50px;
}

.order-boder-top{
  border-top: 1px solid #BABABA; 
}

.order-boder-bottom{
    border-bottom: 1px solid #BABABA; 
}


</style>
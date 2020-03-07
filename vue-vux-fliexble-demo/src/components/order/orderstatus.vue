<template>

<div>
   
      <!-- 预约状态没有的时候-->
      <div class="order-none-status-wapper" v-show="orderDetail == null">
          <div class="order-none-status">
              <div class="order-none-font">
                   <span class="order-status-no">
                       当前没有预约的订单
                   </span>
              </div>
              <div class="order-none-button">
                  <x-button type="primary" action-type="button" @click.native="toHome">快去预约</x-button>
              </div>
          </div>
      </div>
     <div>
    <!--预约有状态 -->
     <scroller lock-x >
     
        <div class="order-have-status"   v-show="orderDetail != null"  >
            <div class="order-have-in" ref="orderIn"  id="orderIn"  >
                <div class="order-have-in-class" v-for="(item,index) in orderDetail" :key="index"  >
                    <div class="order-header-wapper" >
                        <div class="order-header-first">
                            <!--时间、支付--> 
                            <div class="order-header-frist-left">
                                <div class="order-header-left"></div>
                                <div class="order-header-left">
                                    <span class="order-header-font-size">支付方式:
                                       &nbsp;&nbsp;&nbsp;<span v-show="item.orderDetailVoList[0].paymentType === 1">微信支付</span>
                                        <span v-show="item.orderDetailVoList[0].paymentType === 0">现金支付</span>
                                        <!-- <span v-show="item.orderDetailVoList[0].paymentType === 0">未选择付</span> -->
                                    </span>
                                    <input id="cancelOrderId" type="hidden" :value="item.orderDetailVoList[0].orderId"  ref="cancelOrderId"/>

                                </div>
                                <div class="order-header-left">
                                    <span class="order-header-font-size">预约日期:&nbsp;&nbsp;&nbsp;&nbsp;{{item.appointWeek|moment}}</span>
                                </div>
                                <div class="order-header-left">
                                       <span class="order-header-date">星期五</span>
                                </div>
                            </div>
                            <!--价钱-->
                            <div class="order-header-frist-right">
                                <div class="order-header-price">
                                    <span class="order-header-yuan">{{item.appointPrice}}￥</span>
                                </div>
                                <div class="order-header-ticket-wapper">
                                    <span class="order-header-ticket">普通价</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--服务方式-->
              
                    <div class="order-status-service-wapper">
                        <div class="order-service-time-wapper">
                            <div class="order-service-time">
                                <span class="servie-time-font">{{item.appointTime}}</span>
                                <span class="iconfont servie-time-icon">&#xe61e;</span>
                            </div>
                        </div>
                        <div class="order-service-type-wapper">
                            <div class="order-servie-type-left">
                                <span>服务类型</span>
                            </div>
                            <div class="order-servie-type-right">
                                <span>{{item.appointName}}</span>
                            </div>
                        </div>
                        <!--票类--->
                    </div>
                    <!--显示个人信息-->
                    <div class="order-status-person-wapper">
                        <div class="order-status-person">
                            <div class="order-status-person-touch">
                                <span class="person-info-font">
                                    联系人方式
                                </span>
                            </div>
                            <div class="order-status-person-name">
                                <span class="person-info-font person-name" ref="cutomerName">
                                    {{item.orderDetailVoList[0].customerName}}
                                </span>
                            </div>
                            <div class="order-status-person-phone">
                                 <span class="person-info-font">
                                    {{item.orderDetailVoList[0].customerPhone}}
                                </span>
                            </div> 
                        </div>
                        <div class="order-status-person">
                            <div class="order-status-person-touch">
                                <span class="person-info-font">
                                    店长电话
                                </span>
                            </div>
                            <div class="order-status-person-shopkeeper">
                                <span class="iconfont person-shopkeeper-icon">&#xe71f;</span>
                                <span class="order-person-shopkeeper-font">13692060479</span>
                            </div>
                        </div>
                        <!-- <div class="order-status-person">
                    
                        </div> -->
                    </div>
                    <!--按钮-->
                    <div class="order-status-button">
                        <div class="order-button">
                            <x-button  @click.native="isReturnTicket(index)" type="primary" v-show="item.orderDetailVoList[0].orderStatus === 0">退预</x-button>
                             <!-- <x-button   type="disabled" v-show="item.orderDetailVoList[0].orderStatus === 2">退预</x-button> -->
                        </div>
                            <div v-transfer-dom>
                                <confirm v-model="show"
                                title="操作提示"
                                @on-cancel="onCancel"
                                @on-confirm="onConfirm"
                                @on-show="onShow"
                                @on-hide="onHide">
                                <p style="text-align:center;">您是否要确定退约</p>
                                </confirm>
                            </div>
                        <div class="order-button">
                            <x-button type="primary">改约</x-button>
                        </div>
                        <div class="order-button">

                            <x-button v-if="item.orderDetailVoList[0].payStatus === 0" type="primary">待支付</x-button>
                            <x-button v-if="item.orderDetailVoList[0].payStatus === 1" type="primary">已支付</x-button>
                        </div>
                    </div>
                </div>
            </div>                  
        </div>    
     </scroller>
           <home-bottom></home-bottom> 
     </div>
  </div>
   
</template>

<script>
import { Confirm,ConfirmPlugin,TransferDomDirective as TransferDom } from 'vux'
import { XButton } from 'vux'
import { Alert, AlertModule,Scroller} from 'vux'
import axios from 'axios'
import HomeBottom from '../home/components/bottom'
export default {
    directives: {
        TransferDom
    },
    name:'orderstatus',
    components:{
        XButton,
        HomeBottom,
        Confirm,
        ConfirmPlugin,
        Alert,
        AlertModule,
        Scroller
    },
    data(){
        return{
            orderDetail:[],
            show: false,
            orderId:'',
            currentIndex:0,
            statusHeight:'',
        }
    },
    created(){
        //获取订单详情
      this.getByOrderDetail()
    },
    mounted(){
    //加载高度 
    setTimeout(function(){
            var obj = document.getElementsByClassName("order-have-in")[0];
            this.statusHeight = (obj.offsetHeight+50)+'px';
               console.log("高度为："+this.statusHeight);
        },1000);

    //退约弹窗倒计时
    // this.timer = setInterval(() => {
    // console.log(this.$vux.alert.isVisible())
    //         }, 1000)
    // console.log(window.getComputedStyle(this.$refs.orderIn).height)
    // console.log(document.getElementById("orderIn").offsetHeight)
  
 
        },
 
  beforeDestroy () {
    clearInterval(this.timer)
  },

   methods:{
        toHome(){
            this.$router.push({path:'/'})
        },
        getByOrderDetail(){
        // const ERR_OK = 0;
        axios({
          method: 'get',
          url: 'http://www.gouptea.top/haircut/customer/order/api/orderDetail',
          params: {
             'openid':getCookie('openid')
            //    'openid':'oA9nMwK9OluRANG86pHl8YeCERAk'
 
          }
      }).then((response)=>{
            console.log(response)
            this.orderDetail = response.data.data
               
      })  
        },

    //确定取消预约
     cancelOrder(){
        axios({
          method: 'post',
          url: 'http://www.gouptea.top/haircut/customer/order/api/cancel',
          params: {
             'openid':getCookie('openid'),
            //    'openid':'oI5mNxAQgcHSgTAmfjqlWqtTE4Ls',
               'orderId':this.orderId
          }
      }).then((response)=>{
            console.log(response)
            //退预操作
            this.showModule()
            // this.orderDetail = response.data.data
            this.$router.push({path:'/'})
      })  
     },


     //vux弹窗
    onCancel () {
      console.log('on cancel')
    },
    onConfirm (msg) {
      console.log('on confirm')
      if (msg) {
        alert(msg)
      }
      if(this.show){
          console.log("确定下单")
        //   console.log(this.currentIndex)
        //   console.log("orderId=",this.$refs.cancelOrderId[this.currentIndex].value)
          this.orderId = this.$refs.cancelOrderId[this.currentIndex].value
          console.log("退约orderId = ",this.orderId)
          this.cancelOrder()
      }
    },onHide () {
      console.log('on hide')
    },
    onShow () {
      console.log('on show')
    },
        //确定是否退约
        isReturnTicket(index){
            console.log("退约....")
            this.show = true
            this.currentIndex = index
},
       // 退约成功弹窗
       showModule () {
      AlertModule.show({
        title: '消息',
        content: '恭喜您,退约已成功!',
        onShow () {
          console.log('Module: I\'m showing')
        },
        onHide () {
          console.log('Module: I\'m hiding now')
        }
      })
    },
    //退约弹窗3秒后隐藏
     showModuleAuto () {
      this.showModule()
      setTimeout(() => {
        AlertModule.hide()
      }, 3000)
    },
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

<style scoped>


.iconfont{
    font-size:20px;
}

.order-status-wapper{
    width: 100%;
    height: auto;
    
    /* background-color: blue; */
}

.order-none-status-wapper{
    width: 90%;
    height: 350px;
    margin: 0 auto;
    /* background-color: black; */

}

.order-none-status{
    width: 100%;
    height: 100px;
    float: left;
    margin-top: 250px;
    /* background-color: blueviolet; */
}

.order-none-font{
    width: 100%;
    height: 40px;
    line-height: 40px;
    font-size: 18px;
    color: black;
    /* background-color: chocolate; */
}

.order-none-button{
    width: 100%;
    height: 60px;
    /* background-color: bisque; */
}

.order-have-status{
    position: absolute;
    width: 100%;
    /* height: 100%; */
    background-color:#F2F2F2;
}
.order-have-in{
    width: 100%;
    height:100%;
    margin-top:10px;
    margin-bottom: 40px;
    /* background-color: black; */
}

.order-have-in-class{
    
    width: 94%;
    height: 420px;
    margin: 0 auto;
    margin-bottom: 20px;
    background-color: #FFFFFF;
}

.order-header-wapper{
    width: 100%;
    height: 120px;
    border-bottom: 1px solid #BABABA;
    /* background-color: black; */
}
.order-header-first{
    width: 90%;
    height: 100px;
    margin: 0 auto;
    /* background-color: aquamarine; */
}

.order-header-frist-left{
    width: 70%;
    height: 100px;
    float: left;
    /* background-color:blue; */
}

.order-header-left{
    width: 100%;
    height:25px;
    line-height: 25px;
    /* background-color: yellow; */
}
.order-header-date{
    width: 25%;
    height: 25px;
    color: #F75000;
    font-size: 16px;
    font-weight:bold;

    /* background-color: black; */
}

.order-header-font-size{
    float: left;
    font-weight:bold;
    font-size: 14px;
    color: black;
}

.order-header-frist-right{
    width: 30%;
    height: 100px;
    float: left;
    /* background-color: beige; */
}

.order-header-price{
    width: 100%;
    height: 80px;
    line-height: 80px;
    /* background-color: black; */
}

.order-header-ticket-wapper{
    width: 100%;
    height: 20px;
    line-height: 20px;
    /* background-color: #5CACEE; */
}
.order-header-ticket{
    font-size: 18px;
}

.order-header-yuan{
    color: #5CACEE;
    font-size: 30px;
}

.order-status-service-wapper{
    width: 100%;
    height: 90px;
    margin-top: 10px;
    border-bottom: 1px solid #BABABA;
    /* background-color: bisque; */
}

.order-service-time-wapper{
    width: 100%;
    height: 40px;
    line-height: 40px;
    /* background-color: black; */
}
.order-service-time{
    width: 30%;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    font-weight:bold;
    /* border-bottom: 4px solid #BABABA; */
    /* background-color: aqua; */
}
.servie-time-font{
    font-size: 20px;
}
.servie-time-icon{
    font-size: 20px;
}

.order-service-type-wapper{
    width: 100%;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    /* background-color:burlywood; */
}

.order-servie-type-left{
    width: 50%;
    height: 50px;
    /* background-color: yellow; */
    float: left;
}

.order-servie-type-right{
    width: 50%;
    height: 50px;
    float: left;
    /* background-color:violet; */
}

.order-servie-ticket-wapper{
    width: 100%;
    height: 30px;
    line-height: 30px;
    /* background-color: black; */
}

.order-servie-ticket{
    width: 20%;
    height: 30px;
    margin: 0 auto;
    /* background-color:chartreuse; */
}

.order-status-person-wapper{
    width: 100%;
    height: 100px;
    /* height: 135px; */
    margin-top: 30px;
    /* background-color: bisque; */
}

.order-status-person{
    width: 100%;
    height: 30px;
    line-height: 30px;
    /* background-color: #5CACEE; */
    margin-bottom: 15px;
}

.order-status-person-touch{
    width: 25%;
    height: 30px;
    float: left;
    margin-left: 30px;
    /* background-color: black; */
}



.order-status-person-name{
    width: 15%;
    height: 30px;
    float: left;
     margin-left: 25px;
    /* background-color: aqua; */
}

.order-status-person-phone{
    width: 32%;
    height: 30px;
    float: left;
    margin-left: 40px;
    /* background-color: black; */
}

.order-status-person-shopkeeper{
    width:100%;
    height: 30px;
    line-height: 30px;
    margin-left: 145px;
    /* background-color: yellow; */
}

.person-shopkeeper-icon{
    float: left;
}

.order-person-shopkeeper-font{
      float: left;
      margin-left: 75px;
      font-size: 15px;
}


.person-info-font{
    font-weight:bold;
    font-size: 15px;
    float: left;
}

.person-name{
    color:#5CACEE;
}

 

.order-person-font{
    float: left;
    font-size: 18px;
}

.order-status-button{
    width: 100%;
    height:50px;
    /* background-color: black; */
}

.order-button{
    width: 25%;
    height: 50px;
    line-height: 50px;
    margin-left: 25px;
    float: left;
    /* background-color: aquamarine; */
}

 



</style>
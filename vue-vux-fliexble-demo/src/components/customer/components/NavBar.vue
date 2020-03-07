<template>

<div>
  <div class="big-size"></div>
  <div class="bottom-footer-wapper" ref="bottomFooter">
      <div class="footer-wapper-money">
        <div class="wapper-money-bigsize">
                <span class="money-sum">
                  总计:{{totalPrice?totalPrice:'0'}}元
                </span>
                <span class="wapper-money-discount">
                  <!-- 已优惠:0元 -->
                </span>
        </div>
 
      </div>
      <div class="footer-wapper-submit">
 <!-- <x-button type="primary">提交订单</x-button> -->
 <button class="btn" type="submit" @click="submitOrder">提交订单</button>
      </div>
  </div>
</div>
</template>

<script>
 var config = require('../../../../config/index')
    config = config.build
 import { XButton,Alert,AlertModule } from 'vux'
 import axios from 'axios'
export default {
    
    name:'NavBar',
     props:['dateTime','serviceName','money','weekDate','totalPrice','username','phone','roundValue','appointId'],
    components:{
      XButton,
      Alert,
      AlertModule
    },
    //监听数据变化
    watch:{
      dateTime: function(newVal,oldVal){
        console.log(newVal,oldVal)
      }
    },
    data(){
      return{
        //  submitOrderPost:'http://www.gouptea.top/haircut/customer/order/api/submitOrder'
        // submitOrderPost:'http://localhost:8080/haircut/alipay/wapPayServlet',
        submitOrderPost:'http://www.gouptea.top/haircut/alipay/wapPayServlet'
      }
    },
    methods:{
    showNumber () {
        AlertModule.show({
          title: '消息',
          content: '预约人数不能为0',
          onShow () {
            console.log('Module: I\'m showing')
          },
          onHide () {
            console.log('Module: I\'m hiding now')
          }
        })
      },
      showUserName () {
        AlertModule.show({
          title: '消息',
          content: '请输入姓名',
          onShow () {
            console.log('Module: I\'m showing')
          },
          onHide () {
            console.log('Module: I\'m hiding now')
          }
        })
      },
      showPhone () {
        AlertModule.show({
          title: '消息',
          content: '请输入电话',
          onShow () {
            console.log('Module: I\'m showing')
          },
          onHide () {
            console.log('Module: I\'m hiding now')
          }
        })
      },
      //提交订单
      submitOrder: function(){
        console.log('进来')
        console.log('name = ',this.username);
        console.log('phone = ',this.phone);
        if(this.roundValue == 0 || this.roundValue == null){
          this.showNumber();
          return;
        }
        if(this.username == null || this.username == ''){
          this.showUserName();
          return;
        }
        if(this.phone == null || this.phone ==''){
          this.showPhone();
          return;
        }


        console.log('appointQuantity = ',this.roundValue);
        const ERR_OK = 0;
          // axios({
          // method: 'post',
          // url: this.submitOrderPost,
          // params: {
          //   //  'openid':getCookie('openid'),
          //   'openid':'oA9nMwK9OluRANG86pHl8YeCERAk',
          //    'appointId': this.appointId,
          //    'name': this.username,
          //    'phone': this.phone,
          //    'appointQuantity':this.roundValue,
          //      'subject': this.serviceName,
          //     'total_amount': this.totalPrice
          // }      
      //  支付宝H5测试
       // url: this.submitOrderPost,
          // params: {
          //    'openid':getCookie('openid'),
          //    'appointId': this.appointId,
          //    'name': this.username,
          //    'phone': this.phone,
          //    'appointQuantity':this.roundValue,
          //     'subject': this.serviceName,
          //     'total_amount': this.totalPrice
          // }
      // }).then((response)=>{

// if(response.data.code == ERR_OK){
    // 支付宝H5
//  let divForm = document.getElementsByTagName('divform')
//     if (divForm.length) {
//       document.body.removeChild(divForm[0])
//     }
//      const div=document.createElement('divform');
//      div.innerHTML=response.data; // data就是接口返回的form 表单字符串
//      document.body.appendChild(div);
//      document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
//      document.forms[0].submit(); 
// }
//微信支付
    // if(response.data.code == ERR_OK){
    //       location.href = config.wechatPayUrl +
    //       '?openid =' + getCookie('openid') +
    //       '&orderId=' + response.data.data.orderId +
    //       '&returnUrl=' + encodeURIComponent(config.haircutUrl + '/#/orderSuccess/');
    //     }

      // })
      // .catch(function (error) {
      //       this.$router.push({path:'/#/orderstatus'})
      //       console.log(error);
      //     });

      //  2020 2 22 跳转支付页面
         this.$router.push({path:'/gopay',
         query:{
              // openid:getCookie('openid'), 
         //  'openid':'oA9nMwK9OluRANG86pHl8YeCERAk',
             appointId: this.appointId,
             name: this.username,
             phone: this.phone,
             appointQuantity:this.roundValue,
             subject: this.serviceName,
             total_amount: this.totalPrice
         }})
    },

    showAliPay(){
      axios({
            method:'post',
            ur:'http://localhost:8080/haircut/alipay/wapPayServlet',
            params:{
             
            }
          }).then((res) =>{
    let divForm = document.getElementsByTagName('divform')
    if (divForm.length) {
      document.body.removeChild(divForm[0])
    }
     const div=document.createElement('divform');
     div.innerHTML=response.data; // data就是接口返回的form 表单字符串
     document.body.appendChild(div);
    //  document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
     document.forms[0].submit(); 
          })
    },
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

<style  >
 @import '../../../assets/style/reset.css';



.bottom-footer-wapper{

 position:fixed; 
/* margin-top: -100px; */
bottom:0px; 
width:100%; 
height:60px;
line-height: 60px; 
z-index:2; 
overflow:hidden;
background-color: #F8F8F8;
}

.footer-wapper-money{
  width: 50%;
  height: 60px;
  float:left;
  margin-right: 20%;
}

.footer-wapper-submit{
  width: 30%;
  height: 60px;
  
  float: left;
  /* background-color: #52c2d6; */
}

.btn{
   position: relative;/*必须添上这一句，否则波纹布满整个页面*/
            background: dodgerblue;
            border: none;
            color: white;
            width: 100%;
            height: 60px;
            font-size: 19px;/*可以通过字体控制button大小*/
            float:right;
            /* border-radius: 12px; */
            transition-duration: 0.4s;
            overflow: hidden;
            outline-style: none;/*去除点击时外部框线*/
 
}

.wapper-money-bigsize{
  width: 100%;
  height: 60px;
  color: black;
  font-size: 20px;
  /* background-color: blue; */
}

.wapper-money-discount{
  font-size: 15px;
  color: red;
}


</style>
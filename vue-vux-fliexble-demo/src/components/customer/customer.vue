<template>
 <div> 
   <div class="customer-wapper" ref="wapperHeight">
       <!--日期、时间、价钱--->
       <div class="customer-header">
           <div class="customer-header-date-yuan">
               <div class="in-date">
                   <span class="date-text">  
                    &nbsp;&nbsp;{{weekDate|moment}}
                    &nbsp;&nbsp;{{dateTime}}
                   </span>
                    <!-- <span class="iconfont  icon-order-time ">
                       &#xe61e;
                   </span> -->
               </div>
               <!-- <div class="in-yuan">
                    <span class="yuan-text">
                        {{totalPrice?totalPrice:'0'}}
                    </span>
               </div> -->
                <div class="in-week">
                    {{calendarWeek}}
               </div>
           </div>
           <!-- <div class="cutomer-header-week">
               <div class="cusomter-header-week-in">
                   <span class="font-week">
                       星期天
                   </span>
               </div>
           </div> -->
           <!-- <div class="cutomer-header-price">
               <div class="cutomer-header-price-left">
                  <span class="font-moeny">
                        价格
                   </span>
                   <span class="font-yuan">
                       ￥{{totalPrice?totalPrice:'0'}}
                   </span>
               </div>
               <div class="cutomer-header-price-right">
               </div>
           </div> -->
           <!-- <div class="customer-header-time">
               <div class="in-time">
                   <span class="iconfont icon-time">&#xe61e;</span>
                   <span class="time-text" >
                        &nbsp;&nbsp;&nbsp;{{dateTime}}
                   </span>
               </div>
               <div class="in-other">
                   <div class="in-yuan">
                        <span class="yuan-text">
                            {{totalPrice?totalPrice:'0'}}
                        </span>
                        <span>元</span>
                   </div>
               </div>
           </div> -->
       </div>
       <!--服务-->
       <!-- <div class="customer-service-wapper">
           <div class="customer-wapper">
               <div class="customer-service-left">
                   <span class="font-size font-service">洗剪吹</span>
                   <span class="font-size font-yuan">￥30</span>
               </div>
               <div class="customer-service-right">
               </div>
           </div>
       </div> -->
       <div class="">
      <group >
      <x-number 
      :title="serviceName" 
      
      v-model="roundValue" button-style="round" :min="0" :max="100" @on-change="changeNum"></x-number>
      </group>
        </div>
        <!--客户信息--->
        <div class="customer-info-wapper">
            <!--通知注意-->
            <div class="customer-info-notice-wapper">
                <div class="customer-info-notice">
                    <span class="notice-text">
                        购票注意:
                    </span>
                </div>
                <div class="customer-info-notice-text">
                    <span class="in-notice-text"></span>
                    <span class="in-notice-detail">
                        <span class="notice-detail"></span>
                    </span>
                </div>
            </div>
 
            </div>   
         <!--输入用户信息-->
            <div class="cutomer-user-info">
                <span class="font-size-big">填写联系人</span>
            </div>
            <div class="customer-info">
    <group>
      <x-input title=" 
       <span class='iconfont font-size-icon icon-user'> &#xe622;</span>联系人姓名
      " name="username"  v-model="username" placeholder="请输入姓名" is-type="china-name" ref="input_description" @blur.native.capture="lostfocus"  @focus.native.capture = "getFocus"   ></x-input>
    </group>

    <group >
      <x-input title="
      <span class='iconfont font-size-icon icon-touch'  > &#xe630;</span>联系人手机
      " name="mobile"  v-model="phone" placeholder="请输入手机号码" keyboard="number" is-type="china-mobile" @blur.native.capture="lostfocus" @focus.native.capture = "getFocus"></x-input>
    </group>
            </div>

    <!--水平线--->
 
    <!--支付信息-->
    <div class="buttom-wapper">
    <div class="apay-wapper">
         <div class="in-apay-left-icon">
             <span class="iconfont apay-font-icon">&#xe613;</span>
         </div>
         <div class="in-apay-text">
             <span class="apay-text">支付宝支付</span>
         </div>
         <div class="in-apay-right-icon">
             <span class="iconfont icon-submit">&#xe61f;</span>
         </div>
        </div>
       </div>
   </div>

     <nav-bar :dateTime="dateTime" :serviceName="serviceName" 
              :money="money" :weekDate="weekDate" :totalPrice="totalPrice"
              :username="username" :phone="phone" :roundValue="roundValue"
              :appointId="appointId"  v-show="hideshow"
               ref="buttomOrder"
               >
         </nav-bar>   
 
  </div>
</template>

<script>
import NavBar from './components/NavBar'
import {Group, Cell, InlineXNumber,XNumber, XInput,Scroller } from 'vux'

export default {
    name:'customer',
    components:{
        XNumber,
        Group,
        Cell,
        InlineXNumber,
        XInput,
        NavBar,
        Scroller 
    },
    data(){
        return{
             roundValue: 0,
             dateTime:'',
             serviceName:'',
             money:'',
             weekDate:'',
             totalPrice:'',
             username:'',
             phone:'',
             appointId:'',
             docmHeight: '0',  //默认屏幕高度
             showHeight:  '0',  //实时屏幕高度
             hideshow:true,  //显示或者隐藏footer,
             isResize:false, //默认屏幕高度是否已获取
             calendarWeek:''  //日期显示
        
        }
    },
    watch:{
        roundValue(newName,oldName){
            console.log('滚动',newName)
            this.totalPrice = newName * this.money
        },
//         showHeight:function(newName) {

//         if( newName >= this.docmHeight){
//         //    alert("newName:"+newName+"show:"+this.docmHeight)
//             this.hideshow=false
//         }else{
//         // alert("newName:"+newName+"show:"+this.docmHeight)
//             this.hideshow=true
//         }
//     }
        
    },
    created(){
 
    },
    mounted(){  
         // 从list获取的信息
        this.getToCustomer();
           // 加载日期
    this.showWeek();
  
 
    // this.getHeight();
//        window.onresize监听页面高度的变化
//            window.onresize = ()=>{
//         return(()=>{
   
//        if(!this.isResize){
//          //默认屏幕高度
//          this.docmHeight= document.documentElement.clientHeight 
//           this.isResize = true  
//        }
//          //实时屏幕高度
//          this.showHeight = document.body.clientHeight       
// })()
//     }
    },  
    methods:{
        //获取list传过来的值
        getToCustomer(){
            var time = this.$route.query.dateTime;
            var name = this.$route.query.servieName;
            var yuan = this.$route.query.money;
            var date = this.$route.query.date;
            var id   = this.$route.query.appointId; 
            console.log("customer----->>>接受过来的传输",time,name,yuan,date,id)
            this.dateTime = time;
            this.serviceName = name;
            this.money = yuan;
            this.weekDate = date;
            this.appointId = id;
            //  this.showtime = time;
            // console.log("showTime:"+showtime);
            // this.showname = name;
            // this.showyuan = yuan;
            // this.showdate = date;
        },
        //  获取页面高度
        getHeight(){
            // this.docmHeight = this.$refs.wapperHeight.offsetHeight;
            //    this.docmHeight = document.documentElement.clientHeight+'px';
            //    this.showHeight = document.documentElement.clientHeight+'px';
            console.log("默认屏幕高度:"+document.documentElement.clientHeight)
            console.log("实时屏幕高度:"+document.body.clientHeight)
            this.buttomHeight = this.$refs.buttomOrder.$el.offsetTop  //当前的值
              console.log("buttomHeight",this.buttomHeight)
              console.log("docmHeight:",this.docmHeight)
              console.log("showHeight:",this.showHeight)
        },
        lostfocus(){
            console.log("失去焦点")
            if(this.hideshow == false){
                this.hideshow = true
            }
        },
        getFocus(){
            console.log("获取焦点")
            if(this.hideshow == true){
                this.hideshow = false
            }
        },
        changeNum(val){
            this.roundValue = val;
            console.log('changeNum',this.roundValue)
        },

        //显示日期
        showWeek(){
         var str = this.$route.query.date;
         console.log("customer 日期："+str)
         //Date对象实例化
         var calendarObject = new Date(str)
         var day = calendarObject.getDay()
         if(day == 1){
              this.calendarWeek = '星期一';
              console.log('星期一',day);
            }
            if(day == 2){
              this.calendarWeek = '星期二';
                console.log('星期二',day);
            }
            if(day == 3){
               
               this.calendarWeek = '星期三';
                  console.log('星期三',day);
            }
            if(day == 4){
               this.calendarWeek = '星期四';
                  console.log('星期四',day);
            }
            if(day == 5){
              this.calendarWeek = '星期五';
                 console.log('星期五',day);
            }
            if(day == 6){
               this.calendarWeek = '星期六';
                  console.log('星期六',day);
            }
            if(day == 0){
               this.calendarWeek = '星期天';
            }
        }
    }
}
  
</script>

<style  >
 
 

.customer-wapper{
    width: 95%;
    height: auto;
    margin: 0 auto;
    /* background-color: gray; */
 
}
.customer-header{
    width: 100%;
    height:40px;
    /* background-color: black; */
}
.customer-header-date-yuan{
    width: 100%;
    height: 50px;
    line-height: 50px;
  
    
    /* background-color: blue; */
}
.in-date{
    width: 70%;
    height: 50px;
     float: left;
    /* background-color: coral; */
}
.icon-order-time{
    font-size: 24px;
}

.date-text{
    float: left;
    font-weight:bold;
    /* margin-left: 10px; */
    font-size: 18px;
 
    
}

.in-week{
    width: 20%;
    height: 50px;
    font-weight:bold;
    color: #5CACEE;
    float: left;
    font-size: 18px;
  
    /* background-color: blueviolet; */
}


.cutomer-header-week{
    width: 100%;
    height: 39px;
    float: left;
    border-top: 1px solid #BABABA;
    /* background-color: #2c2c2c; */
}

.cusomter-header-week-in{
    width: 50%;
    height: 40px;
    line-height: 40px;

    float: left;
 
    /* background-color:bisque; */
}
.font-week{
    font-size: 20px;

}
.cutomer-header-price{
    width: 100%;
    height: 30px;
    line-height: 30px;
    float: left;
    /* background-color: #F75000; */
}

.cutomer-header-price-left{
    width: 60%;
    height: 30px;
     font-size: 18px;
    float: left;
    /* background-color: #13227a; */
}

.cutomer-header-price-right{
    width: 40%;
    height: 30px;
    float: left;
    background-color:aqua;
    
}
.font-moeny{
  float: left;
  margin-left: 40px;
}

.font-yuan{
    float: left;
}



.yuan-text{
    margin-left: 30px;
    font-size: 20px;
    color: #F75000;
}


.customer-header-time{
    width: 100%;
    height: 30px;
    line-height: 30px;
    margin-top: 10px;
/* background-color: crimson; */
}
.in-time{
    width: 30%;
    height: 30px;
    float: left;
    margin-left: 5px;
    font-size: 18px;
    color: black;
    /* background-color: antiquewhite; */   
}
.in-other{
    width: 60%;
    height: 30px;
    float: left;
    /* background-color: darkblue;  */
}
.in-yuan{
    width: 60%;
    height: 30px;
    float:right;
}


.customer-service-wapper{
    width: 100%;
    height: 50px;
    margin-top: 10px;
    border-top: 1px solid #BABABA;
    border-bottom: 1px solid #BABABA;
    /* background-color: black; */
}

 

.customer-service-left{
    width: 70%;
    height: 40px;
    line-height: 40px;
    float: left;
    /* background-color: antiquewhite; */
}
.font-service{
    width: 30%;
    height: 40px;
    margin-left: 10px;
    float: left;
    /* background-color: blue; */
}

.font-yuan{
    width: 20%;
    height: 40px;
    float: left;
    /* background-color: darkcyan; */
}

.font-size{
    font-size: 19px;
}

.customer-service-right{
    width: 30%;
    height: 40px;
    line-height: 40px;
    float: left;
    /* background-color: cadetblue; */
}


.customer-info-wapper{
    width: 100%;
    height: auto;
    margin-top: 5px;
    /* background-color: black; */
}
.customer-info-notice-wapper{
    width: 100%;
    height: 110px;
    margin-top: 10px;
    /* background-color: antiquewhite; */
}

.customer-info-notice{
    width: 100%;
    height: 30px;
    line-height: 30px;
   
}
.notice-text{
    float: left;
    margin-left: 10px;
    font-size: 19px;
}

.customer-info-notice-text{
    width: 100%;
    height: 80px;
    /* background-color: black; */
}

.in-notice-text{
    width: 100%;
    height: 50px;
    float: left;
    /* background-color: blue; */
}
.in-notice-detail{
    width: 100%;
    height: 30px;
    line-height: 30px;
    float: left;
    /* background-color: yellow; */
}

.notice-detail{
    width: 100px;
    height: 30px;
    /* background-color: brown; */
    float: left;
    margin-left: 70%;
}


.cutomer-user-info{
    width: 30%;
    height: 30px;
    line-height: 30px;
    margin-left:5px;
}

.icon-user{
    color: #2c2c2c;
 
}
.icon-touch{
    color: #13227a;
}

.font-size-big{ 
    font-size: 20px;
    font-weight:bold;
    color: #2c2c2c;
}
    


.customer-info{
    width: 100%;
    height: 120px;
    margin-top: -20px;
    /* background-color: coral;  */
}
.font-size-icon{
    font-size: 18px;
    margin-left: -10px;
    margin-right: 5px;
}


 

.apay-wapper{
    width: 100%;
    height: 30px;
    line-height: 30px;
    margin-top: 10px;
    /* background-color: black; */
}

.in-apay-left-icon{
    width: 10%;
    height: 30px;
    float: left;
    /* background-color: aqua; */
}

.apay-font-icon{
    font-size: 22px;
    color: #00a0e9;
}

.in-apay-text{
    width: 78%;
    height:30px;
    float: left;
    /* background-color: yellow; */
}

.apay-text{
    float: left;
    font-size: 18px;
}

.in-apay-right-icon{
    width: 10%;
    height: 30px;
    float: left;
    color: #00a0e9;
    /* background-color: cadetblue; */
}
.icon-submit{
    font-size: 20px;
}


.apay-size{
    width: 100%;
    height: 200px;
    background-color: aquamarine;
}

 

 




</style>
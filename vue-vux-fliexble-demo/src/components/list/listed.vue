<template>

  <div class="list-wapper">
 <div v-transfer-dom>
      <loading :show="showLoding" text="loading"></loading>
    </div>
      <!--头部-->
      <div class="list-wapper-header">
          <div class="wapper-header-before">
              <span class="header-font" @click="beforeDate">前一天</span>
          </div>
          <div class="wapper-header-date">
              <div class="header-icon">
                  <span class="iconfont font-icon-size">
                      &#xe772;
                  </span>
              </div>
              <div class="header-date">
<calendar    title="" ref="calendarValue"  v-model="demo3" @on-change="change"     disable-past></calendar>
              </div>
          </div>
          <div class="wapper-header-after">
              <span class="header-font" @click="afterDate">后一天</span>
          </div>
      </div>
      <!--列表-->
     <div v-show="listAppoint != null">
          
      <div class="wapper-list" v-for="(item,index) in listAppoint" :key="index">
          <div class="list-left">
         <div class="list-date">
              <div class="date-time" ref="dateTime">
                  {{item.appointTime}}
              </div>
              <div  class="date-char">
                  <span class="iconfont icon-time">&#xe638;</span>
              </div>
          </div>
          <div class="list-name">
              <span class=" icon-name iconfont">
                  &#xe632;
              </span>
              <span class="name-text" ref="servieName">
                  {{item.appointName}}
              </span>
          </div>
          <div class="list-desc">
              <span class="desc-text">
                 请提前10分钟前到达
              </span>
          </div>
          </div>
          <!--按钮-->
          <div class="list-right">
              <div class="right-top"></div>
              <div class="right-bottom"> 
                  <div class="normal-price">
                         <x-button type="primary" @click.native="toCustomer(item.appointId)">￥ 
                             <span ref="money">{{item.appointPrice}}</span>元</x-button>
                  </div>
              </div>
          </div>
      </div>
     </div>
     <!--没有数据的时候-->
     <div class="list-wapper-no-data" v-show="listAppoint == null">
         <div class="no-data-in">
             <span class="no-data-notice">
                 暂无当前选择的日期预约，请重新选择日期预约。
             </span>
         </div>
     </div>

  </div>
  

</template>

<script>

import { XButton,Calendar,Loading,TransferDomDirective as TransferDom, Scroller } from 'vux'
import axios from 'axios'
import storage from '../../model/storage';
export default {
    directives: {
    TransferDom
  },
    name:'list',
    components:{
        XButton,
        Calendar,
        Loading,
        Scroller
    },
    data(){
        return{
            demo3: '',
            calendarValue:'',
            listAppoint:[],  // 查询时间和服务页面列表
            dateWeek:'',     // 定义日期
            appName:'',      // 定义服务名
            appointId:'',    // 定义预约编号
            // time:'',     // 定义时间
            // serviceName:'',  // 定义服务名称
            // money:'',        // 定义价格
            // week:'',         // 定义日期
            response:'',
            showLoding:false
        }
    },
    created(){
        this.initDate();

        //初始化接受查询时间的值
        this.showLoading()
        this.getSelectDateTime();

        //初始化获取预约列表
 //       this.getAppointList();
    },
   
    mounted(){
    //     this.timer = setInterval(() => {
    //   console.log(this.$vux.loading.isVisible())
    // }, 1000)
 
    },
      beforeDestroy () {
    clearInterval(this.timer)
    },
    watch:{
 
    },
    methods:{
         showLoading () {
      this.$vux.loading.show({
        text: ''
      })
      setTimeout(() => {
        this.$vux.loading.hide()
      }, 500)
      },
        //初始化日期
        initDate(){
         var initDate = new Date();
         //当前系统的时间
         var year = initDate.getFullYear();
         var month = initDate.getMonth()+1;
         var date = initDate.getDate();
         var nowDate = year +'-' + month + '-' + date;
         this.demo3 = nowDate;
        },
        change(val){
            this.showLoading()
            this.calendarValue = val;
            setTimeout(() => {
             this.getAppointWeek();
                }, 800)
           
            
           console.log("val",this.calendarValue)
         },
         //获取日期的前一天
        beforeDate(){
            this.showLoading()
            this.demo3 = this.getDateStr(-1);
            // console.log("前天",this.getDateStr(-1));
       },
       //获取 日期的后一天
       afterDate(){
           this.showLoading()
           this.demo3 = this.getDateStr(1);
        //    console.log("后天",this.getDateStr(1))
       },
    // 判断日期 前天  getDateStr(-1)
    //         后天  getDateStr(1)          
     getDateStr(AddDayCount){
        //判断选中的是否null&空NAN
        if(!this.calendarValue){
            this.calendarValue = this.demo3
        }
    // 添加选中的值    
    var dd = new Date(this.calendarValue);
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
 //   console.log("getDate",dd)
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
  //  console.log(y,m,d)
    return y+'-'+ m +'-'+d;
        },
    //接受查询时间传过来的值
    getSelectDateTime(){
        
        this.dateWeek = this.$route.query.selectDateTime;
        this.appName = this.$route.query.appointname;
        console.log("接受过来的值",this.dateWeek,this.appName);
        console.log("接受过来value",this.calendarValue);
        // var week = this.dateWeek;
        // var name = this.appName;
        setTimeout(() => {
          this.getAppointDateAndName();
      }, 800)
       
 
      },
      //跳页客户信息传参
      // 日期、时间、服务、价格
      toCustomer(appointId){
        this.appointId = appointId;
        //   alert(appointId);
        //   var dateTime  = this.$refs.dateTime.innerHTML;
        //   var servieName = this.$refs.servieName.innerHTML;
        //   var money = this.$refs.money.innerHTML;
        //   var date = this.$refs.calendarValue.value;
       //   console.log("list----->>>>传递出的值:",dateTime,servieName,money,date);

       //接受参数
       if(appointId != null){
    axios({
    method: 'get',
    url:'http://www.gouptea.top/haircut/appoint/api/getAppointId',
    params: {
     appointId: this.appointId
    }
}).then((res)=>{
    this.respone = res.data.data;
    // console.log(this.respone.appointTime);
    // this.time = res.data.
    this.$router.push({path:'/customer',
    query:{appointId:this.respone.appointId,dateTime:this.respone.appointTime,servieName:this.respone.appointName,
    money:this.respone.appointPrice,date:this.respone.appointWeek}})
}) 
       }
       //带着参数路由
       // var dateTime = this.respone.appointTime;
      
      },
      //获取后台列表api
      getAppointList: function(){
          console.log("开始获取数据....")
          var list
          axios.get("http://www.gouptea.top/haircut/appoint/api/appoinstList")
          .then( (response) => {
            console.log(response.data);
            this.listAppoint = response.data.data;
            console.log(this.listAppoint)
          })
          .catch(function (error) {
            console.log(error);
          }); 
      },
      // 筛选日期获取后台API
      getAppointWeek : function(){
          console.log("getAppointWeek")
          if(this.calendarValue != null){
           axios({
    method: 'post',
    url:'http://www.gouptea.top/haircut/appoint/api/getAppointDateAndName',
    params: {
     date: this.calendarValue,
     appointName: this.appName
    }
}).then((res)=>{
    console.log(res.data);
    this.listAppoint = res.data.data;
})              
          }
      },
      //封装获取日期和服务方法
      getAppointDateAndName(){
          console.log("getAppointDateAndName")
        axios({
    method: 'post',
    url:'http://www.gouptea.top/haircut/appoint/api/getAppointDateAndName',
    params: {
     date: this.dateWeek,
     appointName: this.appName
    }
}).then((res)=>{
    console.log(res.data);
    this.listAppoint = res.data.data;
})
      },
    }
}
</script>

<style  >

 @import '../../assets/style/reset.css';
 
.iconfont{
    font-size: 28px;
}

.list-wapper{
    width: 100%;
    height: auto;
    
    background: #eeeeee;
}
.list-wapper-header{
    width: 100%;
    height: 50px;
    line-height: 50px;
    text-align: center;
    margin-bottom: 10px;
    background-color: #ffffff;
    /* background-color: black; */
    /* color: #52c3d5; */
   }

.wapper-header-before{
    width: 25%;
    height: 50px;
    /* background-color: blue; */
    float: left;
  
}
.wapper-header-date{
    width: 50%;
    height: 50px;
    float: left;

    /* background-color: #52c3d5; */
    
}
.wapper-header-after{
    width: 25%;
    height: 50px;
       float: left;
    /* background-color: blue; */
}

.header-font{
    font-size: 20px;
    color:#52c3d5;
}

.header-icon{
    width: 30%;
    height: 50px;
    float: left;
    font-size: 20px;
    /* background-color: black; */
}
.header-date{
    /* width: 70%; */
    height: 50px;
    float: left;
    margin-top: -8px;
    font-size: 15px;
    /* background-color: #52c3d5; */
}

.header-date  /deep/ .vux-cell-value{
    font-size: 17px;
    color: #3B3B3B;
}

.header-date  /deep/  .weui-cell_access .weui-cell__ft:after{
    margin-top: -1000px;
}

.font-icon-size{
    font-size: 25px;
    color: #1296db;
}

 
.header-text{
    width: 100%;
    height: 50px;
    /* background-color: brown; */
}

 
.wapper-list{
    width: 100%;
    height: 110px;
    /* border-top: 1px solid #BABABA; */
    /* border-bottom: 1px solid #BABABA; */
    background-color: #ffffff;
    margin-bottom: 10px;
    /* background-color: bisque; */
}
.icon-time{
    font-size: 25px;
    color: #d4237a;
}

.list-left{
    width:55%;
    height: 100px;
    float: left;
    /* background-color: black ; */
}

.list-date{
    width: 100%;
    height: 40px;
    line-height: 40px;
    /* background-color: blue; */
}

.date-time{
    width: 40%;
    height: 40px;
    float: left;
    font-size: 19px;
    color: black;
    /* background-color: blueviolet */
}

.date-char{
    width: 30%;
    height: 40px;
    float: left;
    /* background-color: burlywood;  */
}
.list-name{
    width: 100%;
    height: 30px;
    
    line-height: 30px;
    /* background-color: blueviolet; */
}
.icon-name{
    width: 20%;
    height: 30px;
    float: left;
    font-size: 28px;
    color: #1296db;
    /* background-color: darkgray; */
}

.name-text{
    width: 30%;
    height: 30px;
    font-size: 18px;
    float: left;
    color: #3B3B3B;
    /* background-color: cyan; */
}
.list-desc{
    width: 100%;
    height: 30px;
    line-height: 30px;
   
    /* background-color:burlywood; */
}

.desc-text{
    margin-left: 15px;
    font-size: 15px;
    float: left;
    
}

.list-right{
    width: 45%;
    height: 100px;
    float: left;
    /* background-color: blue; */
}

.right-top{
    width: 100%;
    height: 50px;
    /* background-color:darkmagenta; */
}

.right-bottom{
    width: 100%;
    height: 50px;
    /* background-color: bisque; */
}
.normal-price{
    width: 100%;
    height: 20px;
    margin-left: 10px;
    margin-top: 10px;
    }

/** 没有数据的样式 */    
.list-wapper-no-data{
    width: 100%;
    height: auto;
}

.no-data-in{
    width: 100%;
    margin:0 auto;
}

.no-data-notice{
    color:#2c2c2c;
    font-weight:bold;
    font-size: 16px;
    text-align: center;
}

</style>
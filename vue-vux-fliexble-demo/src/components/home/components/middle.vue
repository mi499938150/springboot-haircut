<template>
    <div>

         <div class="middle">
             <!--选择功能服务-->
             <div class="middle-wapper">
                 <div class="middle-wapper-in">
                     <div class="middle-in-first">
                         <div class="middle-in-first-left">
                             <span class="iconfont icon-Username">&#xe60d;</span>
                   
                         </div>
                         <div class="middle-in-first-right">
                               <div class="middle-in-first-right-in">
                                   <span class="left-in-text">XX美发店</span>
                               </div>
                         </div>

                     </div>
                     <div class="middle-in-second">
                         <div class="middle-in-first-left">
                              <span class="iconfont icon-service">&#xe632;</span>
                         </div>  
                         <div class="middle-in-first-right">
                              <router-link to="/serviced"> 
                             <div class="middle-in-second-right-in">
                                 <span class="right-in-text"   ref="serviceName" >{{aName?aName:'请输入需要的服务'}} </span>
                             </div>
                                 </router-link>
                         </div>
                      
                     </div>
                 </div>
             </div>
             <!-- 预约日期-->
             <div class="middle-date-wapper">
                 <div class="middle-date-in">
                     <div class="middle-date-in-left">
                            <span class="iconfont icon-date">&#xe772;</span>
                     </div>
                     <div class="middle-date-in-right">

  
      <calendar    title="" ref="calendarValue"  v-model="demo2" @on-change="change"  disable-past></calendar>
                     </div>
                     <div class="middle-date-in-end">
                         {{calendarWeek}}
                     </div>
                     
                 </div>
             </div>

            <!-- 查询按钮 -->
            <div class="middle-button-wapper">
                <div class="middle-button-in">
                   <x-button type="primary" @click.native="toList">查询预约</x-button>
                </div>
            </div>
         </div>
         <!-- 划线边界-->
         <!-- <div class="middle-line">
             <hr style="height:1px;border:none;border-top:9px solid 	#E5E5E5" />
         </div> -->
         <!-- 最后占位符 -->
         <!-- <div class="middle-end-wapper">
             <div class="middle-end-in"></div>
         </div> -->
    </div>
</template>

<script>
import { Scroller } from 'vux'
import { Alert,AlertModule } from 'vux'
import { XButton,Calendar } from 'vux'
export default {
    //接受父子组件传过来的值
    props:['aName'],
    components:{
        Scroller,
        XButton,
        Calendar
    },
    data(){
        return{
            demo2: 'TODAY',
            calendarWeek:'',
        }
    },
    created(){

    },
    mounted(){
      
    },
    watch:{
       
    },
    methods:{
        toList(){
          //  this.$router.push({path:'/listed'})
          var selectDate  = this.$refs.calendarValue.value;
          var aName = this.$refs.serviceName.innerHTML;
          
          if((aName.trim()) == '请输入需要的服务'){
              this.showModule()
              return;
          }
          console.log("selectDate = ",selectDate);
          console.log("home_name = ",aName);
          this.$router.push({path:'/listed',query:{selectDateTime:selectDate,appointname:aName}})
        },
    //初始化信息
     change(val){
         //选择日期的时间
         var calendarValue  = val;
         var calendarObject = new Date(calendarValue);
         var day = calendarObject.getDay();
         //当前系统的时间
         var dateObject = new Date();
        // var day = dateObject.getDay();
     //    console.log('day',day);
         //获取年月日
         var year = dateObject.getFullYear();
         var month = dateObject.getMonth()+1;
         var date = dateObject.getDate();
         var sysCalendar = year +'-' + month + '-' + date;
          

  console.log(sysCalendar+ '  ===== ' + val); 
         if(val == sysCalendar){
            this.calendarWeek = '今天';
                  console.log('今天',day);
                  return;   
         }else{
              
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
        //   console.log(val)
     },
        //判断服务名为空的时候弹窗
    showModule () {
      AlertModule.show({
        title: '消息',
        content: '请您输入需要的服务',
        onShow () {
          console.log('Module: I\'m showing')
        },
        onHide () {
          console.log('Module: I\'m hiding now')
        }
      })
    }
    },
  
    showModuleAuto () {
      this.showModule()
      setTimeout(() => {
        AlertModule.hide()
      }, 3000)
    },
}
</script>

<style scoped>

 *{
     margin: 0 auto ;
 }

.middle{
    width: 96%;
    height: auto;
  
}
.middle-wapper{
    width: 100%;
    height: 120px;
    margin-top: 50px;
    border: 1px solid #3B3B3B; 
     border-radius:5px;


}
.middle-wapper-in{
   width: 85%;
   height: 90px;
   line-height: 90px;
   margin-top: 15px;
   /* background-color: aqua; */
}
.middle-in-first{
    width: 100%;
    height: 35px; 
    margin-top: 6px;
    float: left;
    margin-bottom: 10px;
    /* background-color: blue; */
}
.middle-in-second{
    width: 100%;
    height: 35px; 
    margin-top: 6px;
    float: left;
    margin-bottom: 10px;
    /* background-color: blue; */
}


.iconfont{
    font-size: 40px;
}



.middle-in-first-left{
    width: 10%;
    height: 35px;
    line-height: 35px;
   
    float: left;
    /* background-color: aquamarine; */
}
.middle-in-first-right{
    width: 80%;
    height: 35px;
    line-height: 35px;
  
   /* background-color: blanchedalmond; */
    /* border-bottom: 1px solid #BABABA; */
}
.middle-in-second-right-in{
    width: 90%;
    /* width: 80%; */
    height: 35px;
    border-bottom: 1px solid #BABABA;
 
      
    /* line-height: 30px; */
    /* background-color: black;   */
}

.left-in-text{
    margin: 5px 5px;
    font-size: 18px;
}

.right-in-text{
    float: left;
    margin: 5px 5px;
    color:#3B3B3B;
    margin-left: 10px;
    font-size: 19px;
}

 

.icon-Username{
      color: #13227a;
      
}

.icon-service{
    color: #1296db;
}

.icon-date{
 
    color: #1296db;
      font-size: 32px;
}

/**
   */
.middle-date-wapper{
    width: 100%;
    height: 35px;
    margin-top: 20px;
    border: 1px solid #3B3B3B;
    border-radius:5px;
} 

.middle-date-in{
    width: 100%;
    height: 35px;
   margin-top: 0px;
    /* background-color: black; */
}

.middle-date-in-left{
    width: 10%;
    height: 35px;
    line-height: 35px;
    margin-top: 1px;
    float: left;
    margin-left: 20px;
    /* background-color: bisque; */
}
 

.middle-date-in-right{
    width: 50%;
    height: 35px;
     float: left;
     font-size: 18px;
     color:black;
    /* background-color: blue; */
}

.middle-date-in-right  /deep/ .vux-cell-value{
    color: #3B3B3B;
}
.middle-date-in-right  /deep/  .weui-cell_access .weui-cell__ft:after{
    margin-top: -1000px;
}


.middle-date-in-end{
    width: 20%;
    height: 30px;
   padding: 0.25rem 0.4rem;
    float: left;
    font-size: 18px;
    color: #F75000;
    /* background-color: gray; */
}

.middle-button-wapper{
    width: 100%;
    height: 50px;
    margin-top: 40px;
    /* background-color: beige; */
}

.middle-button-in{
    width: 100%;
    height: 50px;
    line-height: 50px;
 
    /* background-color: blanchedalmond; */
}

.middle-line{
    margin-top: 20px;
}

.middle-end-wapper{
    width: 100%;
    height:auto;
    background-color: blue;
}

.middle-end-in{
  width: 50%;
  height: 30px;
  background-color: antiquewhite;
}

 
</style>
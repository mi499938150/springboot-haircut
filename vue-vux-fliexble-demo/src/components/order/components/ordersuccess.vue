<template>
    <div class="order-success-wapper">
        <div class="success-wapper">
            <div class="success-icon-wapper"></div>
                <div class="success-icon">
                <span class=" iconfont icon-font"></span>   
            </div>
            <div class="success-font-first">
                <span class="font-first">恭喜您预约提交成功！</span>
            </div>
            <div class="success-font-second">
                <span class="font-second">感谢您的支持!</span>
            </div>
            <div class="orderCheck-button">
                <router-link :to="{path:'/orderstatus'}">
   <x-button type="primary" action-type="button">查询预约</x-button>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
import { XButton } from 'vux'
export default {
    name:'orderSuccess',
 components: {
    XButton
  },
  data(){
      return{
          
      }
  },
  created(){
 
  }
}
</script>

<style>

*{
    margin: 0 auto;
}
.order-success-wapper{
    width: 100%;
    height: auto;
    /* background: grey; */
 
}

.success-wapper{
    width: 95%;
    height:400px;
    
    /* background-color: black; */
}

.success-icon-wapper{
    width: 100%;
    height: 100px;
    /* background-color: azure; */
}

.success-icon{
    width: 40%;
    height: 130px;
    /* background-color: bisque; */
}

.success-font-first{
    width: 100%;
    height: 30px;
    line-height: 30px;
    margin-top: 20px;
    /* background-color: blue; */
}
.font-first{
    color: black;
    font-size: 22px;
    text-align: center;
}

.success-font-second{
    width: 100%;
    height: 30px;
    line-height: 30px;
    margin-top: 10px;
    /* background-color: blue; */
}

.font-second{
  color:gray;
  font-size: 19px;
   text-align: center;
}

.orderCheck-button{
    width: 100%;
    height: 40px;
    line-height: 40px;
    margin-top: 20px;
    /* background-color: blue; */
}


</style>
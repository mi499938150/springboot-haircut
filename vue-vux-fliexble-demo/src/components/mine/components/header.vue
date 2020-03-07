<template>
   <div>
    <div class="mine-wapper">
        <!--我的头部-->
        <div class="mine-wapper-header">
            <div class="wapper-header-in">
                <img class="header-in-left" :src="order.headImgUrl"/>
                <div class="header-in-right">
                    <div class="in-right-name">
                        <span class="wechat-name">
                        {{order.nickName}}
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <!-- 订单其它选项 -->
        <div class="middle-order-wapper">         
            <div class="order-wapper">
                <router-link :to="{path:'/order'}">
                <div class="order-wapper-icon">
                    <span class="iconfont icon-order">&#xe612;</span>
                </div>
                <div class="order-wapper-font">
                    <span class="order-font">订单</span>
                </div>
                </router-link>
            </div>
            
       
                        <div class="order-wapper">
                <div class="order-wapper-icon">
                    <span class="iconfont icon-order">&#xe61d;</span>
                </div>
                <div class="order-wapper-font">
                    <span class="order-font">优惠卷</span>
                </div>
            </div>
                        <div class="order-wapper">
                <div class="order-wapper-icon">
                    <span class="iconfont icon-order">&#xe621;</span>
                </div>
                <div class="order-wapper-font">
                    <span class="order-font">我的积分</span>
                </div>
            </div>
            
        </div>
        </div>
   </div>
</template>

<script>
import axios from 'axios'
export default {
    //接受父子组件传过来的值
    props:{
        openid:{
            type:String
        }
    },
    data(){
        return{
            order:{}
        }
    },
    created(){
        // alert(this.openid)
        this.getByOpenid()
    },
    methods:{
        getByOpenid(){
               if(this.openid != null){
            axios({
                method: 'get',
                url:'http://www.gouptea.top/haircut/order/userInfo/api/getByOrderyOpenId',
                params: {
                    openid:this.openid
                    // openid:'oA9nMwK9OluRANG86pHl8YeCERAk'
                }
            }).then((res)=>{
                console.log(res.data.data);
                this.order = res.data.data
            })    
               }
        }
    }
}
</script>

<style scoped>
@import '../../../assets/style/reset.css';


.iconfont{
    font-size: 26px;
}

.mine-wapper{
    width: 100%;
    height: auto;
}

.mine-wapper-header{
    width: 100%;
    height: 140px;

    /* background-color: yellow; */
}

.wapper-header-in{
     width: 100%;
    height: 140px;
    background-color:#F0F0F0;
}

.header-in-left{
    width: 30%;
    height: 110px;
    margin-left: 20px;
    margin-top: 10px;
    float: left;
    /* background-color: blue; */
    border-radius:80px
}

.header-in-right{
    width: 60%;
    height: 120px;
    float: left;
    margin-top: 10px;
    /* background-color: yellow; */
}

.in-right-name{
    width: 80%;
    height: 40px;
    margin-top: 40px;
    margin-left: 20px;
    /* background-color: blue; */
}
.wechat-name{
    height: 40px;
    line-height: 40px;
    font-size: 15px;
    float: left;
}

.middle-order-wapper{
    width: 100%;
    margin-top: 10px;
    height: 300px;
    /* background-color: blue; */
}
 
.order-wapper{
    width: 60px;
    height: 80px;
    float: left;
    margin: 0 auto;
    text-align: center;
    margin: 10px 10px 30px 45px;
    /* background-color: chartreuse; */
}

.order-wapper-icon{
    width: 100%;
    height: 50px;
    line-height: 50px;
    /* background-color: yellow; */
}

.order-wapper-font{
    width: 100%;
    height: 20px;
    line-height: 20px;
    /* background-color: brown; */
}
 

.icon-order{
    color: #5CACEE;
}

.order-font{
    font-size: 15px;
    color: #272727;
}
 

</style>
<template>
  <div class="all-style">
    <!--跑马灯标题-->
    <div class="header-title">
      <!--图标-->
      <span class="header-title-left">
        <span class=" iconfont icon-laba1"></span>
      </span>
      <!--文字-->
      <span class="header-title-right">
             本店优惠活动XXXXXXX，截止日期XXXXX   
      </span>
    </div>
    <!-- 轮播图 -->
    <div class="header-swiper">
      <swiper :list="demo01_list" :aspect-ratio="350/700" >

      </swiper>

    </div>
  </div>
</template>

<script >
import { Swiper } from 'vux'

const baseList = [{
  url: 'javascript:',
  img: 'http://www.gouptea.top/haircut/imgs/banner-2.jpg',
  title: '时尚美发'
}, {
  url: 'javascript:',
  img: 'http://www.gouptea.top/haircut/imgs/banner-3.jpg',
  title: '客户至上'
}, {
  url: 'javascript:',
  img: 'http://www.gouptea.top/haircut/imgs/banner-1.png', // 404
  title: '服务价表',
  // fallbackImg: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vw50iwj20ff0aaaci.jpg'
}]

export default {
   components:{
     Swiper
   },
   data(){
     return{
       demo01_list: baseList
     }
      
   }
}
</script>

<style scoped>
 
 *{
     margin: 0 auto ;
 }

.all-style{
    width: 96%;
    height: auto;
    position: relative;

   
      /* background-color: yellow; */
}

.header-title{
  width: 100%;
  height: 30px;
  line-height: 30px;
  /* background-color: gray; */
}
.icon-icon--{
  font-size: 10px;
}

.header-title-left{
  float: left;
}

.header-title-right{
   margin-left: 10px;
   font-size: 15px;
   float: left;
}

.header-swiper{
  margin-top: 10px;
  width: 98%;
  height: 150px;
  /* border: 3px solid #3B3B3B;
  border-radius:10px; */
}

.icon-header{
  color: #5CACEE	;
  font-size: 20px;
}



</style>
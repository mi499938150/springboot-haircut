<template>
  
  <div>
    <tabbar style="position:fixed">
      <tabbar-item link="/"
          :selected= "$route.path === '/'"
      >
      <i slot="icon" class="iconfont icon-color">&#xe60e;</i>
        <span slot="label"  class="icon-font" >预约</span>
      </tabbar-item>
      <tabbar-item  link="orderstatus"
          :selected= "$route.path === '/orderstatus'">
       <i slot="icon" class="iconfont icon-color">&#xe633;</i>
        <span slot="label" class="icon-font">预约详情</span>
      </tabbar-item>
      <tabbar-item  link="/mine"
          :selected= "$route.path === '/mine'" >
    <i slot="icon" class="iconfont icon-color" >&#xe629;</i>
        <span slot="label" class="icon-font">我的</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>

<script>
import { Tabbar, TabbarItem } from 'vux'
 
export default {
    name:'bottom',
    components:{
         Tabbar, 
         TabbarItem, 
    }
}
</script>

<style >

 @import '../../../assets/style/reset.css';

.icon-font{
  font-size: 15px;
}

</style>
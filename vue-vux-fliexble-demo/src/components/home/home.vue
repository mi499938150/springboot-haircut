
<template>
<div>
     
    <home-header></home-header>
    <!-- 父传子组件-->
    <home-middle :aName = "aName"></home-middle>
 
    <home-bottom ></home-bottom>
</div>
</template>

<script>
 
import HomeBottom from './components/bottom'
import HomeHeader from './components/header'
import HomeMiddle from './components/middle'
import { Scroller } from 'vux'
import storage from '../../model/storage.js'
var config = require('../../../config/index')
    config = config.build
export default {
    name:"home",
    components:{
          HomeBottom,
          HomeMiddle,
          HomeHeader,
          Scroller
    },
    data(){
        return{
            //声明预约名称
             aName: '请输入需要的服务'
        }
    },
    created(){
        // this.getByOpenid()
    },
    mounted(){
          //获取缓存数据
        this.aName = storage.get('storageAppointName');
        console.log('name = ' +this.aName);
    },methods:{
        getByOpenid(){
        //如果url里有openid，设置进cookie
        var openid = this.$route.query.openid;
        if(typeof openid !== 'undefined'){
         
            var exp = new Date();
            exp.setTime(exp.getTime() + 3600 * 1000);//过期时间60分钟
            document.cookie = 'openid=' + openid + ";expires=" + exp.toGMTString();
        }
        //获取openid
         if(getCookie('openid') == null) {
         location.href = config.openidUrl + '?returnUrl=' +  encodeURIComponent(config.AppointUrl+ '/#/');
         console.log("====",location.href = config.openidUrl + '?returnUrl=' +  encodeURIComponent(config.haircutUrl+ '/#/'))
         }
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
 
 

 
</style>
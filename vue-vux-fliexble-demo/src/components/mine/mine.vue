<template>
     <div>

            <mine-header :openid="openid"></mine-header>
            <mine-bottom></mine-bottom>    


    </div>   
</template>

<script>

import MineHeader from '../mine/components/header'
import MineBottom from '../home/components/bottom'
export default {
    name:'mine',
      data(){
        return{
            //openid 声明
            openid: getCookie('openid')
        }
    },
    components:{
        MineHeader,
        MineBottom
    },
    
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
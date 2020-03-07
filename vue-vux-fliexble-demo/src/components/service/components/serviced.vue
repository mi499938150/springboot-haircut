<template>

     <div >
             <!-- <x-header>This is the page title.</x-header> -->
         <div class="service-wapper-in">
                <!--服务标题-->
                <div class="wapper-in-title">
                    <div class="in-title">
                        <span>请选择需要的服务</span>
                    </div>
                </div>
                <!--水平线-->
                <div class="service-line">
                      <hr style="height:1px;border:none;border-top:9px solid 	#E5E5E5" />
                </div>
                <!--服务列表--->
                <div class="service-list-in">
                    <div class="list-in" v-for="(item,index) in listName" :key="index" v-on:click="getAppointName(item.appointName)">
                        <span class="in-text">
                            {{item.appointName}}
                        </span>
                    </div>
                 
                </div>
         </div>
     </div>
</template>

<script>
import { XHeader } from 'vux'
import axios from 'axios'
import storage from '../../../model/storage.js'
export default {
    name:'serviced',
    components:{
        XHeader
        },
    data(){
        return{
            listName:[''],
            storageAppointName:''
        }
    },
     created(){

        //初始化获取预约列表
        this.getAppointListName();
    },
    methods:{
        //获取服务器api
     getAppointListName: function(){
        console.log("开始获取数据....")
        var list
        axios.get("http://www.gouptea.top/haircut/appoint/api/appoinstList")
        // axios.get("http://localhost:8080/haircut/appoint/api/appoinstList")
        .then( (response) => {
        console.log(response.data);
        this.listName = response.data.data;
        console.log(this.listName)
        })
        .catch(function (error) {
        console.log(error);
        });
      },
      // 点击获取名称
      getAppointName(appointName){
          console.log('当前被点击的name = '+appointName);
          //添加数据缓存
          this.storageAppointName = appointName;
          storage.set('storageAppointName',this.storageAppointName);

          this.$router.push({path:'/'});
      }      
    }
}
</script>

<style>
 @import '../../../assets/style/reset.css';
 
.service-wapper{
    width: 100%;
    height: auto;
}

.wapper-in-title{
    width: 100%;
    height: 50px;
    line-height: 50px;
    margin-top: 10px;
 /* background-color: blueviolet; */
}
.in-title{
    font-size: 20px;
}

.service-line{
    margin-top: 10px;
}
.service-list-in{
    width: 100%;
    height: 300px;
    margin-top: 10px;
    /* background-color: black; */
}
.list-in{
    width: 100%;
    height: 50px;
    /* background-color: blue; */
    line-height: 50px;
    border-bottom: 1px solid 	#E5E5E5;
}

.in-text{
  font-size: 19px;
  float: left;
  margin-left: 20px;
}


</style>
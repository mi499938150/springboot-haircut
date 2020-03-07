<template>
    <div>
        <div class="show-refund-wapper">
            <div class="show-refund-wapper-in">
                <!--理由-->
                <div class="show-refund-reason-wapper">
                    <div class="show-refund-reason-desc">
                        <span class="show-refund-desc-font">请输入退票理由</span>
                    </div>
                    <div class="show-refund-reason-text">
                        <form>
                               <textarea class="show-refund-textarea"  v-model="msg" placeholder="询价备注（尺寸、材质等）"></textarea>
                        </form>
                    </div>
                </div>
                <!--按钮-->
                <div class="show-refund-button-wapper">
                    <div class="show-refund-button">
                        <x-button type="primary">确定退票</x-button>                 
                    </div>
                    <div class="show-refund-button">
                        <x-button type="warn" @click.native="toHome">暂时不退了</x-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { XButton } from 'vux'

export default {
  components: {
    XButton
  },
  methods:{
      toHome(){
          this.$router.push({path:'/home'})
      }
  }
}
</script>

<style>

 @import '../../../assets/style/reset.css';

.show-refund-wapper{
   width: 100%;
   height: auto;
}

.show-refund-wapper-in{
    position: absolute;
    width: 100%;
    height: 100%;
    background-color:	#F0F0F0;
}

.show-refund-reason-wapper{
    width: 100%;
    height: 30%;
    /* background-color: azure; */
}

.show-refund-reason-desc{
    width: 100%;
    height: 40px;
    line-height: 40px;
    /* background-color: black; */

}

.show-refund-desc-font{
    font-size: 19px;
    /* float: left; */
 
}

.show-refund-reason-text{
    width: 100%;
    height: 150px;
    /* background-color:blue; */
}

.show-refund-textarea{
 border:0;
 border-radius:5px;
 background-color:rgba(255, 255, 255, 0.98);
 width: 100%;
 height: 100px;
 padding: 10px;
 resize: none;
}

.show-refund-button-wapper{
    width: 94%;
    height: 100px;
    margin: 0 auto;
    /* background-color: black; */
}

.show-refund-button{
    width: 100%;
    line-height: 50px;
    height: 50px;
    /* background-color: aqua; */
}

</style>
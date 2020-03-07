<template>
  <div>
      <div class="J-weixin-tip weixin-tip" ref="myWeixinTip">
        <div class="weixin-tip-content" ref="myWeixinTipContent">
  <!--         请在菜单中选择在浏览器中打开,<br/>
          以完成支付 -->
        </div>
      </div>
      <div class="J-weixin-tip-img weixin-tip-img"></div>
    </div>
</template>
<script>
import ap from '../../assets/js/ap'
 import axios from 'axios'
export default {
      data(){
        return{
          appointId:'',
          name:'',
          phone:'',
          appointQuantity:'',
          subject:'',
          total_amount:'',
          html:'',
          submitOrderPost:'http://www.gouptea.top/haircut/alipay/wapPayServlet'
          // submitOrderPost:'localhost:8080/haircut/alipay/wapPayServlet'
        }
      },
     created(){
       this.alipay()
     },
     mounted(){
       this.getOrderParms()
     },
      methods:{

      getOrderParms(){
        this.appointId = this.$route.query.appointId
        this.name = this.$route.query.name
        this.phone= this.$route.query.phone
        this.appointQuantity = this.$route.query.appointQuantity
        this.subject = this.$route.query.subject
        this.total_amount = this.$route.query.total_amount
        },
      alipay(){

        axios({
          method: 'post',
          url: this.submitOrderPost,
          params: {
            //  'openid':getCookie('openid'),
            'openid':'oA9nMwK9OluRANG86pHl8YeCERAk',
             'appointId': this.$route.query.appointId,
             'name': this.$route.query.name,
             'phone': this.$route.query.phone,
             'appointQuantity': this.$route.query.appointQuantity,
               'subject': this.$route.query.subject,
              'total_amount': this.$route.query.total_amount
          }   
      }).then((response)=>{

    let divForm = document.getElementsByTagName('divform')
    if (divForm.length) {
      document.body.removeChild(divForm[0])
    }
     const div=document.createElement('divform');
     div.innerHTML=response.data; // data就是接口返回的form 表单字符串
     console.log(response.data)
     document.body.appendChild(div);
     document.forms[0].acceptCharset='utf-8';
     document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
    //  document.forms[0].submit(); 
    var queryParam = '';
    Array.prototype.slice.call(document.querySelectorAll("input[type=hidden]")).forEach(function (ele) {
      queryParam += '&' + ele.name + "=" + encodeURIComponent(ele.value);
  });
   

  var gotoUrl = document.querySelector("form").getAttribute('action')   + queryParam;
  //  alert(gotoUrl)
  _AP.pay(gotoUrl);
      })
},
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
 
import Vue from 'vue'
import Router from 'vue-router'
 
import index from '@/components/home/index'
import home from '@/components/home/home'
import mine from '@/components/mine/mine'
import serviced from '@/components/service/components/serviced'
import listed from '@/components/list/listed'
import customer from '@/components/customer/customer'
import order from '@/components/order/order'
import orderSuccess from '@/components/order/components/ordersuccess'
import orderstatus from '@/components/order/orderstatus'
import showrefund from '@/components/order/components/showrefund'
import gopay from '@/components/pay/gopay'
import alipay from '@/components/pay/alipay'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:'home',
      component:home
    },{
      path:'/mine',
      name:'mine',
      component:mine
    },
    {
      path:'/serviced',
      name:'serviced',
      component:serviced
    },
    {
      path:'/listed',
      name:'list',
      component:listed
    },
    {
      path:'/customer',
      name:'customer',
      component:customer
    },
    {
      path:'/order',
      name:'order',
      component: order
    },
    {
      path:'/orderSuccess',
      name:'orderSuccess',
      component: orderSuccess
    },
    {
      path:'/orderstatus',
      name:'orderstatus',
      component:orderstatus
    },
    {
      path:'/showrefund',
      name:'showrefund',
      component:showrefund
    },
    {
      path:'/gopay',
      name:'gopay',
      component:gopay
    },
    {
      path:'/alipay',
      name:'alipay',
      component:alipay
    }
  ]
})

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'lib-flexible'
import './assets/style/iconfont.css'
import moment from 'moment'

/**弹窗 */
import { Confirm } from 'vux'
Vue.component('confirm', Confirm)
import { LoadingPlugin } from 'vux'  
Vue.use(LoadingPlugin)
/**点击延迟 */
import  FastClick  from  'fastclick'
FastClick.attach(document.body);
Vue.config.productionTip = false
Vue.filter('datefmt', function (input, fmtstring) {
  return moment.unix(input).format(fmtstring)
})

Vue.filter('moment', function (value, formatString) {
  formatString = formatString || 'YYYY-MM-DD';
return moment(value).format(formatString);

});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

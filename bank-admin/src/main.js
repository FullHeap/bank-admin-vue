import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import axios from 'axios'
import VueAxios from 'vue-axios'
//使用vue-axios 插件
Vue.use(VueAxios, axios)


//element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/styles/element-variables.scss'
import '@/assets/styles/index.scss' // global css
import '@/assets/styles/base.scss' // base css

Vue.use(ElementUI, {
  size: 'small'
})

//图标引入
import './assets/icons'

//mock测试引入
//import './mock/mock.js'

//用户权限管理
import permission from './directive/permission'
import './permission' // permission control
Vue.use(permission)


import { parseTime, resetForm, addDateRange } from "@/utils/index";
Vue.prototype.addDateRange = addDateRange
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm

//全局弹出框
Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
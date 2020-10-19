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

import WlTreeTransfer from 'wl-tree-transfer' 
import 'wl-tree-transfer/lib/wl-tree-transfer.css' 
Vue.use(WlTreeTransfer)

Vue.use(ElementUI, {
  size: 'small'
})

//图标引入
import './assets/icons'

//mock测试引入
import './mock/mock.js'

//用户权限管理
import permission from './directive/permission'
import './permission' // permission control
Vue.use(permission)

//查找和刷新按钮组件
import RightToolbar from "@/components/RightToolbar";
//分页组件
import Pagination from "@/components/Pagination";



import { parseTime, resetForm, addDateRange, handleTree } from "@/utils/index";
Vue.prototype.addDateRange = addDateRange
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
//菜单解析
Vue.prototype.handleTree = handleTree

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
//全局组件
Vue.component('RightToolbar', RightToolbar)
Vue.component('Pagination', Pagination)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
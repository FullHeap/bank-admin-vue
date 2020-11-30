import Vue from 'vue'
import VueRouter from 'vue-router'

import PUBLIC from './modules/public'
import SYSTEM from './modules/system'

Vue.use(VueRouter)

export const constantRoutes = [
  ...SYSTEM,
  ...PUBLIC
]

const router = new VueRouter({
  mode: 'history', // 去掉url中的#  hash
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

export default router

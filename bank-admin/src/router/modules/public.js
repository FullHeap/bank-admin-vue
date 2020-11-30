/** 
 * 公共模块
 */
export default [
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    meta: { title: '系统登录' },
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },{
    path: '/500',
    component: (resolve) => require(['@/views/error/500'], resolve),
    hidden: true
  },
]
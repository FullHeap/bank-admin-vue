/** 
 * 系统管理模块
 */
/* Layout */
import Layout from '@/layout'

export default [
    {
        path: '/redirect',
        component: Layout,
        hidden: true,
        children: [
            {
                path: '/redirect/:path(.*)',
                component: (resolve) => require(['@/views/redirect'], resolve)
            }
        ]
    },
    {
        path: '',
        component: Layout,
        redirect: 'index',
        children: [
            {
                path: 'index',
                component: (resolve) => require(['@/views/index'], resolve),
                name: '首页',
                meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
            }
        ]
    },
    {
        path: '/user',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [
            {
                path: 'profile',
                component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
                name: 'Profile',
                meta: { title: '个人中心', icon: 'user' }
            }
        ]
    },
    {
        path: '/dict',
        component: Layout,
        hidden: true,
        children: [
            {
                path: 'type/data/:dictId(\\d+)',
                component: (resolve) => require(['@/views/system/dict/data'], resolve),
                name: 'Data',
                meta: { title: '字典数据', icon: '' }
            }
        ]
    },
    /* {
 path: '/job',
 component: Layout,
 hidden: true,
 children: [
   {
     path: 'log',
     component: (resolve) => require(['@/views/monitor/job/log'], resolve),
     name: 'JobLog',
     meta: { title: '调度日志' }
   }
 ]
},
{
 path: '/gen',
 component: Layout,
 hidden: true,
 children: [
   {
     path: 'edit',
     component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
     name: 'GenEdit',
     meta: { title: '修改生成配置' }
   }
 ]
} */
]
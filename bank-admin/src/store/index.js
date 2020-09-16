/* vuex封裝 */
import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'

//主页面layout
import app from './modules/app'
import tagsView from './modules/tagsView'
import permission from './modules/permission'
import settings from './modules/settings'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    app,
    user,
    tagsView,
    permission,
    settings
  },
  getters
})

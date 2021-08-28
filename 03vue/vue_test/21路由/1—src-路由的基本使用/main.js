import Vue from 'vue'
import App from './App.vue'

//引入 vuex


import VueRouter from "vue-router";
Vue.use(VueRouter)

import router from './router'

// 关闭生产提示
Vue.config.productionTip = false



new Vue({
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  },
  router,

}).$mount('#app')

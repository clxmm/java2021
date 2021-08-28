import Vue from 'vue'
import App from './App.vue'



// 关闭生产提示
Vue.config.productionTip = false

// 配置全局混合
// import {mixin2} from './mixin'
// Vue.mixin(mixin2)



import plugins from './plugins'

Vue.use(plugins,1,2)


new Vue({
  render: h => h(App),
}).$mount('#app')

import Vue from 'vue'
import App from './App.vue'


// 关闭生产提示
Vue.config.productionTip = false

// 配置全局混合
// import {mixin2} from './mixin'
// Vue.mixin(mixin2)

//

// const  demo = Vue.extend( {a:1,b:2});
// const  d = new demo;
//
// Vue.prototype.x = d



new Vue({
  render: h => h(App),

}).$mount('#app')

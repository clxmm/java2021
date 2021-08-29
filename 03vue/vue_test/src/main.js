import Vue from 'vue'
import App from './App.vue'



// 关闭生产提示
Vue.config.productionTip = false



// import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
// Vue.use(ElementUI);
import { Button, Row,DatePicker } from 'element-ui';


Vue.component(Button.name, Button);
Vue.component(Row.name, Row);
Vue.component(DatePicker.name,DatePicker)
/* 或写为
 * Vue.use(Button)
 * Vue.use(Select)
 */




new Vue({
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  },

}).$mount('#app')

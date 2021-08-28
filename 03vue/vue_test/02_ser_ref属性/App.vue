<template>
  <div>

    <h1 v-text="msg" ref="title">click</h1>
    <button @click="showDom">click</button>
    <School ref="sch"></School>
  </div>
</template>

<script>
//引入组件
import School from './components/Schoole'



export default {
  name: 'app',
  data() {
    return {
      msg: 'hello vue'
    }
  },
  components: {
    School,
  },
  methods:{
    showDom() {
      console.log(this.$refs.title) //真实DOM元素

      console.log(this.$refs.sch) //School组件的实例对象（vc）
    }
  }
}
</script>
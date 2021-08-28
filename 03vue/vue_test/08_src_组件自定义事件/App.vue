<template>
  <div class="app">

    <h1>{{ msg }}</h1>
    <Student></Student>

    <!-- 通过父组件给子组件绑定一个自定义事件实现：子给父传递数据（第一种写法，使用@或v-on） -->
    <!--    <School v-on.once:myEvent="getSchooleName1"/>-->

    <!-- 通过父组件给子组件绑定一个自定义事件实现：子给父传递数据（第二种写法，使用ref） -->
    <School ref="student" @click.native="show"/>


  </div>
</template>

<script>
//引入组件
import Student from './components/Student'
import School from "./components/School";


export default {
  name: 'app',
  data() {
    return {
      msg: 'hello vue',
      studentNameL: '',
    }
  },
  components: {
    Student,
    School
  },
  methods: {
    getSchooleName1(name, ...array) {
      console.log("1", name, array)
    },
    getsSchoolName2(name, ...array) {
      console.log("getsSchoolName2", name, array)
      this.studentNameL = name
    },
    show() {
      console.log("native")
    }
  },
  mounted() {
    this.$refs.student.$on('myEvent', this.getsSchoolName2) //绑定自定义事件
    // this.$refs.student.$once('myEvent',this.getsSchoolName2)  // 只出发一次
    // 设置3s后再绑定事件，可以更灵活一些
    // setTimeout(()=>{
    //   this.$refs.student.$on('myEvent',this.getsSchoolName2)
    // },3000)


  }
}
</script>

<style>

.app {
  background-color: chartreuse;
  padding: 10px;
}


</style>
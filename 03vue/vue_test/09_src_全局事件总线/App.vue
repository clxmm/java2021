<template>
  <div class="app">

    <h1>{{ msg }}</h1>
    <Student></Student>
    <School/>



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

  },
  mounted() {


  }
}
</script>

<style>

.app {
  background-color: chartreuse;
  padding: 10px;
}


</style>
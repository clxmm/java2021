<template>
  <div>
    <Student></Student>

    <hr>
    <School/>


  </div>
</template>

<script>
//引入组件
import Student from './components/Student'
import School from "@/components/MyHeader";


export default {
  name: 'app',
  data() {
    return {
      msg: 'hello vue',
    }
  },
  components: {
    Student,
    School
  },
  methods: {}
}
</script>
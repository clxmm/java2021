<template>
  <div>
    <Student name="clxmm" ></Student>

    <hr>
    <Student name="xsyl" :age="19"></Student>


  </div>
</template>

<script>
//引入组件
import Student from './components/Student'


export default {
  name: 'app',
  data() {
    return {
      msg: 'hello vue',
    }
  },
  components: {
    Student,
  },
  methods: {}
}
</script>
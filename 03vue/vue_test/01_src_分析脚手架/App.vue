<template>
  <div>
    <School></School>
    <Student></Student>
  </div>
</template>

<script>
//引入组件
import School from './components/Schoole'

import Student from './components/Student'


export default {
  name: 'app',
  components: {
    School,
    Student
  }
}
</script>
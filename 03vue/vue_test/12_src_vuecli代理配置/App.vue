<template>
  <div class="app">

    <button @click="getStudent">getStudent</button>


  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'app',
  data() {
    return {
      msg: 'hello vue',
      studentNameL: '',
    }
  },
  components: {},
  methods: {
    getStudent() {
      axios.get('http://localhost:8081/api1/student').then(
          response => {
            console.log("success:",response.data)
          },
          error => {
            console.log('fail',error)
          }
      )

    }
  },
  mounted() {


  }
}
</script>

<style>


</style>
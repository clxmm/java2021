<template>
  <div class="demo">
    <h2 class="title">name：{{ name }}</h2>
    <h2 class="title2">age: {{ age }}</h2>


  </div>
</template>

<script>

export default {
  name: 'Student',
  data() {
    return {
      msg: 'hello student',
      name: 'student name',
      age: 18
    }
  },
  methods: {},
  mounted() {
    this.$bus.$on('hello', (data) => {
      console.log('shtudent 组件接收到参数了', data)
    })
  }
}

</script>


<style scoped lang="less">
.demo {
  background-color: pink;

  .title2 {
    font-size: 40px;
  }
}

</style>
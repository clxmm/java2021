<template>
  <div class="demo">
    <h2 class="title">name：{{ name }}</h2>
    <h2 class="title2">age: {{ age }}</h2>


  </div>
</template>

<script>

import pubsub from 'pubsub-js'

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
    // this.$bus.$on('hello', (data) => {
    //   console.log('shtudent 组件接收到参数了', data)
    // })

    // this.pubid = pubsub.subscribe('hello', function (msgName, data) {
    //     console.log(this)
    //   console.log("hello:", data)
    // })

    this.pubid = pubsub.subscribe('hello', (msgName, data) => {
      console.log(this)
      console.log("hello:", data)
    })
  },
  beforeDestroy() {
    pubsub.unsubscribe(this.pubid)
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
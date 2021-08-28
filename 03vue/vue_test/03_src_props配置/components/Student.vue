<template>
  <div class="demo">
    <h1>{{ msg }}</h1>
    <h2>name：{{ name }}</h2>
    <h2>age：{{ myAge + 1 }}</h2>
    <button @click="showName">点我提示学校名</button>
    <button @click="myAge++">++</button>

  </div>
</template>

<script>

export default {
  name: 'School',
  data() {
    return {
      msg: 'hello student',
      myAge:this.age
    }
  },
  // props:['name','age'],  // 简单接受
  // 接受时进行类型限制
  /*  props :{
      name: String,
      age: Number
    },*/
  props: {
    name: {
      type: String,
      require: true,
    },
    age: {
      type: Number,
      default: 90 //默认值
    }
  },
  methods: {
    showName() {
      alert(this.name)
    }
  },
}

</script>


<style>

</style>
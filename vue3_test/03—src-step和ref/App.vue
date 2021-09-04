<template>
  <h1>app
  </h1>

  <h2>name:{{ name }}</h2>
  <h2>age:{{ age }}</h2>
  <button @click="syaHello">syaHello</button>
  <button @click="changeInfo">changeInfo</button>


  <br>
  <h3>工作种类：{{job.type}}</h3>
  <h3>工作薪水：{{job.salary}}</h3>

  <button @click="changeInfo">修改人的信息</button>
</template>

<script>
// import {h} from 'vue'

import {ref} from  'vue'
export default {
  name: 'App',
  setup() {
    // 数据
    let name = ref('clx')
    let age = ref(18)
    let job = ref({
      type:'前端工程师',
      salary:'30K'
    })




    // 方法
    function syaHello() {
      // alert(`name: ${name}   ,age: ${age}  hello`)
    }

    function changeInfo() {
      console.log(name,age)
      name.value = 'cclxmm11'
      age.value = 28


      console.log(job.value)
      job.value.type = 'UI设计师'
      job.value.salary = '60K'


    }


    // 返回一个对象（常用）
    return {
      name,
      age,
      job,


      syaHello,
      changeInfo
    }
    //返回一个函数（渲染函数）
    // return () => {
    //   return h('h1', 'clxmm')
    // };


  }
}
</script>

<style>

</style>

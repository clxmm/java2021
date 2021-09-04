<template>

  <h2>add:{{sum}} </h2>


  <button @click="sum++">add</button>
  <hr>

  <h2>msg: {{msg}}</h2>
  <button @click="msg+='!'">changeMsg</button>

  <hr>
  <h2>姓名：{{person.name}}</h2>
  <h2>年龄：{{person.age}}</h2>
  <h2>薪资：{{person.job.j1.salary}}K</h2>
  <button @click="person.name+='~'">修改姓名</button>
  <button @click="person.age++">增长年龄</button>
  <button @click="person.job.j1.salary++">涨薪</button>



</template>

<script>

import {ref,watch,reactive,watchEffect} from "vue";

export default {
  name: "Demo",

  setup() {

    let sum = ref(0)
    let msg = ref('hello')
    let person = reactive({
      name:'张三',
      age:18,
      job:{
        j1:{
          salary:20
        }
      }
    })


    watch(sum,(newV,oldV)=> {
      console.log("sum change:",newV,oldV)
    })

    //
    watchEffect(()=>{
      const  x1 = sum.value
      const x2 = person.job.j1.salary
      console.log('watchEffect -- ',x1,x2)
    })












    return {
      sum,
      msg,
      person,
    }

  }

}
</script>

<style scoped>

</style>
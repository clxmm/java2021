<template>
  <h1>person:</h1>
  <h2>name: {{ person.name }}</h2>
  <h2>age: {{ person.age }}</h2>


  <button @click="test"> demo hello</button>
</template>

<script>
import {reactive} from 'vue'

export default {
  name: "Demo",
  props: ['msg', 'school'],
  emits: ['hello'],
  setup(props, context) {
    // console.log('step', props)
    // console.log('step',context)   //相当与Vue2中的$attrs

    // console.log('---setup---111', context.emit) //触发自定义事件的。
    console.log('---setup---', context.slots) //插槽

    let person = reactive({
      name: 'clxmm',
      age: 18
    })

    function test() {
      context.emit('hello', 666)
    }


    return {
      person,
      test
    }

  }

}
</script>

<style scoped>

</style>
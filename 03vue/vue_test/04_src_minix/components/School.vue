<template>
  <div class="demo">
    <h1>{{ msg }}</h1>
    <h2>name：{{ name }}</h2>
    <h2>age：{{ age + 1 }}</h2>
    <button @click="showName">点我提示</button>
    <button @click="age++">++</button>

  </div>
</template>

<script>
import {mixinin} from '../mixin'

export default {
  name: 'School',
  data() {
    return {
      msg: 'hello student',
      name: 'clxmm',
      age: 18
    }
  },
  methods: {},
  mixins: [mixinin]
}

</script>


<style>

</style>
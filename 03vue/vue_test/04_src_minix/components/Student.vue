<template>
  <div class="demo">
    <h2>name：{{ name }}</h2>
    <button @click="showName">点我提示</button>

  </div>
</template>

<script>
import {mixinin} from '../mixin'

export default {
  name: 'School',
  data() {
    return {
      msg: 'hello student',
      name: 'school name',

    }
  },
  methods: {

  },
  mixins:[mixinin]
}

</script>


<style>

</style>
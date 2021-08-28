<template>
  <div class="demo">
    <h1>{{ msg }}</h1>
    <h2>name：{{ name | mySlice }}</h2>


    <input v-fbind:value="name">


  </div>
</template>

<script>

export default {
  name: 'School',
  data() {
    return {
      msg: 'hello student 11111 ',
      name: 'clxmm',
    }
  },
  methods: {},
}

</script>


<style>

</style>
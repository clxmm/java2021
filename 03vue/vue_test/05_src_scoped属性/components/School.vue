<template>
  <div class="demo">
    <h1>{{ msg }}</h1>
    <h2>name：{{ name }}</h2>

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


<style scoped>
  .demo {
    background-color: skyblue;
  }
</style>
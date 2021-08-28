<template>
  <div class="demo">
    <h2>name：{{ name }}</h2>
    <h2>age: {{ age }}</h2>


    <button @click="test">test</button>
  </div>
</template>

<script>

export default {
  name: 'Student',
  data() {
    return {
      msg: 'hello student',
      name: 'school name',
      age: 18

    }
  },
  methods: {
    test() {
      this.hello()
    }
  },
}

</script>


<style>

</style>
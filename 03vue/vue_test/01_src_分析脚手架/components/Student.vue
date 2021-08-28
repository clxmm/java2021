<template>
  <div class="demo">
    <h2>学生名称：{{ name }}</h2>
    <h2>age：{{ age }}</h2>
    <button @click="showName">点我提示学校名</button>
  </div>
</template>

<script>

export default {
  name: 'Student',
  data() {
    return {
      name: 'clxmm',
      age: 12
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
.demo {
  background-color: orange;
}
</style>
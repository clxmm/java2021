<template>
  <div class="demo">
    <h1>{{ msg }}</h1>
    <h2>name：{{ name }}</h2>

    <button @click="sendSchooleName">senName</button>

  </div>
</template>

<script>
import pubsub from 'pubsub-js'
export default {
  name: 'School',
  data() {
    return {
      msg: 'hello school 11111 ',
      name: 'clxmm',
    }
  },
  methods: {
    sendSchooleName() {
      // this.$bus.$emit('hello',this.name )
      pubsub.publish('hello',this.name)
    }
  },
  mounted() {

  },
  // beforeDestroy() {
  //   this.$bus.$off('hello')
  // }
}

</script>


<style scoped>
.demo {
  background-color: skyblue;
  padding: 5px;
  margin-bottom: 10px;
}
</style>
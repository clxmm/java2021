<template>

  <h2>add:{{ sum }} </h2>
  <button @click="sum++">add</button>

  <hr>
  <h2> x: {{ point.x }}, y:{{ point.y }} </h2>


</template>

<script>

import {ref} from 'vue'

import usePoint from "@/hooks/usePoint";

export default {
  name: "Demo",
  setup() {
    console.log('---setup---')

    let sum = ref(0)

    let  point = usePoint()

    return {
      sum,
      point
    }
  },


}
</script>

<style scoped>

</style>
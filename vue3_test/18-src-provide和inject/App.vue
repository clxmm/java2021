<template>

  <div class="app">
    <h3>app,name: {{ name }},price:{{ price }}W</h3>
    <Child/>
  </div>

</template>

<script>
import Child from "@/components/Child";

import {reactive, toRefs, provide} from 'vue'

export default {
  name: 'App',
  setup() {

    let card = reactive({
      name: 'bc', price: 40
    })

    provide('car', card)  //给自己的后代组件传递数据

    return {
      ...toRefs(card)
    }
  },
  components: {
    Child
  }

}
</script>

<style>

.app {
  background-color: gray;
  padding: 10px;
}

</style>

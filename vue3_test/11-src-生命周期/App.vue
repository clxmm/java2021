<template>

  <Demo v-if="isShowSDemo"/>
  <br>
  <button @click="isShowSDemo =  !isShowSDemo">changeDemo</button>
</template>

<script>
import Demo from "@/components/Demo";
import {ref} from 'vue'

export default {
  name: 'App',
  setup() {

    let isShowSDemo = ref(true)

    function showMsg(value) {
      alert(`你好啊，你触发了hello事件，我收到的参数是:${value}！`)
    }

    return {
      showMsg, isShowSDemo
    }
  },
  components: {
    Demo
  }
}
</script>

<style>

</style>

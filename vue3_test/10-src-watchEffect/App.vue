<template>

  <Demo/>



</template>

<script>
import Demo from "@/components/Demo";
// import { reactive} from 'vue'

export default {
  name: 'App',
  setup() {

    function showMsg(value) {
      alert(`你好啊，你触发了hello事件，我收到的参数是:${value}！`)
    }

    return {
      showMsg
    }
  },
  components: {
    Demo
  }
}
</script>

<style>

</style>

<template>
  <div>
    <button @click="isShow =! isShow"> 显示/隐藏</button>


    <transition
        name="animate__animated animate__bounce"
        enter-active-class="animate__wobble"
        leave-active-class="animate__backOutUp"
        appear
        >
      <h2 v-show="isShow">hello </h2>
    </transition>
  </div>
</template>

<script>
import "animate.css"

export default {
  name: "Test",
  data() {
    return {
      isShow: true
    }
  }
}
</script>


<style scoped>
h2 {
  background-color: orange;
}


</style>
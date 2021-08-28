<template>
  <div>
    <button @click="isShow =! isShow"> 显示/隐藏</button>


    <transition-group name="hello" appear>
      <h2 v-show="isShow" key="1">hello </h2>
      <h2 v-show="isShow" key="2">hello 哦哦哦哦哦</h2>
    </transition-group>
  </div>
</template>

<script>
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
  background-color: chartreuse;
  transition:  0.5s linear;
}


.hello-enter {
  transform: translateX(-100%);
}

.hello-enter-to {
  transform: translateX(0);
}


.hello-leave {
  transform: translateX(0);
}

.hello-leave-to {
  transform: translateX(-100px);
}


</style>
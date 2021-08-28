<template>
  <div>
    <button @click="isShow =! isShow"> 显示/隐藏</button>


    <transition name="myName" appear>
      <h2 v-show="isShow">hello </h2>
    </transition>
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
}

.com {
  animation: myC 1s;
}

.go {
  animation: myC 1s reverse;
}

.myName-enter-active {
  animation: myC 1s;
}

.myName-leave-active {
  animation: myC 1s reverse;
}


@keyframes myC {
  from {
    transform: translateX(-100%);
  }
  to {
    transform: translateX(0px);
  }
}

</style>
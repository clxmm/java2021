<template>
  <div>
    <button @click="isShow= true">alert</button>


    <teleport to="body">
      <div v-if="isShow" class="mask">
        <div class="dialog">
          <h3>nae</h3>
          <h3>nae</h3>
          <h3>nae</h3>
          <button @click="isShow=false">close</button>
        </div>

      </div>
    </teleport>


  </div>
</template>

<script>
import {ref} from "vue"

export default {
  name: "Dailog",
  setup() {
    let isShow = ref(false)

    return {
      isShow
    }
  }
}
</script>

<style scoped>

.mask {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
}

.dialog {
  position: absolute;
  text-align: center;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  width: 300px;
  height: 300px;
  background-color: green;
}

</style>
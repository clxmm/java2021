<template>


  <div class="son">
    <h3>son （孙）name: </h3>

    <Dailog/>
  </div>


</template>

<script>
import Dailog from "@/components/Dailog";
export default {
  name: "Son",
  setup() {

  },
  components:{Dailog}
}
</script>

<style scoped>
.son {
  background-color: skyblue;
  padding: 1px;
}

</style>
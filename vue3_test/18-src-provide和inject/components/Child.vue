<template>
  <div class="child">
    <h3>child(子) </h3>
    <Son/>
  </div>
</template>

<script>
import Son from "@/components/Son";
export default {
  name: "Child",
  components:{
    Son
  }
}
</script>

<style scoped>
.child {
  background-color: orange;
  padding: 10px;

}

</style>
<template>
  <div class="category">
    <h3>分类</h3>
    <slot>默认插槽</slot>

  </div>
</template>

<script>
export default {
  name: "Category"
}
</script>

<style scoped>
.category{
  background-color: skyblue;
  width: 200px;
  height: 300px;
}
h3{
  text-align: center;
  background-color: orange;
}
video{
  width: 100%;
}
img{
  width: 100%;
}
</style>
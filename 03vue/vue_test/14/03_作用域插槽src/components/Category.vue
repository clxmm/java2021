<template>
  <div class="category">
    <h3>分类</h3>
    <!-- 定义一个插槽（挖个坑，等着组件的使用者进行填充） -->
    <slot :gamesList="games">默认插槽1</slot>

  </div>
</template>

<script>
export default {
  name: "Category",
  data() {
    return {
      games: ['红色警戒', '穿越火线', '劲舞团', '超级玛丽'],
    }
  },
}
</script>

<style scoped>
.category {
  background-color: skyblue;
  width: 200px;
  height: 300px;
}

h3 {
  text-align: center;
  background-color: orange;
}

video {
  width: 100%;
}

img {
  width: 100%;
}
</style>
<template>
  <div>
    <h2>number:{{ id }}</h2>
    <h2>title:{{ title }}</h2>
  </div>
</template>

<script>
export default {
  name: "Deatil",
  props:['id','title']
}
</script>

<style scoped>

</style>
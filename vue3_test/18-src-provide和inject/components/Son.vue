<template>


  <div class="son">
    <h3>son （孙）name: {{ car.name }},price{{ car.price }}</h3>
  </div>
</template>

<script>
import {inject} from 'vue'

export default {
  name: "Son",
  setup() {
    let car = inject('car')
    console.log(car)
    return {
      car
    }
  }
}
</script>

<style scoped>
.son {
  background-color: skyblue;
  padding: 1px;
}

</style>
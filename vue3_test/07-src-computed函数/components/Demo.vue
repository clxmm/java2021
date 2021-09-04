<template>
  <h1>person:</h1>

  <h2>姓：<input type="text" v-model="person.name1"></h2>
  <h2>名：<input type="text" v-model="person.name2"></h2>

  <h2>全名：{{ person.fullname }}</h2>

  <h2><input type="text" v-model="person.fullname"></h2>


</template>

<script>
import {reactive, computed} from 'vue'

export default {
  name: "Demo",

  setup() {

    let person = reactive({
      name1: 'c',
      name2: 'lc'
    })

    // 简写；没有计算属性被修改的情况
    /*    let fullname = computed(() => {
          return person.name1 + '-' + person.name2
        })*/

    /*    person.fullname = computed(() => {
          return person.name1 + '-' + person.name2
        })*/

    //计算属性——完整
    person.fullname = computed({
      get() {
        return person.name1 + '-' + person.name2
      },

      set(value) {
        const nameArr = value.split('-')
        person.name1 = nameArr[0]
        person.name2 = nameArr[1]
      }

    })


    return {
      person,
    }

  }

}
</script>

<style scoped>

</style>
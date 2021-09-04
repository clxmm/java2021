<template>

  <br>

  <input type="text" v-model="keyWord1">

  <h3>{{ keyWord1 }}</h3>

</template>

<script>
import {ref, customRef} from 'vue'

export default {
  name: 'App',
  setup() {

    let keyWord = ref('hello')

    function myRef(value, delay) {
      let timer
      return customRef((track, trigger) => {
        return {
          get() {
            console.log(value)
            track()   //通知Vue追踪value的变化（提前和get商量一下，让他认为这个value是有用的）
            return value
          },
          set(newVal) {
            clearTimeout(timer)

            timer = setTimeout(() => {
              value = newVal
              trigger()  //通知Vue去重新解析模板
            }, delay)

          }
        }
      })

    }

    let keyWord1 = myRef('hello', 500)

    return {
      keyWord, keyWord1

    }
  },

}
</script>

<style>

</style>

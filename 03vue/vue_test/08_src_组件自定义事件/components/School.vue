<template>
  <div class="demo">
    <h1>{{ msg }}</h1>
    <h2>name：{{ name }}</h2>
    <button @click="senName1">sendName1</button>

    <button @click="unbindEvent">解绑组件自定义事件</button>

    <button @click="deathZj">销毁</button>

  </div>
</template>

<script>

export default {
  name: 'School',
  data() {
    return {
      msg: 'hello school 11111 ',
      name: 'clxmm',
    }
  },
  methods: {
    senName1() {
      this.$emit('myEvent',this.name,666,888,900)
    },
    unbindEvent() {
      this.$off('myEvent')   // 解绑一个
      // this.$off(['myEvent','demoEvent'])   // 解绑
      // this.$off() // 全部
    },
    deathZj() {
      this.$destroy()   // 销毁当前School 实例，销毁后所有School实例的自定义事件全都不奏效。
    }
  },
}

</script>


<style scoped>
  .demo {
    background-color: skyblue;
    padding: 5px;
    margin-bottom: 10px;
  }
</style>
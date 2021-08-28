<template>
  <div>
    <h1>当前求和为 {{$store.state.sum}} </h1>
    <select v-model.number="n">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="add">+</button>
    <button @click="duce">-</button>
    <button @click="odd">当前求和奇数再加</button>
    <button @click="wait">等一等再加</button>
  </div>
</template>

<script>
export default {
  name: "Count",
  data() {
    return {
      n: 1,
      sum: 0
    }
  },
  methods: {
    add() {
      this.$store.dispatch('jia',this.n)
    },
    duce() {
      this.$store.commit('JIAN',this.n)
    },
    odd() {
      this.$store.dispatch('jiaodd',this.n)
    },
    wait() {
      this.$store.dispatch('jiawait',this.n)
    }

  }
}
</script>

<style scoped>
button {
  margin-left: 10px;
}

</style>
<template>
  <div>
    <h1>当前求和为 {{ $store.state.sum }} </h1>
    <h1>放大10被 {{ bigSum }} </h1>

    <h1>school: {{ school }} xueke: {{ subject }} </h1>
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
import {mapState, mapGetters} from 'vuex'

export default {
  name: "Count",
  data() {
    return {
      n: 1,

    }
  },
  computed: {
    //靠程序员自己亲自去写计算属性
    // sum1() {
    //   return this.$store.state.sum
    // },
    // school() {
    //   return this.$store.state.school
    // },
    // subject() {
    //   return this.$store.state.subject
    // },

    //借助mapState生成计算属性，从state中读取数据。（对象写法）
    // ...mapState({he:'sum',xuexiao:'school',xueke:'subject'}),

    //借助mapState生成计算属性，从state中读取数据。（数组写法）
    ...mapState(['sum', 'school', 'subject']),


    /* bigSum(){
        return this.$store.getters.bigSum
      }, */

    //借助mapGetters生成计算属性，从getters中读取数据。（对象写法）
    // ...mapGetters({bigSum:'bigSum'})

    //借助mapGetters生成计算属性，从getters中读取数据。（数组写法）
    ...mapGetters(['bigSum'])
  },
  methods: {
    add() {
      this.$store.dispatch('jia', this.n)
    },
    duce() {
      this.$store.commit('JIAN', this.n)
    },
    odd() {
      this.$store.dispatch('jiaodd', this.n)
    },
    wait() {
      this.$store.dispatch('jiawait', this.n)
    },
    mounted() {
      const x = mapState({he: 'sum', xuexiao: 'school', xueke: 'subject'})
      console.log(x)
    },

  }
}
</script>

<style scoped>
button {
  margin-left: 10px;
}

</style>
<template>
  <div>
    <h1>人员列表</h1>
    <h1 style="color: red">count sum : {{ sum }}</h1>
    <input v-model="name" placeholder="输入姓名">
    <button @click="addPerson">添加</button>

    <ul>
      <li v-for="p in personList" :key="p.id">{{ p.name }}</li>

    </ul>
  </div>
</template>

<script>
import {nanoid} from 'nanoid'

export default {
  name: "Person",
  data() {
    return {
      name: ''
    }
  },
  methods: {
    addPerson() {
      const personObj = {id: nanoid(), name: this.name}
      this.$store.commit('ADD_PERSON', personObj)
      this.name = ''
    }
  },
  computed: {
    personList() {
      return this.$store.state.personList
    },
    sum() {
      return this.$store.state.sum
    }
  }
}
</script>

<style scoped>

</style>
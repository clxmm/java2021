<template>

    <div class="container">
      <Search/>

      <List/>
    </div>

</template>

<script>


import Search from "@/components/Search";
import List from "@/components/List";

export default {
  name: 'app',
  data() {
    return {
      msg: 'hello vue',
      studentNameL: '',
    }
  },
  components: {
    Search, List
  },
  methods: {},
  mounted() {


  }
}
</script>

<style>


</style>
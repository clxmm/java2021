<template>
  <div class="app">

    <Test/>

    <hr>

    <Test2/>


    <hr>
    <Test3></Test3>



  </div>
</template>

<script>
import Test from "@/components/Test";
import Test2 from "@/components/Test2";
import Test3 from "@/components/Test3";

export default {
  name: 'app',
  data() {
    return {
      msg: 'hello vue',
      studentNameL: '',
    }
  },
  components: {
    Test, Test2,Test3
  },
  methods: {},
  mounted() {


  }
}
</script>

<style>


</style>
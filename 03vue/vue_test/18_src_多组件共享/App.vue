<template>

  <div>
    <Count/>

    <hr>
    <!--    <Person/>-->
  </div>

</template>

<script>

import Count from "@/components/Count";
// import Person from "@/components/Person";

export default {
  name: 'app',

  components: {
    Count,
    // Person
  },
  methods: {},
  mounted() {

    // console.log("app",this)

  }
}
</script>

<style>

</style>
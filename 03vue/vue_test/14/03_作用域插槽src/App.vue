<template>

  <div class="appClass">


    <Category title="游戏">
      <template scope="scopeData">

        <ul>
          <li v-for="(g,index) in scopeData.gamesList" :key="index">{{ g }}</li>
        </ul>
      </template>
    </Category>

    <Category title="游戏">
      <template scope="{gamesList}">

        <ol>
          <li v-for="(g,index) in gamesList" :key="index">{{ g }}</li>
        </ol>
      </template>
    </Category>

    <Category title="游戏">
      <template scope="{gamesList}">
          <h4 v-for="(g,index) in gamesList" :key="index">{{ g }}</h4>
      </template>
    </Category>


  </div>

</template>

<script>

import Category from "@/components/Count";

export default {
  name: 'app',

  components: {Category},
  methods: {},
  mounted() {


  }
}
</script>

<style>
.appClass {
  display: flex;
  justify-content: space-around;
}

</style>
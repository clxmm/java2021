<template>
  <section class="jumbotron">
    <h3 class="jumbotron-heading">Search Github Users</h3>
    <div>
      <input type="text" v-model="keyword" placeholder="enter the name you search"/>
      &nbsp;
      <button @click="searchUsers">searchUsers</button>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "Search",
  data() {
    return {
      keyword: ''
    }
  },
  methods: {
    searchUsers() {
      this.$bus.$emit('getUsers',{isLoading:true,errMsg:'',users:[],isFirst:false})
      axios.get(`https://api.github.com/search/users?q=${this.keyword}`).then(
          response => {
            // console.log('sucees',response.data.items)
            this.$bus.$emit('getUsers',{isLoading:false,errMsg:'',users:response.data.items})
          },
          error => {
            this.$bus.$emit('getUsers',{isLoading:false,errMsg:error.message,users:[]})
          }
      )
    }
  },

}
</script>

<style scoped>

</style>
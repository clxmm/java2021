<template>
  <ul>
    <li v-for="m in msgs" :key="m.id">
      <!-- 跳转路由并携带query参数，to的字符串写法 -->
      <!--       <router-link :to="`/home/messages/detail?id=${m.id}&title=${m.title}`">{{m.title}}</router-link>&nbsp;&nbsp;-->
      <!-- 跳转路由并携带query参数，to的对象写法 -->
      <router-link :to="{
					// path:'/home/messages/detail',
					name:'xq',
					query:{
						id:m.id,
						title:m.title
					}
				}">
        {{ m.title }}
      </router-link>


    </li>

    <hr>
    <router-view/>

  </ul>
</template>

<script>
export default {
  name: "Messages",
  data() {
    return {
      msgs: [
        {id: '001', title: 'title01'},
        {id: '002', title: 'title02'},
        {id: '003', title: 'title03'}
      ]
    }
  },

}
</script>

<style scoped>

</style>
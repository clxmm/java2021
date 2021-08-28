<template>

  <div class="appClass">

    <Category title="美食">
      <img slot="demo1" src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg" alt="">
      <a  slot="demo2" href="http://www.atguigu.com">更多美食</a>
    </Category>

    <Category title="游戏">
      <ul>
        <li v-for="(g,index) in games" :key="index">{{ g }}</li>
      </ul>
    </Category>

    <Category title="电影">

      <video controls slot="demo1" src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"></video>
      <template v-slot:demo2>
        <div class="foot">
          <a href="http://www.atguigu.com">经典</a>
          <a href="http://www.atguigu.com">热门</a>
          <a href="http://www.atguigu.com">推荐</a>
        </div>
        <h4>欢迎前来观影</h4>
      </template>
    </Category>

  </div>

</template>

<script>

import Category from "@/components/Count";

export default {
  name: 'app',
  data() {
    return {
      foods: ['火锅', '烧烤', '小龙虾', '牛排'],
      games: ['红色警戒', '穿越火线', '劲舞团', '超级玛丽'],
      films: ['《教父》', '《拆弹专家》', '《你好，李焕英》', '《尚硅谷》']
    }
  },
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
<template>
  <div>
    <div>按钮</div>
    <input/>


    <hr>
    <el-button type="primary">主要按钮</el-button>


    <hr>
    <div class="block">
      <span class="demonstration">默认</span>
      <el-date-picker
          type="date"
          placeholder="选择日期">
      </el-date-picker>
    </div>



  </div>

</template>

<script>


export default {
  name: 'app',

  methods: {},
  mounted() {

    // console.log("app",this)

  }
}
</script>

<style>

</style>
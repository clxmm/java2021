<template>
  <li>
    <label>
      <input type="checkbox" :checked="td.done" @click="changCheck(td.id)"/>
<!--      <input type="checkbox" v-model="td.done"/>-->
      <span>{{ td.title }}</span>
    </label>
    <button class="btn btn-danger" @click="deleByid(td.id)" >删除</button>
  </li>
</template>

<script>

export default {
  name: 'List',
  data() {
    return {}
  },
  methods: {
    changCheck(id) {
      this.changeDone(id)
    },
    deleByid(id) {
      if (confirm("是否要删除？")){
        this.deleteTodoById(id)
      }
    }
  },
  // 接受对象
  props: ['td','changeDone','deleteTodoById']
}

</script>


<style scoped>

/*item*/
li {
  list-style: none;
  height: 36px;
  line-height: 36px;
  padding: 0 5px;
  border-bottom: 1px solid #ddd;
}

li label {
  float: left;
  cursor: pointer;
}

li label li input {
  vertical-align: middle;
  margin-right: 6px;
  position: relative;
  top: -1px;
}

li button {
  float: right;
  display: none;
  margin-top: 3px;
}

li:before {
  content: initial;
}

li:last-child {
  border-bottom: none;
}

li:hover {
  background-color: #dddddd;
}

li:hover button {
  display: block;
}

</style>
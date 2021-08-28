<template>
  <ul class="todo-main">


    <Item v-for="(todo) in todoList"
          :changeDone="changeDone"
          :key="todo.id"
          :deleteTodoById="deleteTodoById"
          :td="todo"/>

  </ul>
</template>

<script>
import Item from "@/components/Item";

export default {
  name: 'List',

  methods: {},
  components: {
    Item
  },
  props: ['todoList', 'changeDone', 'deleteTodoById']
}

</script>


<style scoped>
/*main*/
.todo-main {
  margin-left: 0px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding: 0px;
}

.todo-empty {
  height: 40px;
  line-height: 40px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding-left: 5px;
  margin-top: 10px;
}
</style>
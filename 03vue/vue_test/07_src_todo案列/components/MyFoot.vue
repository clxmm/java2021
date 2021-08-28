<template>
  <div class="todo-footer">
    <label>
      <input type="checkbox" v-model="isAll"/>
    </label>
    <span>
          <span>已完成{{ doneTotal }}</span> / 全部 {{ total }}
        </span>
    <button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
  </div>
</template>

<script>

export default {
  name: 'MyFoot',
  data() {
    return {}
  },
  methods: {
    clearAll() {
      this.clearTodoDone()
    }
  },
  computed: {
    doneTotal() {

      // const x = this.todoList.reduce((pre, current) => {
      //   console.log(pre, current)
      //   return pre + (current.done ? 1 : 0)
      // }, 0)
      return this.todoList.reduce((i, todo) => i + (todo.done ? 1 : 0), 0)
    },
    total() {
      return this.todoList.length
    },
    isAll: {
      get() {
        return this.doneTotal === this.total && this.total>0
      },
      set(val) {
        this.checkAllTodo(val)
      }
    }


  },
  props: ['todoList','checkAllTodo','clearTodoDone']
}

</script>


<style scoped>

/*footer*/
.todo-footer {
  height: 40px;
  line-height: 40px;
  padding-left: 6px;
  margin-top: 5px;
}

.todo-footer label {
  display: inline-block;
  margin-right: 20px;
  cursor: pointer;
}

.todo-footer label input {
  position: relative;
  top: -1px;
  vertical-align: middle;
  margin-right: 5px;
}

.todo-footer button {
  float: right;
  margin-top: 5px;
}

</style>
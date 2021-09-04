<template>
  <h1>app
  </h1>
  <h1>一个人的信息</h1>
  <h2>姓名：{{ person.name }}</h2>
  <h2>年龄：{{ person.age }}</h2>
  <h3>工作种类：{{ person.job.type }}</h3>
  <h3>工作薪水：{{ person.job.salary }}</h3>
  <h3>爱好：{{ person.hobby }}</h3>
  <h3>测试的数据c：{{ person.job.a.b.c }}</h3>

  <h3 v-show="person.sex">sex {{person.sex}}</h3>
  <button @click="changeInfo">修改人的信息</button>

  <button @click="addSex">add sex</button>
  <button @click="delSex">del sex</button>


</template>

<script>

import { reactive} from 'vue'

export default {
  name: 'App',
  setup() {
    // 数据
    //数据
    let person = reactive({
      name: '张三',
      age: 18,
      job: {
        type: '前端工程师',
        salary: '30K',
        a: {
          b: {
            c: 666
          }
        }
      },
      hobby: ['抽烟', '喝酒', '烫头']
    })


    // 方法
    function syaHello() {
      // alert(`name: ${name}   ,age: ${age}  hello`)
    }

    function addSex() {
      person.sex = '男'
    }

    function delSex() {
       delete  person.sex
    }

    // function changeInfo() {
    //   console.log(name,age)
    //   name.value = 'cclxmm11'
    //   age.value = 28
    //
    //
    //   console.log(job.value)
    //   job.type = 'UI设计师'
    //   job.salary = '60K'
    // }

    //方法
    function changeInfo() {
      person.name = '李四'
      person.age = 48
      person.job.type = 'UI设计师'
      person.job.salary = '60K'
      person.job.a.b.c = 999
      person.hobby[0] = '学习'
    }


    // 返回一个对象（常用）
    return {
      person,
      syaHello,
      changeInfo,
      addSex,
      delSex,
    }
    //返回一个函数（渲染函数）
    // return () => {
    //   return h('h1', 'clxmm')
    // };


  }
}
</script>

<style>

</style>

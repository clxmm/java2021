// 创建vuex中的核心store


//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//应用Vuex插件
Vue.use(Vuex)


// 求和功能相关的配置
const countOptions = {
    actions: {
        jia(context, value) {
            console.log('actions中的jia被调用了')
            context.commit('JIA', value)
        },
        jian(context, value) {
            console.log('actions jian()')
            context.commit('JIAN', value)
        },
        jiaodd(context, value) {
            console.log('actions jiaodd')
            if (context.state.sum % 2) {
                context.commit('JIA', value)
            }
        },
        jiawait(context, value) {
            setTimeout(() => {
                context.commit('JIA', value)
            }, 1000)
        }

    },
    mutations: {
        JIA(state, value) {
            console.log('mutations中的JIA被调用了')
            state.sum += value
        },
        JIAN(state, value) {
            state.sum -= value
        },
    },
    state: {
        sum: 0, //当前的和
        school: '尚硅谷',
        subject: '前端',
    },
    getters: {
        bigSum(state) {
            return state.sum * 10
        }
    }
}


// 人员管理功能相关的配置
const personOptions = {
    actions: {},
    mutations: {
        ADD_PERSON(state, p) {
            state.personList.unshift(p)
        }
    },
    state: {
        personList: [
            {id: '001', name: '张三'}
        ]
    },
    getters: {}
}


//创建并暴露store
export default new Vuex.Store({
    modules: {
        countAbout: countOptions,
        personAbout: personOptions
    }
})
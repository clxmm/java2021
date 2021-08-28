// 求和功能相关的配置
export default {
    namespaced: true,
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


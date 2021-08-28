// 人员管理功能相关的配置
export default {
    namespaced: true,
    actions: {
        addPersonWang(context, value) {
            if (value.name.indexOf('clx') === 0) {
                context.commit('ADD_PERSON', value)
            } else {
                alert("clx start")
            }
        }
    },
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
    getters: {
        firstPersonName(state) {
            return state.personList[0].name
        }


    }
}
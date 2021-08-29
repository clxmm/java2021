// 该文件专门用于创建整个应用的路由器


import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from "@/pages/News";
import Messages from "@/pages/Messages";
import Deatil from "@/pages/Deatil";

//创建并暴露一个路由器
export default new VueRouter({
    routes:[
        {
            path:'/about',
            component:About
        },
        {
            path:'/home',
            component:Home,
            children:[
                {
                    path:'news',
                    component:News
                },
                {
                    path: 'messages',

                    component: Messages,
                    children: [{
                        name:'xq',
                        path:'detail/:id/:title',
                        component:Deatil

                    }]


                }

            ]
        }
    ]
})
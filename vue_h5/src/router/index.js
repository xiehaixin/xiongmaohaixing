import {createRouter, createWebHashHistory} from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import maotaiCode from '@/views/maotaiCode/index'


const routes = [
    {
        path: '/',
        name: 'HelloWorld',
        component: HelloWorld
      },
      {
        path: '/maotaiCode',
        name: 'maotaiCode',
        component: maotaiCode
      }
]

const routerHistory = createWebHashHistory()

const routers = createRouter({
    history: routerHistory,
    routes:routes
})

export default routers

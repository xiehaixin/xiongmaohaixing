import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import maotaiCode from '@/views/maotaiCode/index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/maotaiCode',
      name: 'maotaiCode',
      component: maotaiCode
    },
  ]
})

import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import energy from '@/components/energy'
import mileage from '@/components/mileage'
import temperature from '@/components/temperature'
import voltageCurrent from '@/components/voltage_current'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/he',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'energy',
      component: energy
    },
    {
      path: '/mileage',
      name: mileage,
      component: mileage
    },
    {
      path: '/temperature',
      name: 'temperature',
      component: temperature
    },
    {
      path: '/voltageCurrent',
      name: 'voltageCurrent',
      component: voltageCurrent
    }
  ]
})

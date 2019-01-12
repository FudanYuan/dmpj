// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import energy from './components/energy'
import temperature from './components/temperature'
import mileage from './components/mileage'
import voltageCurrent from './components/voltage_current'
import G2Line from './components/G2Line'
import router from './router'

Vue.config.productionTip = false

Vue.component('energy', energy)
Vue.component('temperature', temperature)
Vue.component('mileage', mileage)
Vue.component('voltage_current', voltageCurrent)
Vue.component('G2Line', G2Line)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

import Vue from 'vue'
import Router from 'vue-router'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import MainTemplate from '@/components/MainTemplate.vue'
import BankList from '@/components/BanksList.vue'
import ProfitableBanks from '@/components/ProfitableBanks.vue'

Vue.use(Router)
Vue.use(Vuetify)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainTemplate',
      component: MainTemplate
    },
    {
      path: '/list',
      name: 'BankList',
      component: BankList
    },
    {
      path: '/best',
      name: 'ProfitableBanks',
      component: ProfitableBanks
    }
  ]
})

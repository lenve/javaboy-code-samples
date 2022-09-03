import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BpmnView01 from "@/views/BpmnView01";
import BpmnView02 from "@/views/BpmnView02";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },{
    path: '/01',
    name: '01',
    component: BpmnView01
  },{
    path: '/02',
    name: '02',
    component: BpmnView02
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/BpmnView01.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router

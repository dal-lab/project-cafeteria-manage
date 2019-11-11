import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import MenuHome from '../components/MenuHome.vue'
import Menu from '../components/Menu.vue'
import MenuplanHome from '../components/MenuplanHome.vue'
import MonthlyMenuplan from '../components/MonthlyMenuplan.vue'
import IngredientHome from '../components/IngredientHome.vue'
import NotFound from '../components/NotFound.vue'

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/menus',
            component: MenuHome
        },
        {
            path: '/menus/:menuId',
            component: Menu
        },
        {
            path: '/menuPlans',
            component: MenuplanHome
        },
        {
            path: '/menuPlans/:month',
            component: MonthlyMenuplan
        },
        {
            path: '/ingredients',
            component: IngredientHome
        },
        {
            path: '*',
            component: NotFound
        }
    ]
})

export default router
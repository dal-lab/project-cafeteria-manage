import * as api from '../api'

const actions = {

    // Menu Actions
    FETCH_MENUS({ commit }) {
        return api.menu.fetch().then(data => {
            commit('SET_MENUS', data)
        })
    },
    FETCH_MENU({ commit }, { id }) {
        return api.menu.fetch(id).then(data => {
            commit('SET_MENU', data)
        })
    },
    ADD_MENU({ dispatch }, { menuName }) {
        return api.menu.create(menuName).then(data => {
            dispatch('FETCH_MENUS')
            return data
        })
    },
    DELETE_MENU({ dispatch }, { id }) {
        return api.menu.destory(id).then(data => {
            dispatch('FETCH_MENUS')
        })
    },

    // Menuplan actions
    ADD_MENUPLAN({ dispatch }, { menuPlanMonth }) {
        return api.menuPlan.create(menuPlanMonth).then(data => {
            dispatch('GET_MENUPLANMONTH')
            return data
        })
    },
    GET_MENUPLANMONTH({ commit }) {
        return api.menuPlan.fetchMonth().then(data => {
            commit('SET_MENUPLANMONTH', data.existedMonthList)
        })
    },
    GET_MONTHLYMENUPLANS({ commit }, { year, month, weekCount }) {
        return api.menuPlan.fetchMonthlyMenuPlan(year, month, weekCount).then(data => {
            commit('SET_MONTHLYMENUPLANS', data)
        })
    },
    ADD_MENUTOMENUPLAN({ dispatch }, { workDayId, menuName, pos, year, month, weekCount }) {
        return api.menuPlan.ceateMenuPlan(workDayId, menuName, pos).then(_ => {
            dispatch('GET_MONTHLYMENUPLANS', { year, month, weekCount })
        })
    },
    DELETE_MENUPLAN({ dispatch }, { workDayId, menuId, year, month, weekCount }) {
        return api.menuPlan.deleteMenuPlan(workDayId, menuId).then(_ => {
            dispatch('GET_MONTHLYMENUPLANS', { year, month, weekCount })
        })
    }
}

export default actions
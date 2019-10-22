import * as api from '../api'

const actions = {
    FETCH_MENUS({ commit }) {
        return api.menu.fetch().then(data => {
            commit('SET_MENUS', data)
        })
    },
    ADD_MENU({ dispatch }, { menuName }) {
        return api.menu.create(menuName).then(data => {
            dispatch('FETCH_MENUS')
            return data
        })
    }
}

export default actions
import * as api from '../api'

const actions = {
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
    }
}

export default actions
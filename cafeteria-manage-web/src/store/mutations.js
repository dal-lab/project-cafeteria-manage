const mutations = {
    SET_IS_ADD_MENU(state, toggle) {
        state.isAddMenu = toggle
    },
    SET_IS_MENU(state, toggle) {
        state.isMenu = toggle
    },
    SET_MENUS(state, menus) {
        state.menus = menus
    },
    SET_MENU(state, menu) {
        state.menu = menu
    },
    
    SET_IS_ADD_MENUPLAN(state, toggle) {
        state.isAddMenuplan = toggle
    }
}

export default mutations
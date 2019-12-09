import axios from 'axios'

const DOMAIN = 'http://localhost:8080'

const request = (method, url, data) => {
    return axios({
      method, 
      url: DOMAIN + url, 
      data
    }).then(result => result.data)
      .catch(result => {
       throw result.response
    })
}

export const menu = {
  fetch(id) {
      return id ? request('get', `/menus/${ id }`) : request('get', '/menus')
  },
  create(menuName) {
    return request('post', '/menus', { menuName })
  },
  destory(id) {
    return request('delete', `/menus/${ id }`)
  }
}

export const menuPlan = {
  create(menuPlanMonth) {
    return request('post', '/workDay', { menuPlanMonth })
  },
  fetchMonth()  {
    return request('get', '/workMonth')
  },
  fetchMonthlyMenuPlan(year, month, weekCount) {
    return request('get', `/menuPlans?year=${year}&month=${month}&weekCount=${weekCount}`)
  },
  ceateMenuPlan(workDayId, menuName, pos) {
    return request('post', `/workDays/${workDayId}/menu`, {menuName, pos})
  },
  deleteMenuPlan(workDayId, menuId) {
    return request('delete', `/workDays/${workDayId}/menu/${menuId}`)
  },
  updateMenuPlan(menuPlanId, workDayId, menuId, pos) {
    return request('put', `/menuPlans/${menuPlanId}`, {workDayId, menuId, pos}) 
  }
}
import axios from 'axios'
import { create } from 'domain'

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
  fetchMonthlyMenuPlan(year, month) {
    return request('get', `/menuPlans?year=${ year }&month=${ month }`)
  },
  ceateMenuPlan(workDayId, menuName) {
    return request('post', `/workDays/${ workDayId }/menu`, { menuName })
  }
}
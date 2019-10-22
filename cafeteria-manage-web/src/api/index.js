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
    fetch() {
        return request('get', '/menus')
    },
    create(menuName) {
      return request('post', '/menus', { menuName })
    }
}
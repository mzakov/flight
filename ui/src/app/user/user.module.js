// import apiUrl from './api.url'
import appUser from './user.component.js'
// import userRoute from './user.route.js'
import UserService from './user.service.js'

export default
  angular
    .module('user', [])
    // .constant('apiUrl', apiUrl)
    .component('appUser', appUser)
    // .config(userRoute)
    .service('UserService', UserService)
    .name

import appLogin from './login.component.js'
import loginRoute from './login.route.js'
import LoginService from './login.service.js'

export default
  angular
    .module('login', [])
    .component('appLogin', appLogin)
    .config(loginRoute)
    .service('LoginService', LoginService)
    .name

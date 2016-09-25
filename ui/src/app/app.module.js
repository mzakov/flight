import flightMap from './map/map.module'
import apiUrl from './api.url'
import flightApp from './app.component.js'
import login from './login/login.module.js'
import homeRoute from './app.route.js'
import AppService from './app.service.js'

export default
  angular
    .module('flight', [
      'ngAria',
      'ngAnimate',
      'ngMaterial',
      'ngMessages',
      'ui.router',
      'login',

      flightMap
    ])
    .constant('apiUrl', apiUrl)
    .component('flightApp', flightApp)
    .config(homeRoute)
    .service('AppService', AppService)
    .name

export default
/* @ngInject */
function routes ($stateProvider, $urlRouterProvider, $mdIconProvider) {
  $urlRouterProvider.otherwise('login')
  $mdIconProvider.fontSet('md', 'material-icons')
  $stateProvider
    .state('home', {
      url: '/home',
      component: 'flightApp'
    })
}

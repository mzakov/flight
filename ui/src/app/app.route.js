export default
/* @ngInject */
function routes ($stateProvider, $urlRouterProvider, $mdIconProvider) {
  $urlRouterProvider.otherwise('home')
  $mdIconProvider.fontSet('md', 'material-icons')
  $stateProvider
    .state('home', {
      url: '/home',
      component: 'flightApp'
    })
}

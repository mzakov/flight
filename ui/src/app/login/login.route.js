export default
/* @ngInject */
function routes ($stateProvider) {
  $stateProvider
    .state('login', {
      url: '/login',
      component: 'appLogin'
    })
}

var bcrypt = require('bcryptjs')

export default
/* @ngInject */
class LoginController {
	constructor ($log, $scope, $rootScope, $http, $location, LoginService, AppService) {
  $log.debug('LoginController instantiated!')

  this.user = {'username': '', 'password': ''}
  this.register = function () {
    LoginService.register(this.user).then((result) => {
      if (result.data.name !== null) {
        $location.url('/home')
      } else {
        $location.url('/login')
      }
    })
  }

  this.login = function () {
    LoginService.login(this.user).then((result) => {
      if (bcrypt.compareSync(this.user.password, result.data.password)) {
        this.user = result.data
        AppService.user = result.data
        AppService.loggedIn = true
        $rootScope.message = 'Authentication successful!'
        $location.url('/home')
      } else {
        $rootScope.message = 'Authentication failed.'
        $location.url('/login')
      }
    })
  }
}
}

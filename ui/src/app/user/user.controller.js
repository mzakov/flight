
export default
/* @ngInject */
class UserController {
	constructor ($log, AppService, $scope, $timeout, $location, $state, $rootScope, UserService) {
  $log.debug('UserController instantiated!')
	let ctrl = this
  this.loggedIn = AppService.loggedIn
  this.user = AppService.user
  this.users = []

	$scope.$on('updateBookings', function (event, data) {
  $scope.bookings = data
})

  this.logout = function () {
    this.user = {}
    AppService.user = {}
    AppService.loggedIn = false
    $state.reload()
  }


	console.dir(ctrl.user.id)



  this.drawPath = function (item) {
    let color = '#'+Math.floor(Math.random()*16777215).toString(16)
    $rootScope.$broadcast('drawPathEvent', {
			origin: item.origin,
			destination: item.destination,
			color: color
		})
  }


	this.clearPath = function (item) {
		$rootScope.$broadcast('clearPathEvent', item)
	}

  this.getProfile = function () {
    console.dir(this.user)
  }

}
}

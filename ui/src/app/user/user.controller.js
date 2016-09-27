
export default
/* @ngInject */
class UserController {
	constructor ($log, AppService, $scope, $location, $state, $rootScope) {
  $log.debug('UserController instantiated!')

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

  this.drawAllPaths = function (items) {
    $rootScope.$broadcast('clearAllPaths')
    for (let item of items.flights) {
      this.drawPath(item)
    }
  }

  this.drawPath = function (item) {
    let color = '#' + Math.floor(Math.random() * 16777215).toString(16)
    $rootScope.$broadcast('drawPathEvent', {
      origin: item.origin,
      destination: item.destination,
      color: color
    })
  }

  this.clearAllPaths = function (items) {
    for (let item of items.flights) {
      this.clearPath(item)
    }
  }

  this.clearPath = function (item) {
    $rootScope.$broadcast('clearPathEvent', item)
  }
}
}

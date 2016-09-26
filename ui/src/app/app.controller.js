
export default
/* @ngInject */
class AppController {
	constructor ($log, AppService, $scope, $timeout, $location, $state, $rootScope) {
  $log.debug('AppController instantiated!')
	let ctrl = this
  this.loggedIn = AppService.loggedIn
  this.user = AppService.user
  this.users = []
	this.showProfile = false
  $scope.flights = []

  $scope.routes = []
  $scope.newBooking = {
    user: {},
    flights: []
  }

  this.logout = function () {
    this.user = {}
    AppService.user = {}
    AppService.loggedIn = false
    $state.reload()
  }

  $scope.reload = function () {
    AppService.getAllFlights().then((result) => {
      $scope.flights = result.data
    })
    $timeout(function () {
      $scope.reload()
    }, 5000)
  }
  $scope.reload()

  this.book = function (booking) {
    booking.user = this.user
    console.dir(booking)
    AppService.addBooking(booking).then((result) => {
      console.dir(result.data)
      // $scope.bookings.push(result.data)
    })
  }

  this.drawPath = function (item) {
    let color = '#'+Math.floor(Math.random()*16777215).toString(16)
    $scope.$broadcast('drawPathEvent', {
			origin: item.origin,
			destination: item.destination,
			color: color
		})
  }


	this.clearPath = function (item) {
		$scope.$broadcast('clearPathEvent', item)
	}

  this.getProfile = function () {


		AppService.getBookings(ctrl.user.id).then((result) => {
				AppService.bookings = result.data
				$scope.$broadcast('updateBookings', result.data)
				console.dir(AppService.bookings)
			}).then(() => {
					this.showProfile = true
			})

	}


	this.getHome = function () {
		this.showProfile = false
	}

  this.getRoutes = function () {
    AppService.getRoutes(ctrl.selectedOrigin, ctrl.selectedDestination).then((result) => {
      $scope.routes = result.data
      // console.dir(result.data)
    })
		$timeout(function () {
			ctrl.getRoutes(ctrl.selectedOrigin, ctrl.selectedDestination)
		}, 5000)
  }



//	Autocompele //////////////////////////////////////////////////////////

// list of `state` value/display objects
  this.cities = loadAll()
  this.selectedOrigin = null
  this.selectedDestination = null
  this.searchTextOrigin = null
  this.searchTextDestination = null
  this.querySearch = querySearch

  function querySearch (query) {
    var results = query ? this.cities.filter(createFilterFor(query)) : this.cities
    return results
  }

  function loadAll () {
    var allCities = 'Memphis, Knoxville, Chattanooga, Nashville'

    return allCities.split(/, +/g).map(function (city) {
      return {
        value: city.toLowerCase(),
        display: city
      }
    })
  }

  function createFilterFor (query) {
    var lowercaseQuery = angular.lowercase(query)

    return function filterFn (city) {
      return (city.value.indexOf(lowercaseQuery) === 0)
    }
  }
// End of autocomplete ///////////////////////////////////////////////////
}
}


export default
/* @ngInject */
class AppController {
	constructor ($log, AppService, $scope, $timeout, $location, $state, $rootScope) {
  $log.debug('AppController instantiated!')

  this.loggedIn = AppService.loggedIn
  this.user = AppService.user
  this.users = []
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
    }, 50000)
  }
  $scope.reload()

  this.book = function (flights) {
    $scope.newBooking.user = this.user
    $scope.newBooking.flights = flights
    console.dir($scope.newBooking)
    AppService.addBooking($scope.newBooking).then((result) => {
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
    console.dir(this.user)
  }

  this.getRoutes = function (origin, destination) {
    AppService.getRoutes(origin, destination).then((result) => {
      $scope.routes = result.data
      console.dir(result.data)
    })
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

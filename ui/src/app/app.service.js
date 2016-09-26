export default
	/* @ngInject */
 class AppService {
   constructor ($http, apiUrl) {
     this.$http = $http
     this.apiUrl = apiUrl
     this.user = {}
     this.loggedIn = false
     this.drawPaths = []
   }
addPath (item) { this.drawPaths.push([item.origin, item.destination, '#000099']) }

getAllUsers () { return this.$http.get(this.apiUrl + '/users/') }

getAllFlights () { return this.$http.get(this.apiUrl + '/flights/') }

addBooking (booking) { return this.$http.post(this.apiUrl + '/bookings/', booking) }

getRoutes (origin, destination) { return this.$http.get(this.apiUrl + '/flights/itin/' + origin.display + '/' + destination.display) }
}

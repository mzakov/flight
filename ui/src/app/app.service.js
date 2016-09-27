export default
	/* @ngInject */
 class AppService {
   constructor ($http, apiUrl) {
     this.$http = $http
     this.apiUrl = apiUrl
     this.user = {}
     this.loggedIn = false
     this.bookings = []
   }

getCities () { return this.$http.get(this.apiUrl + '/location/') }

getBookings (user_id) { return this.$http.get(this.apiUrl + '/users/' + user_id + '/bookings') }

getAllFlights () { return this.$http.get(this.apiUrl + '/flights/') }

addBooking (booking) { return this.$http.post(this.apiUrl + '/bookings/', booking) }

getRoutes (origin, destination) { return this.$http.get(this.apiUrl + '/flights/itin/' + origin.display + '/' + destination.display) }
}

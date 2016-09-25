var bcrypt = require('bcryptjs')
export default
	/* @ngInject */
 class LoginService {
	constructor ($http, apiUrl) {
  this.$http = $http
  this.apiUrl = apiUrl
	}

  login (user) {
    return this.$http.post(this.apiUrl + '/users/login/', user)
  }

  register (user) {
    user.password = bcrypt.hashSync(user.password, 10)
    return this.$http.post(this.apiUrl + '/users/', user)
  }
}

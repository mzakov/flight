import templateUrl from './map.component.html'

/* @ngInject */
class MapController {
  zoom = 7
  center = [35.5175, -86.5804]
  markers = []
  paths = []

  constructor ($map, locations, $rootScope, $scope, $state, AppService, $timeout) {
    let ctrl = this
    this.$map = $map
    this.loggedIn = AppService.loggedIn
    ctrl.map = $scope.map

    ctrl.updateMap = function () {
    google.maps.event.trigger($scope.map, 'resize')
    }

    const { memphis, nashville, knoxville } = locations
    let markers = []

    markers.forEach(marker => this.addMarker(marker))

    let paths = []

    $scope.$on('clearAllPaths', function (event) {
      ctrl.paths = []
    })

    $scope.$on('clearPathEvent', function (event, data) {
      let origin
      let destination
      $map.getMarkerByCityName(data.origin)
        .then((result) => {
          origin = result
          $map.getMarkerByCityName(data.destination)
            .then((result2) => {
              destination = result2
            }).then(() => {
              let check = {
                path: `[[${origin.latitude}, ${origin.longitude}], [${destination.latitude}, ${destination.longitude}]]`
              }

              for (let path of ctrl.paths) {
                if (path.path === check.path) {
                  let index = jQuery.inArray(path, ctrl.paths)
                  ctrl.paths.splice(index, 1)
                }
              }
            })
        })
    })

    $scope.$on('drawPathEvent', function (event, data) {
      let origin
      let destination
      let color = data.color
      $map.getMarkerByCityName(data.origin)
        .then((result) => {
          origin = result
          $map.getMarkerByCityName(data.destination)
            .then((result2) => {
              destination = result2
              ctrl.addPath(origin, destination, color)
            })
        })
    })
    paths.forEach(args => this.addPath(...args))
  }

  addMarker ({ latitude, longitude }) {
    this.markers.push({
      position: `[${latitude}, ${longitude}]`
    })
  }

  addPath (a, b, color) {
    this.paths.push({
      path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
      strokeColor: color,
      strokeOpacity: 1.0,
      strokeWeight: 3,
      geodesic: true
    })
  }
}

export default {
  templateUrl,
  controller: MapController,
  controllerAs: '$mapCtrl'
}

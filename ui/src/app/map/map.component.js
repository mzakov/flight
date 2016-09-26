import templateUrl from './map.component.html'

/* @ngInject */
class MapController {
  zoom = 7
  center = [35.5175, -86.5804]
  markers = []
  paths = []

  constructor ($map, locations, $rootScope, $scope) {
    let ctrl = this
    this.$map = $map

    // add markers from an angular constant
    const { memphis, nashville, knoxville } = locations
    let markers = [
      // memphis, nashville, knoxville
    ]

    markers.forEach(marker => this.addMarker(marker))

    // add paths manually
    let paths = [
      // [memphis, nashville, '#CC0099'],
      // [nashville, knoxville, '#AA1100']
    ]

    paths = []

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
              // let checkO = {
              //   position: `[${origin.latitude}, ${origin.longitude}]`
              // }
              // let checkD = {
              //   position: `[${destination.latitude}, ${destination.longitude}]`
              // }

              for (let path of ctrl.paths) {
                if (path.path === check.path) {
                  let index = jQuery.inArray(path, ctrl.paths)
                  ctrl.paths.splice(index, 1)
                }
              }
              // for (let marker of ctrl.markers) {
              //   if (marker.position === checkO.position){
              //     let index = jQuery.inArray(marker, ctrl.markers)
              //     ctrl.markers.splice(index, 1)
              //   }
              // }
              // for (let marker of ctrl.markers) {
              //   if (marker.position === checkD.position){
              //     let index = jQuery.inArray(marker, ctrl.markers)
              //     ctrl.markers.splice(index, 1)
              //   }
              // }
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
              // console.log(origin)
              // console.log(destination)
              ctrl.addPath(origin, destination, color)
            })
        })
    })
    // let paths = AppService.drawPaths

    paths.forEach(args => this.addPath(...args))

    // add path from webservice
    // $map.getMarkerByCityName('Chattanooga')
    //   .then(chattanooga => {
    //     console.log(knoxville)
    //     console.log(chattanooga)
    //     this.addPath(knoxville, chattanooga, '#FF3388')
    //   })
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
    // this.markers.push({
    //   position: `[${a.latitude}, ${a.longitude}]`
    // })
    // this.markers.push({
    //   position: `[${b.latitude}, ${b.longitude}]`
    // })
  }

}

export default {
  templateUrl,
  controller: MapController,
  controllerAs: '$mapCtrl'
}

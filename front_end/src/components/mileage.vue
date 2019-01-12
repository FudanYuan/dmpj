<template>
  <div :id="id">
  </div>
</template>

<script>
import G2 from '@antv/g2'

export default {
  name: 'mileage',
  data () {
    return {
      chart: null
    }
  },
  props: {
    chartData: {
      type: Array,
      default: function () {
        return {
          data: []
        }
      }
    },
    id: String
  },
  watch: {
    chartData: function (val, oldVal) {
      console.log('new: %s, old: %s', val, oldVal)
      this.drawChart(val)
    }
  },
  mounted () {
    this.drawChart()
  },
  methods: {
    // getRectPath (points) {
    //   var path = []
    //   for (var i = 0; i < points.length; i++) {
    //     var point = points[i]
    //     if (point) {
    //       var action = i === 0 ? 'M' : 'L'
    //       path.push([action, point.x, point.y])
    //     }
    //   }
    //   var first = points[0]
    //   path.push(['L', first.x, first.y])
    //   path.push(['z'])
    //   return path
    // },
    // getFillAttrs (cfg) {
    //   var defaultAttrs = Global.shape.interval
    //   var attrs = Util.mix({}, defaultAttrs, cfg.style, {
    //     fill: cfg.color,
    //     stroke: cfg.color,
    //     fillOpacity: cfg.opacity
    //   })
    //   return attrs
    // },
    drawChart () {
      this.chart && this.chart.destroy()
      this.chart = new G2.Chart({
        container: this.id,
        width: 700,
        height: 450
      })
      this.chart.source(this.chartData)
      const Util = G2.Util
      const Shape = G2.Shape
      const Global = G2.Global
      Shape.registerShape('interval', 'waterfall', {
        draw: function draw (cfg, container) {
          var defaultAttrs = Global.shape.interval
          var attrs = Util.mix({}, defaultAttrs, cfg.style, {
            fill: cfg.color,
            stroke: cfg.color,
            fillOpacity: cfg.opacity
          })
          // var attrs = this.getFillAttrs(cfg)
          var path = []
          for (var i = 0; i < cfg.points.length; i++) {
            var point = cfg.points[i]
            if (point) {
              var action = i === 0 ? 'M' : 'L'
              path.push([action, point.x, point.y])
            }
          }
          var first = cfg.points[0]
          path.push(['L', first.x, first.y])
          path.push(['z'])
          var rectPath = path
          rectPath = this.parsePath(rectPath)
          var interval = container.addShape('path', {
            attrs: Util.mix(attrs, {
              path: rectPath
            })
          })
          if (cfg.nextPoints) {
            var linkPath = [['M', cfg.points[2].x, cfg.points[2].y], ['L', cfg.nextPoints[0].x, cfg.nextPoints[0].y]]
            if (cfg.nextPoints[0].y === 0) {
              linkPath[1] = ['L', cfg.nextPoints[1].x, cfg.nextPoints[1].y]
            }
            linkPath = this.parsePath(linkPath)
            container.addShape('path', {
              attrs: {
                path: linkPath,
                stroke: '#8c8c8c',
                lineDash: [4, 2]
              }
            })
          }
          return interval
        }
      })
      for (var i = 0; i < this.chartData.length; i++) {
        var item = this.chartData[i]
        if (i > 0 && i < this.chartData.length) {
          if (Util.isArray(this.chartData[i - 1].mileage)) {
            item.mileage = [this.chartData[i - 1].mileage[1], this.chartData[i].mileage]
          } else {
            item.mileage = [this.chartData[i - 1].mileage, this.chartData[i].mileage]
          }
        }
      }
      this.chart.legend({
        custom: true,
        clickable: false,
        items: [{
          value: '里程增量',
          marker: {
            symbol: 'square',
            fill: '#1890FF',
            radius: 5
          }
        }]
      })
      this.chart.scale('time', {
        type: 'timeCat',
        mask: 'YYYY-MM-DD HH:mm'
      })
      this.chart.interval().position('time*mileage').tooltip('time*mileage', (time, mileage) => {
        if (Util.isArray(mileage)) {
          return {
            name: '里程增量',
            value: mileage[1] - mileage[0]
          }
        }
        return {
          name: '里程增量',
          value: mileage
        }
      }).shape('waterfall')
      // this.chart.line().position('time*mileage').shape('smooth')
      // this.chart.point().position('time*mileage').size(4).shape('circle').style({
      //   stroke: '#fff',
      //   lineWidth: 1
      // })
      this.chart.render()
    }
  }
}
</script>

<style scoped>

</style>

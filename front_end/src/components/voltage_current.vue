<template>
  <div :id="id">
  </div>
</template>

<script>
import G2 from '@antv/g2'
import DataSet from '@antv/data-set'

export default {
  name: 'voltage_current',
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
    pick (data, field) {
      return data.map(function (item) {
        var result = {}
        for (var key in item) {
          if (item.hasOwnProperty(key) && field.indexOf(key) !== -1) {
            result[key] = item[key]
          }
        }
        return result
      })
    },
    drawChart () {
      this.chart && this.chart.destroy()
      this.chart = new G2.Chart({
        container: this.id,
        width: 700,
        height: 400
      })
      this.chart.scale({
        time: {
          sync: true
        }
      })
      let view1 = this.chart.view()
      let ds = new DataSet()
      let dv = ds.createView().source(this.pick(this.chartData, ['time', 'start_voltage', 'end_voltage']))
      dv.transform({
        type: 'fold',
        fields: ['start_voltage', 'end_voltage'],
        key: 'voltage',
        value: 'value'
      })
      view1.source(dv, {
        'start_voltage': {
          alias: '起始电压'
        },
        'end_voltage': {
          alias: '终止电压'
        },
        'start_current': {
          alias: '起始电流'
        },
        'end_current': {
          alias: '终止电流'
        }
      })
      view1.axis('value', {
        label: {
          formatter: function formatter (val) {
            return val + 'V'
          }
        }
      })
      view1.tooltip({
        crosshairs: {
          type: 'line'
        }
      })
      view1.interval().position('time*value').color('voltage')
      // view1.point().position('time*value').color('voltage').size(4).shape('circle').style({
      //   stroke: '#fff',
      //   lineWidth: 1
      // })
      let view2 = this.chart.view()
      let ds2 = new DataSet()
      let dv2 = ds2.createView().source(this.pick(this.chartData, ['time', 'start_current', 'end_current']))
      dv2.transform({
        type: 'fold',
        fields: ['start_current', 'end_current'],
        key: 'current',
        value: 'currentvalue'
      })
      view2.source(dv2)
      view2.coord().reflect().reflect('x')
      view2.interval().position('time*currentvalue').color('current', ['#db4c3c', '#fec514'])
      // view2.point().position('time*currentvalue').color('current', ['#db4c3c', '#fec514']).size(4).shape('circle').style({
      //   stroke: '#fff',
      //   lineWidth: 1
      // })
      // this.chart.line().position('time*end_voltage').color('#db4c3c').shape('smooth')
      // this.chart.point().position('time*end_voltage').color('#db4c3c').size(4).shape('circle').style({
      //   stroke: '#fff',
      //   lineWidth: 1
      // })
      //
      this.chart.render()
    }
  }
}
</script>

<style scoped>

</style>

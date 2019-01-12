<template>
  <div :id="id">
  </div>
</template>

<script>
import G2 from '@antv/g2'
import DataSet from '@antv/data-set'

export default {
  name: 'energy',
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
      return data.map(function(item) {
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
        height: 400,
        sync: false
      })
      let scale = {
        predict_energy: {
          alias: 'predict_energy'
        },
        actual_energy: {
          alias: 'actual_energy'
        },
        start_soc: {
          alias: 'start_soc'
        },
        end_soc: {
          alias: 'end_soc'
        }
      }
      this.chart.axis('time', {
        grid: null
      })
      let view2 = this.chart.view()
      let ds2 = new DataSet()
      let dv2 = ds2.createView().source(this.chartData)
      dv2.transform({
        type: 'fold',
        fields: ['start_soc', 'end_soc'],
        key: 'soc',
        value: 'delt_soc'
      })
      view2.source(dv2, scale)
      let view1 = this.chart.view()
      let ds = new DataSet()
      let dv = ds.createView().source(this.chartData)
      dv.transform({
        type: 'fold',
        fields: ['predict_energy', 'actual_energy'],
        key: 'energy',
        value: 'energy_value'
      })
      view1.axis('time', {
        label: null
      })
      view1.source(dv, scale)
      this.chart.tooltip({
        crosshairs: {
          type: 'line'
        }
      })
      view1.line().position('time*energy_value').color('energy', ['#db4c3c', '#fec514']).shape('smooth')
      view1.point().position('time*energy_value').color('energy', ['#db4c3c', '#fec514']).size(4).shape('circle').style({
        stroke: '#fff',
        lineWidth: 1
      })
      view2.coord().reflect('x')
      view2.interval().position('time*delt_soc').color('soc', ['#db4c3c', '#4FAAEB'])
      this.chart.render()
    }
  }
}
</script>

<style scoped>

</style>

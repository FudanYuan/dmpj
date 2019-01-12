<template>
  <div id="chart">
    <energy class="chart" :chartData="energyData" :id="'c1'"></energy>
    <temperature class="chart" :chartData="temperatureData" :id="'c3'"></temperature>
    <mileage class="chart" :chartData="mileageData" :id="'c4'"></mileage>
    <voltage_current class="chart" :chartData="voltageCurrentData" :id="'c5'"></voltage_current>
  </div>
</template>

<script>
import G2Line from './components/G2Line.vue'
import energy from './components/energy.vue'
import axios from 'axios'
import qs from 'qs'

export default {
  name: 'App',
  comments: {
    G2Line,
    energy
  },
  data () {
    return {
      energyData: [],
      temperatureData: [],
      mileageData: [],
      voltageCurrentData: []
    }
  },
  methods: {
    getData () {
      axios({
        url: 'http://10.131.247.206:8081/dmpj/getData',
        // url: 'http://10.222.220.205:8081/dmpj/getData',
        method: 'post',
        data: qs.stringify({vId: 4, mId: 1, type: 4, sTime: 1513283411, eTime: 1513393411}),
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
        // .post('http://10.131.247.206:8081/dmpj/getData', {'vId': 4, 'mId': 1, 'type': 4, 'sTime': 1510993411, 'eTime': 1513393411})
        .then(response => {
          console.log(response)
          this.energyData = response.data.data.energyData
          this.temperatureData = response.data.data.temperatureData
          this.voltageCurrentData = response.data.data.voltageCurrentData
          this.mileageData = response.data.data.mileageData
        })
        .catch(error => (console.log(error)))
    }
  },
  mounted () {
    this.getData()
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  width: 100%;
  height: 100%;
}
#chart{
  margin: 0 auto;
  width: 90%;
}
.chart{
  float: left;
  margin: 20px;
}
</style>

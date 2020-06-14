<template>
  <div id="app">
    <h1>US Census Population Estimations</h1>
    <EstimationList msg=""/>
  </div>
</template>

<script>
import EstimationList from './components/EstimationList.vue'

export default {
  name: 'App',
  components: {
    EstimationList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

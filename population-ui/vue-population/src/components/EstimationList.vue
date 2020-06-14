<template>
  <div class="page-styles">
        <div class="input-group left-right-padding">
          <input v-model="searchterm" class="form-control" type="text" placeholder="Search for region or year..."/>
          <span @click="performSearch()" class="input-group-addon green-hover">
            <i class="searchicon glyphicon glyphicon-search black"></i>
          </span>
        </div>
    <ul>
    <li>
      <div class="estimationListContainer">
        <div class="estimationItem33">Region</div>
        <div class="estimationItem33">Population Estimate</div>
        <div class="estimationItem33">Estimation Date</div>
      </div>
    </li>
    <li v-for="(data,index) in filteredEstimates" :key="index">
      <div class="estimationListContainer">
        <div class="estimationItem33">{{data.region}}</div>
        <div class="estimationItem33">{{data.population}}</div>
        <div class="estimationItem33">{{data.estimatedate}}</div>
      </div>
    </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: 'EstimationList',
  props: {
    msg: String
  },
  data(){
    return {
      allEstimates:[],
      filteredEstimates:[],
      searchterm: ""
    }
  },
  mounted(){
    this.getPopulationEstimates()
  },
  methods: {
    getPopulationEstimates(){
      axios({method: "GET", "url": "http://localhost:8080/populationestimate"}).then(result => {
        for (var i = 0; i < result.data.length;i++){
          this.allEstimates.push({
            "region": result.data[i].regionDescription,
            "population": this.formatPopulationNumber(result.data[i].populationEstimate),
            "estimatedate": result.data[i].populationDate
          });
          this.filteredEstimates = this.allEstimates;
        }
      })
    },
    formatPopulationNumber(numberToFormat){
      return numberToFormat.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    performSearch(){
      console.log("Perform Search Called");
      if (this.searchterm === ""){
        this.filteredEstimates = this.allEstimates;
      } else {
        this.filteredEstimates = []; //clear the array
        console.log(this.allEstimates.length);
        for (var i=0; i < this.allEstimates.length;i++){
          if (this.allEstimates[i].region.toString().toLowerCase().includes(this.searchterm.toLowerCase())||
              this.allEstimates[i].estimatedate.toString().includes(this.searchterm)) {
            this.addEstimateToFilteredList(this.allEstimates[i].region,this.allEstimates[i].population,this.allEstimates[i].estimatedate);
          }
        }
      }
    },
    addEstimateToFilteredList(regionToAdd,populationToAdd,estimatedDateToAdd){
      this.filteredEstimates.push({
        "region": regionToAdd,
        "population":populationToAdd,
        "estimatedate": estimatedDateToAdd
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import 'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css';
.estimationListContainer{
  display: flex;
  flex-wrap: wrap;
}
.estimationItem33{
  width: 33%;
  flex-grow:1;
}
.estimationItem80{
  width:80%;
  flex-grow:1;
}

h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 0 10px;
}
a {
  color: #42b983;
}

.green-hover:hover{
  background-color:green;
}
.black{
  color: black;
}

.left-right-padding{
  padding-left: 4em;
  padding-right: 4em;
}

.page-styles{
  font-size: 1.5em;
}

</style>

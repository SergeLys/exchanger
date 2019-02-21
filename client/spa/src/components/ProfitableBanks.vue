<template>
  <div>
    <div class="card">
      <h2>Buying</h2>
      <br/><b><h3>EUR</h3></b><br/>
      <h4>Bank {{bestBuying.EUR.name}} : {{bestBuying.EUR.currencies[0].buyValue}} RUB</h4><br/>
      <input v-model.number="buyEUR" type="number" placeholder="Enter your EUR">
      <br/><h3>Cost: {{buyEUR * bestBuying.EUR.currencies[0].buyValue}} RUB</h3>
      <br/>
      <b><h3>USD</h3></b><br/>
      <h4>Bank {{bestBuying.USD.name}} : {{bestBuying.USD.currencies[1].buyValue}} RUB</h4> <br/>
      <input v-model.number="buyUSD" type="number" placeholder="Enter your USD">
      <br/><h3>Cost: {{buyUSD * bestBuying.USD.currencies[1].buyValue}} RUB</h3>
    </div>

    <div class="card">
      <h2>Selling</h2>
      <br/><b><h3>EUR</h3></b><br/>
      <h4>Bank {{bestSelling.EUR.name}} : {{bestSelling.EUR.currencies[0].sellValue}} RUB</h4> <br/>
      <input v-model.number="sellEUR" type="number" placeholder="Enter your EUR">
      <br/><h3>Cost: {{sellEUR * bestSelling.EUR.currencies[0].sellValue}} RUB</h3><br/>
      <b><h3>USD</h3></b><br/>
      <h4>Bank {{bestSelling.USD.name}} : {{bestSelling.USD.currencies[1].sellValue}} RUB</h4> <br/>
      <input v-model.number="sellUSD" type="number" placeholder="Enter your USD">
      <br/><h3>Cost: {{sellUSD * bestSelling.USD.currencies[1].sellValue}} RUB</h3>
    </div>
  </div>
</template>

<script>
    export default {
        name: "ProfitableBanks",
      data() {
        return {
          bestBuying: null,
          bestSelling: null,
          buyEUR:null,
          buyUSD:null,
          sellEUR:null,
          sellUSD:null
        }
      },
      methods:{

      },
      created: function () {
        this.$http.get('http://localhost:8181/api/best-buying').then(response => {
          response.json().then(result =>{
            this.bestBuying = result;
          })
        })
        this.$http.get('http://localhost:8181/api/best-selling').then(response => {
          response.json().then(result =>{
            this.bestSelling = result;
          })
        })
      }
    }
</script>

<style scoped>
.card{
  float: left;
  margin: 20px;
  padding: 15px;
  background-color: khaki;
}

input, textarea {
  background-color : white;
}
</style>

<template>
  <div id="info">
    <v-alert
      :value="true"
      type="info"
      color="blue"
    >
      <h3>Official rate: USD (RUB) : {{usd}} | EUR (RUB) : {{eur}}</h3>
    </v-alert>

  </div>
</template>

<script>

export default {
      name: "OfficialRate",
      data() {
        return {
          usd : 0,
          eur : 5,
          currencies:[]
        }
      },
      created: function () {
        this.$http.get('http://localhost:8181/api/official-rate').then(response => {
          response.json().then(result =>
          this.usd = result.currencies.get(0).buyValue;
        })
      }
    }

</script>

<style scoped>
  #info {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: left;
    color: #2c3e50;
  }
</style>


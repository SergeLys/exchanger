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
          eur : 5
        }
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

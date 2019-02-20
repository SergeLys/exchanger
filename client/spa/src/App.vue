npm<template>
  <div id="app">
      <v-toolbar dark color="primary">
        <v-toolbar-title>Exchanger</v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
      <router-view/>
      <v-footer class="pa-3" dark color="primary">
        <v-spacer></v-spacer>
        <div>&copy; {{ new Date().getFullYear() }}</div>
      </v-footer>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>

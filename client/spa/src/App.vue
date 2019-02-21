<template>
  <div id="app">
    <div id="header">
      <v-toolbar dark color="primary">
        <router-link tag="button" to="/">
          <v-toolbar-title>Exchanger $</v-toolbar-title>
        </router-link>
        <v-spacer></v-spacer>
        <v-toolbar-items class="hidden-sm-and-down">
          <router-link tag="button" to="/best">
            <v-btn flat>The best exchange rate</v-btn>
          </router-link>
          <router-link tag="button" to="/list">
            <v-btn flat>Banks list</v-btn>
          </router-link>
        </v-toolbar-items>
      </v-toolbar>
      <OfficialRate></OfficialRate>
    </div>
    <div id="content">
      <router-view/>
    </div>
    <div id="footer">
      <v-footer class="pa-3" dark color="primary">
        <v-spacer></v-spacer>
        <div>&copy; {{ new Date().getFullYear() }}</div>
      </v-footer>
    </div>
  </div>
</template>

<script>
import OfficialRate from '@/components/OfficialRate.vue'

export default {
  name: 'App',
  data() {
    return {
    }
  },
  components: {
    OfficialRate
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
}
  #header{
    width: 100%;
  }
#content{
}
#footer{
  position: fixed;
  left: 0; bottom: 0;
  width: 100%;
}
</style>

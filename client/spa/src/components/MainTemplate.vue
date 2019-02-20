<template>
  <div>
    <OfficialRate></OfficialRate>
    <ProfitableBanks></ProfitableBanks>
    <v-spacer></v-spacer>
    <BankList></BankList>
  </div>
</template>

<script>
  import BankList from './BanksList.vue'
  import OfficialRate from './OfficialRate.vue'
  import ProfitableBanks from './ProfitableBanks.vue'

export default {
  data() {
    return {
    }
  },
  components: {
    BankList,
    OfficialRate,
    ProfitableBanks
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>

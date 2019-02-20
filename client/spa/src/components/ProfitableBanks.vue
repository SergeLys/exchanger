<template>
  <div>
    <v-alert id="info"
      :value="true"
      type="success"
      color="green"
    >
      <h3>This is the most profitable course</h3>
    </v-alert>
    <v-data-table
      :headers="headers"
      :items="desserts"
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.name }}</td>
        <td class="text-xs-right">{{ props.item.calories }}</td>
        <td class="text-xs-right">{{ props.item.fat }}</td>
        <td class="text-xs-right">{{ props.item.carbs }}</td>
        <td class="text-xs-right">{{ props.item.protein }}</td>
        <td class="text-xs-right">{{ props.item.iron }}</td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
    export default {
        name: "ProfitableBanks",
      data() {
        return {
          search: '',
          headers: [
            {
              text: 'Bank',
              align: 'center',
              sortable: false,
              value: 'name'
            },
            { text: 'Buying USD (RUB)', value: 'buyingUsd' },
            { text: 'Selling USD (RUB)', value: 'sellingUsd' },
            { text: 'Buying EUR (RUB)', value: 'buyingEur' },
            { text: 'Selling EUR (RUB)', value: 'sellingEur' },

          ],
          banks: [
            {
              name: 'Sberbank',
              buyingUsd: 159,
              buyingEur: 15,
              sellingUsd: 11,
              sellingEur: 80,
            },
            {
              name: 'Alfa bank',
              buyingUsd: 15,
              buyingEur: 14,
              sellingUsd: 10,
              sellingEur: 8,
            }
          ]
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
    color: white;
  }
</style>

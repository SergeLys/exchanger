<template>
  <div>
    <v-alert id="info"
             :value="true"
             type="info"
             color="orange"
    >
      <h3>List of banks of Saint-Petersburg</h3>
    </v-alert>
    <v-card>
      <v-card-title>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="search"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="banks"
        :search="search"
      >
        <template slot="items" slot-scope="props">
          <td>{{ props.item.name }}</td>
          <td class="text-xs-left">{{ props.item.buyingUsd }}</td>
          <td class="text-xs-left">{{ props.item.sellingUsd }}</td>
          <td class="text-xs-left">{{ props.item.buyingEur }}</td>
          <td class="text-xs-left">{{ props.item.sellingEur }}</td>
        </template>
        <v-alert slot="no-results" :value="true" color="error" icon="warning">
          Your search for "{{ search }}" found no results.
        </v-alert>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "BanksList",
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

</style>

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
          <td class="text-xs-left">{{ props.item.currencies[1].buyValue }}</td>
          <td class="text-xs-left">{{ props.item.currencies[1].sellValue }}</td>
          <td class="text-xs-left">{{ props.item.currencies[0].buyValue }}</td>
          <td class="text-xs-left">{{ props.item.currencies[0].sellValue }}</td>
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
            { text: 'Buying USD (RUB)'},
            { text: 'Selling USD (RUB)'},
            { text: 'Buying EUR (RUB)'},
            { text: 'Selling EUR (RUB)'},
          ],
          banks: []
        }
      },
  created: function () {
    this.$http.get('http://localhost:8181/api/banks').then(response => {
      response.json().then(result =>{
        this.banks = result;
      })
    })
  }
    }
</script>

<style scoped>

</style>

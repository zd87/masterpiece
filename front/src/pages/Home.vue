<template>
	<div class="container">
        <div class="mb-5 d-flex justify-pace-between align-center">
            <div class="heading-1 ">Assets</div>
            <v-spacer></v-spacer>
            <v-switch v-model="group" inset label="Group by perimeter"></v-switch>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line outlined dense
                hide-details
            />
        </div>
        
        <v-card class="mb-5" flat>
            <v-card-text>
                <v-btn class="outlinedButton mr-3" outlined rounded @click="createServer=true">
                    <v-icon class="mr-3">mdi-plus-circle-outline</v-icon>
                    Add asset
                </v-btn>
            </v-card-text>
        </v-card>
        <CreateUpdateServer v-if="createServer" v-model="createServer" @closeCreate="createServer=false" />
        <ServerTable :search="search" :group="group" />
    </div>
</template>

<script>
import ServerTable from "@/components/serverTable/ServerTable.vue"
import CreateUpdateServer from "@/components/serverTable/CreateUpdateServer.vue"
import { get, call } from "vuex-pathify"
export default {
    components: {
        ServerTable,
        CreateUpdateServer
    },
    data (){
        return {
            createServer: false,
            search:"",
            group:true
        }
    },
    computed: {
        servers: get("servers/servers"),
    },
    mounted(){
        this.fetchServers();
    },
    methods: {
        ...call("servers", ["fetchServers"]),
    }
}
</script>
<style lang="scss" scoped>

</style>

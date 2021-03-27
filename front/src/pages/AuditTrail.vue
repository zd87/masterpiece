<template>
	<div class="customContainer">
        <div class="mb-5">
            <div class="heading-1 mr-8">Audit trail</div>
            <v-data-table 
                :headers="headers"
                :items="auditEntries"
            >
                <template v-slot:item.actionDate="{item}">
                    {{ formatDate(item.actionDate) }}
                </template>
                <template v-slot:item.serverBefore="{item}">
                    {{ item.serverBefore }}
                </template>
                <template v-slot:item.serverAfter="{item}">
                    {{ item.serverAfter }}
                </template>
            </v-data-table>
        </div>
        
        
    </div>
</template>

<script>
import moment from "moment";
import { get, call } from "vuex-pathify"
export default {
    components: {
        
    },
    data (){
        return {
           
        }
    },
    computed: {
        auditEntries: get("audit/auditEntries"),
        headers() {
            return [
                {text:"Date",       value:"actionDate",     width:"10%",    sortable: true},
                {text:"User",       value:"performedBy",    width:"10%",    sortable: true},
                {text:"User Ip",    value:"clientIp",       width:"10%",    sortable: true},
                {text:"Action",     value:"action",         width:"10%",    sortable: true},
                {text:"From",       value:"serverBefore",   width:"30%",    sortable: true},
                {text:"To",         value:"serverAfter",    width:"30%",    sortable: true}
            ];
        },
    },
    mounted(){
        this.fetchAuditEntries();
    },
    methods: {
        ...call("audit", ["fetchAuditEntries"]),
        formatDate(item){
            return moment(item).format("DD MMM YYYY");
        }
    }
}
</script>
<style lang="scss" scoped>
.v-btn--text{
    text-transform: none;
}
</style>

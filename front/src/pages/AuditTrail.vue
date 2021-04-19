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
                    <div v-if="item.serverBefore">
                        <p v-for="key of Object.keys(item.serverBefore)" :key="key">
                            <span class="font-weight-bold">{{ key }}</span> : 
                            <span v-if="key != 'attributes'" :class="{'redCode': !sameValue(item, key)}"> 
                                {{ showValue(item.serverBefore[key]) }}
                            </span>
                            <span v-else>
                                <span 
                                    v-for="(attr, index) of item.serverBefore[key]" :key="index" 
                                    :class="{'redCode': !foundAttr(item.serverAfter, attr)}"
                                >
                                    {{ attr.name }} : {{attr.value}} {{ index != item.serverBefore[key].length-1 ? ',':''}}
                                </span>
                            </span>
                        </p>
                    </div>
                </template>
                <template v-slot:item.serverAfter="{item}">
                    <div v-if="item.serverAfter">
                        <p v-for="key of Object.keys(item.serverAfter)" :key="key">
                            <span class="font-weight-bold">{{ key }}</span> : 
                            <span v-if="key != 'attributes'" :class="{'greenCode': !sameValue(item, key)}"> 
                                {{ showValue(item.serverAfter[key]) }} 
                            </span>
                            <span v-else>
                                <span 
                                    v-for="(attr, index) of item.serverAfter[key]" :key="index" 
                                    :class="{'greenCode': !foundAttr(item.serverBefore, attr)}"
                                >
                                    {{ attr.name }} : {{attr.value}} {{ index != item.serverAfter[key].length-1 ? ',':''}}
                                </span>
                            </span>
                        </p>
                    </div>
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
        },
        sameValue(item, key){
            return JSON.stringify(item.serverBefore?.[key]) === JSON.stringify(item.serverAfter?.[key]);
        },
        foundAttr(server, attr){
            return server?.attributes.find(el=> el.name === attr.name && el.value === attr.value);
        },
        showValue(value){
            return value ? value?.ip ?? value?.name ?? value : null;
        }
    }
}
</script>
<style lang="scss" scoped>
.v-btn--text{
    text-transform: none;
}
p {
    margin-bottom: 0;
}
.redCode{
    color: #f05b6f;
}
.greenCode{
    color: #4caf50;
}
</style>

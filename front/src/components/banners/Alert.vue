<template>
    <div id="alerts" class="d-flex flex-column">
        <v-alert 
            v-for="(alert, index) in reversedAlerts"
            :key="index"
            :type="alert.type"
            :icon="alert.icon"
            :value="alert.active"
            class="ma-2"
            transition="slide-x-reverse-transition"
            dense
            width="600px"
        >
            <div class="d-flex justify-space-between align-center">
                <span>{{alert.text}}</span>
                <v-btn icon rounded @click="remove(index)">
                    <v-icon dark>mdi-close</v-icon>
                </v-btn>
            </div>
        </v-alert>
    </div>
</template>

<script>
import { get, call } from "vuex-pathify"
export default {
    computed: {
        ...get("alert", ["alerts"]),
        reversedAlerts(){
            return this.alerts?.reverse();
        }
    },
    methods:{
        ...call("alert", ["remove"])
    }
   
}
</script>
<style lang="scss" scoped>
#alerts{
    position: absolute;
    bottom: 0;
    right: 0;
}
::v-deep .v-alert {
    &__icon{
        align-self: center;
    }
}
.v-application .error{
    background-color: #e3345a !important;
}
</style>
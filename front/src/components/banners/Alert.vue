<template>
    <div>
        <v-alert 
            v-for="(alert, index) in alerts"
            :key="index"
            :type="alert.type"
            :icon="alert.icon"
            :value="alert.active"
            class="ma-2"
            transition="slide-x-reverse-transition"
            dense
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
        alerts:get("alert/alerts")
    },
    methods:{
        ...call("alert", ["remove"])
    }
   
}
</script>
<style lang="scss" scoped>
.v-application .error{
    background-color: #e3345a !important;
}
</style>
<template>
    <div>
        <v-card class="ma-4" flat outlined>
            <v-card-title>Validation failed for the following fields:</v-card-title>
            <v-card-text>
                <ul>
                    <li v-for="(error, index) of errors" :key="index">
                        {{error.fieldName}} : {{ translation[error.errorCode] || error.errorCode }}
                    </li>
                </ul>
            </v-card-text>
        </v-card>
    </div>
</template>
<script>

export default {
    props: {
        errors: {
            type: Array,
            default: ()=>([])
        }
    },
    data(){
        return {
            
        }
    },
    computed: {
        translation(){
            return this.$i18n.messages[this.$i18n.locale].validation;
        }
        
    },
    watch: {
        
    },
    methods: {
        
    }
};
</script>
<style lang="scss" scoped>
.v-card{
    background-color: rgba(240,91,111,0.6) !important;
    &__title{
        font-size: unset !important;
        padding-bottom: 4px !important;
    }
}
</style>
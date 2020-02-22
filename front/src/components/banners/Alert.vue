<template>
    <v-alert v-model="show" type="error" class="alert">
        {{message}}
        <v-btn text color="accent" @click.native="show = false">Close</v-btn>
    </v-alert>
</template>

<script>
export default {
    data () {
        return {
            show: false,
            message: ""
        }
    },
    created() {
        this.$store.watch(state => state.alert.alert, () => {
        const msg = this.$store.state.alert.alert
        if (msg) {
            this.show = true
            this.message = this.$store.state.alert.alert
            this.$store.commit("alert/setAlert", "")
        }
        })
    }
}
</script>
<style lang="scss" scoped>
.v-application .error{
    background-color: #e3345a !important;
}
</style>
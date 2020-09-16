<template>
    <div>
        <!--if not authenticated-->
        <v-btn v-if="!token" text depressed tile to="/login"><v-icon>mdi-account</v-icon>{{$t("header.loginBtn")}}</v-btn>
        <!--if authenticated-->
        <v-btn v-else text depressed tile @click="logout"><v-icon>mdi-account</v-icon>{{$t("header.logoutBtn")}}</v-btn>
    </div>
</template>
<script>
import { get, call } from "vuex-pathify"
export default {
    components: {
       
    },
    data (){
        return {
            
        }
    },
    computed: {
        token: get("auth/token")
    },
    methods: {
        ...call("auth", ["updateToken"]),
        logout(){
            this.updateToken(null);
            this.$router.push({name:"login"});
        }
    }
};
</script>
<style lang="scss" scoped>

</style>
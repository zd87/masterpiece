<template>
    <div>
        <!--if not authenticated-->
        <v-btn v-if="!token" text depressed to="/login"><v-icon>mdi-account</v-icon>{{$t("header.loginBtn")}}</v-btn>
        <!--if authenticated-->
        <v-btn v-else text depressed @click="logout"><v-icon>mdi-account</v-icon>{{$t("header.logoutBtn")}}</v-btn>
    </div>
</template>
<script>
import { get } from "vuex-pathify"
export default {
    components: {
       
    },
    data (){
        return {
            
        }
    },
    computed: {
        token: get("user/token")
    },
    methods: {
        logout(){
            localStorage.removeItem("token");
            //token set in store for reativity
            this.$store.set("user/token", null);
            this.$router.push({name:"login"});
        }
    }
};
</script>
<style lang="scss" scoped>

</style>
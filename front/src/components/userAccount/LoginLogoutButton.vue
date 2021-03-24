<template>
    <div>
        <!--if not authenticated-->
        <v-btn v-if="!token" text depressed tile to="/login"><i class="fas fa-user-astronaut fa-2x mr-1"></i>{{$t("header.loginBtn")}}</v-btn>
        <!--if authenticated-->
        <v-btn v-else text depressed tile @click="logout"><i class="fas fa-user-astronaut fa-2x mr-2"></i>{{$t("header.logoutBtn")}}</v-btn>
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
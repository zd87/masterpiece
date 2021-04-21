<template>
	<div>
        <v-alert v-if="token && !tokenIsValid" type="info" text>
            Seems like your session has expired, please login.
        </v-alert>
        <LoginForm />
    </div>
</template>

<script>
import LoginForm from "@/components/userAccount/LoginForm.vue"
import { get } from "vuex-pathify"
export default {
    components: {
        LoginForm
    },
    data (){
        return {
            
        }
    },
    computed: {
        user: get("user/user"),
        ...get("auth", ["token", "tokenIsValid"])
    },
    mounted(){
        
    },
    methods: {
        
    }
}
</script>
<style lang="scss" scoped>

</style>

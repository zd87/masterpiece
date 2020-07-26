<template>
	<div>
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
        user: get("user/user")
    },
    mounted(){
        
    },
    methods: {
        
    }
}
</script>
<style lang="scss" scoped>

</style>

<template>
	<div id="header">
        <v-toolbar>
            <v-spacer></v-spacer>
            <v-btn text @click="createAccountModal=true"><v-icon>mdi-account</v-icon>{{$t("header.loginBtn")}}</v-btn>
        </v-toolbar>
		<CreateAccountModal :create-account-modal="createAccountModal" @closeModal="closeModal"/>
	</div>
</template>

<script>

import CreateAccountModal from "@/components/userAccount/CreateAccountModal.vue"

export default {
    components: {
        CreateAccountModal
    },
    data (){
        return {
            createAccountModal:false,
        }
    },
    methods: {
        closeModal(boolean) {
            this.createAccountModal = boolean;
            console.log(window.console);
            
        }
    }
}
</script>

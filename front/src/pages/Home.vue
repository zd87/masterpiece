<template>
	<div class="home">
		<v-btn @click="createAccountModal=true">Create account</v-btn>
		<CreateAccountModal :create-account-modal="createAccountModal" @closeModal="closeModal"/>
	</div>
</template>

<script>
// @ is an alias to /src
import CreateAccountModal from '@/components/CreateAccountModal.vue'

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
			console.log("from closeModal parent", this.createAccountModal);
			
		}
	}
}
</script>

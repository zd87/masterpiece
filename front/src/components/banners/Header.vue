<template>
	<div id="header">
        <v-app-bar app color="white">
            <v-list class="d-flex pa-0">
                <v-list-item v-for="(item, index) in filteredNavItems" :key="index">
                    <v-btn
                        :to="item.to"
                        :ripple="false"
                        depressed text
                    >{{ item.label }}</v-btn>
                </v-list-item>
            </v-list>
            <v-spacer></v-spacer>
            <LoginLogoutButton />
        </v-app-bar>
	</div>
</template>

<script>
import { get } from "vuex-pathify"
import LoginLogoutButton from "@/components/userAccount/LoginLogoutButton.vue"
export default {
    components: {
        LoginLogoutButton,
    },
    data (){
        return {
           
        }
    },
    computed: {
        navItems: get("header/navItems"),
        token: get("user/token"),
        filteredNavItems(){
            //if authenticated show all, else hide restricted
            return this.token? this.navItems : this.navItems.filter(item=> !item.meta?.requiresAuth);
        }
    },
    mounted(){
    
    },
    methods: {
        closeModal(boolean) {
            this.createAccountModal = boolean;
            console.log(window.console);
            
        }
    }
}
</script>

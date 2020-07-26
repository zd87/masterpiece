<template>
	<div id="header">
        <v-app-bar app color="white" height="60px">
            
            <router-link to="/">
                <img :src="imageSrc" :style="{height:'50px'}" />
            </router-link>
            
            <v-list class="d-flex pa-0">
                <!-- <v-list-item><v-img :src="imageSrc" style='height: 100%; width: 100%; object-fit: contain' /></v-list-item> -->
                <v-list-item v-for="(item, index) in filteredNavItems" :key="index">
                    <v-btn
                        class="navItem"
                        :to="item.to"
                        :ripple="false"
                        depressed text tile
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
           imageSrc:require("@/assets/logo4.jpg")
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
<style lang="scss" scoped>

</style>
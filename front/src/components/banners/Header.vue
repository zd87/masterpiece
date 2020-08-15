<template>
	<div id="header">
        <v-app-bar app color="white" height="60px">
            
            <router-link to="/">
                <img :src="imageSrc" :style="{height:'50px'}" />
            </router-link>
            
            <v-list class="d-flex pa-0">
                <v-list-item v-for="(item, index) in navItems" :key="index">
                    <v-btn
                        class="navItem"
                        :to="item.to"
                        :ripple="false"
                        :disabled="!authorized(item)"
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
           imageSrc:require("@/assets/logo11.jpg")
        }
    },
    computed: {
        navItems: get("header/navItems"),
        token: get("user/token"),
        user: get("user/user")
        // TO HIDE UNAUTHORIZED NAV ITEMS, DELETE IF NOT REQUIRED
        // filteredNavItems(){
        //     //if authenticated show all, else hide restricted
        //     return this.token? this.navItems : this.navItems.filter(item=> !item.meta?.requiresAuth);
        // },
        
    },
    mounted(){
    
    },
    methods: {
        authorized(item){
            //if restrictions in meta, then compare required roles and roles the user has
            return item.meta?.roles ? 
            this.token && this.user.roles.some(role=> item.meta?.roles?.indexOf(role) !== -1) 
            //if no restrictions, then authorized
            : true;
        }
    }
}
</script>
<style lang="scss" scoped>

</style>
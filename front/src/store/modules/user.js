import authAxios from '@/axios';
import { make } from "vuex-pathify";
import { $store } from '@/main'
const state = {
    user:{}
};

const mutations = {
    ...make.mutations(["user"])
};

const actions = {

    async fetchUser(){
        try {
            let { data } = await authAxios.get("/me")
            let user = data;
            user.roles = user.roles.map(role => role.code);
            user.isAdmin = user.roles.includes("ROLE_ADMIN");
            $store.set("user/user", user);
        }catch(error){
            console.log("ERROR", error);
        }
    }
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
}

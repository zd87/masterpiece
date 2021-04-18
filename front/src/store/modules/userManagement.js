import { make } from "vuex-pathify";
import authAxios from "@/axios";
import { $store } from '@/main'
const state = {
    users:[]
};

// const getters = {
//     // comupted of store
// };

const mutations = {
    ...make.mutations(state)
};

const actions = {
    async fetchUsers(){
        try {
            const { data } = await authAxios.get("/admin/users");
            $store.set("userManagement/users", data);
        }catch(error){
            console.log("ERROR", error);
        }
    },
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
};
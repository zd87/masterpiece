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
    ...make.mutations(["users"])
};

const actions = {
    fetchUsers(){
        authAxios.get(`/admin/users`)
            .then(response => { 
                $store.set("userManagement/users", response.data)
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    },
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
}
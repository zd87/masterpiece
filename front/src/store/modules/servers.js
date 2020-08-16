import { make } from "vuex-pathify";
import authAxios from '@/axios';
import { $store } from '@/main'
const state = {
    servers:[],
    attributeOptions:{}
};

// const getters = {
//     // comupted of store
// };

const mutations = {
    ...make.mutations(["servers", "attributeOptions"])
};

const actions = {
    fetchServers(){
        authAxios.get(`/servers`)
            .then(response => { 
                $store.set("servers/servers", response.data)
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    },
    fetchAtrributeNames(){
        authAxios.get(`/servers/attributes`)
            .then(response => { 
                $store.set("servers/attributeOptions", response.data)
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    }
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
}
import { make } from "vuex-pathify";
import authAxios from '@/axios';
import { $store } from '@/main'
const state = {
    servers:[],
    attributeOptions:{}
};

const mutations = {
    ...make.mutations(state)
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
    fetchAtrributeOptions(){
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
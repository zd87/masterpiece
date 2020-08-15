import { make } from "vuex-pathify";
import axios from "axios";
import { $store } from '@/main'
const state = {
    servers:[],
    attributeOptions:[]
};

// const getters = {
//     // comupted of store
// };

const mutations = {
    ...make.mutations(["servers", "attributeOptions"])
};

const actions = {
    fetchServers(){
        axios.get(`http://localhost:8085/api/servers`)
            .then(response => { 
                $store.set("servers/servers", response.data)
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    },
    fetchAtrributeNames(){
        axios.get(`http://localhost:8085/api/servers/attributes`)
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
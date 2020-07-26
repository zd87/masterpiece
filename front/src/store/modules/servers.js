import { make } from "vuex-pathify";
import axios from "axios";
import { $store } from '@/main'
const state = {
    servers:[],
    attributeNames:[]
};

// const getters = {
//     // comupted of store
// };

const mutations = {
    ...make.mutations(["servers", "attributeNames"])
};

const actions = {
    fetchServers(){
        console.log("get servers dispatch");
        
        axios.get(`http://localhost:8085/api/servers`)
            .then(response => { 
                console.log("response", response);
                $store.set("servers/servers", response.data)
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    },
    fetchAtrributeNames(){
        axios.get(`http://localhost:8085/api/serverAttributeNames`)
            .then(response => { 
                $store.set("servers/attributeNames", response.data)
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
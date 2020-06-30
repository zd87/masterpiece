import { make } from "vuex-pathify";
import axios from "axios";
import store from '../index'
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
        axios.get(`http://localhost:8085/servers`)
            .then(response => { 
                console.log("response", response);
                store.set("servers/servers", response.data)
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    },
    fetchAtrributeNames(){
        axios.get(`http://localhost:8085/serverAttributeNames`)
            .then(response => { 
                console.log("response", response);
                store.set("servers/attributeNames", response.data)
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
import { make } from "vuex-pathify";
import authAxios from '@/axios';
import { $store } from '@/main'
const state = {
    servers:[],
    attributeOptions:{},
    attributeValues:[]
};

// const getters = {
//     // comupted of store
// };

const mutations = {
    ...make.mutations(["servers", "attributeOptions", "attributeValues"])
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
    },
    fetchAttrValues({}, attrName){
        console.log("name: ", attrName);
        
        authAxios.get(`/servers/attributes/attrValues/${attrName}`)
            .then(response => { 
                $store.set("servers/attributeValues", response.data)
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
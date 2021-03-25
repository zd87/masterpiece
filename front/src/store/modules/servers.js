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
    },
    uploadExcelFile({dispatch}, formData){
        let options = {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }
        authAxios.post(`/servers/excel`,formData, options)
            .then(response => { 
                dispatch("fetchServers");
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
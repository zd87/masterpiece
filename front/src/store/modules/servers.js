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
    async fetchServers(){
        try {
            const { data } = await authAxios.get("/servers");
            $store.set("servers/servers", data);

        }catch(error){
            console.log("ERROR", error);
        }
    },
    async fetchAtrributeOptions(){
        try {
            const { data } = await authAxios.get("/servers/attributes");
            $store.set("servers/attributeOptions", data);

        }catch(error){
            console.log("ERROR", error);
        }
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
};
import { make } from "vuex-pathify";
import authAxios from '@/axios';
import { $store } from '@/main'
const state = {
    auditEntries:[]
};

const mutations = {
    ...make.mutations(state)
};

const actions = {
    fetchAuditEntries(){
        authAxios.get(`/admin/audit`)
            .then(response => { 
                let data = response.data;
                data.forEach(entry => {
                    try {
                        entry.serverBefore = entry.serverBefore?  JSON.parse(entry.serverBefore) : null;           
                        entry.serverAfter = entry.serverAfter? JSON.parse(entry.serverAfter) : null;              
                      } catch (e) {
                        console.error("Parsing error:", e);
                      }
                    return entry;
                })
                $store.set("audit/auditEntries", data)
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
import authAxios from '@/axios';
import { make } from "vuex-pathify";
import { $store } from '@/main'
const state = {
    user:{}
};

const mutations = {
    ...make.mutations(["user"])
};

const actions = {

    fetchUser(){
        authAxios.get(`/me`)
            .then(response => { 
                let user = response.data;
                user.roles = user.roles.map(role => role.code);
                user.isAdmin = user.roles.includes("ROLE_ADMIN");
                $store.set("user/user", user);
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    },
    //authentication training, delete after
    // fetchUser2(){
    //     let options= {
    //         headers: {
    //             "Authorization" : "Bearer "+localStorage.token
    //         }
    //     };
    //     axios.get(`http://localhost:9090/api/userInfo`, options)
    //         .then(response => { 
    //             console.log("response", response);
    //             $store.set("user/user", response.data)
    //         })
    //         .catch(error => {
    //             console.log("ERROR", error);
    //         })
    // }
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
}

import axios from "axios";
import { $store } from "@/main";

const authAxios= axios.create({
    baseURL:"http://localhost:8085/api"
});

authAxios.interceptors.request.use(
    config => {
        config.headers["Authorization"] = `Bearer ${localStorage.token}`;
        return config;
    },
    error => Promise.reject(error)
);
authAxios.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        $store.dispatch("alert/add", {response:error});
        Promise.reject(error)
    }
);

export default authAxios;
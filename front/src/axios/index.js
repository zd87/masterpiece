import axios from "axios";

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

export default authAxios;
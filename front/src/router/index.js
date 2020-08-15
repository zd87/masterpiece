import pages from "../pages";

const routes = [
    { path: "*", redirect: "/"},
    {
        path: '/',
        name: 'assets',
        component: pages.Assets,
        // meta: {authorize: ["ROLE_USER","ROLE_ADMIN"] }
        meta: { requiersAuth:true, roles: ["ROLE_USER","ROLE_ADMIN"] }
    },
    {
        path: '/about',
        name: 'about',
        component: pages.About
    },
    {
        path: '/login',
        name: 'login',
        component: pages.Login
    },
    
    
]    
export default routes;
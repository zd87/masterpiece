import pages from "../pages";

const routes = [
    { path: "*", redirect: "/"},
    {
        path: '/',
        name: 'assets',
        component: pages.Assets,
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
    {
        path: '/users',
        name: 'userMangament',
        component: pages.UserManagement,
        meta: { requiersAuth:true, roles: ["ROLE_ADMIN"] }
    },{
        path: '/audit',
        name: 'auditTrail',
        component: pages.AuditTrail,
        meta: { requiersAuth:true, roles: ["ROLE_ADMIN"] }
    },
    
]    
export default routes;
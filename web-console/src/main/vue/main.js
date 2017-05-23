import Vue from 'vue';
import VueRouter from 'vue-router'
import Main from './Main.vue';
import DatabaseListView from './components/DatabaseListView.vue';
Vue.use(VueRouter);

import Vuex from 'vuex';
Vue.use(Vuex);

const router = new VueRouter({
    routes: [
        {
            path: '/', component: Main,
            children :[
                     {name :"databaselist", path: "/database/list" , component: DatabaseListView   }
             ]
        }
    ]
})

const app = new Vue({
    router
}).$mount('#app')
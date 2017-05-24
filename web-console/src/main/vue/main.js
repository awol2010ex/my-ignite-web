import Vue from 'vue';
import VueRouter from 'vue-router'
import Main from './Main.vue';
import DatabaseListView from './components/DatabaseListView.vue';
import SqlDatamodelListView from './components/SqlDatamodelListView.vue';
Vue.use(VueRouter);

import Vuex from 'vuex';
Vue.use(Vuex);

const router = new VueRouter({
    routes: [
        {
            path: '/', component: Main,
            children :[
                     {name :"databaselist", path: "/database/list" , component: DatabaseListView   },
                     {name :"sqldatamodellist", path: "/sqldatamodel/list" , component: SqlDatamodelListView   }
             ]
        }
    ]
})

const app = new Vue({
    router
}).$mount('#app')
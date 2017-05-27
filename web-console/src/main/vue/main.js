import Vue from 'vue';
import VueRouter from 'vue-router'
import Main from './Main.vue';
import DatabaseListView from './components/DatabaseListView.vue';
import SqlDatamodelListView from './components/SqlDatamodelListView.vue';
import SqlDatamodelEditView from './components/SqlDatamodelEditView.vue';
Vue.use(VueRouter);

import store from './store'
import Vuex from 'vuex';
Vue.use(Vuex);

const router = new VueRouter({
    routes: [
        {
            path: '/', component: Main,
            children :[
                     {name :"databaselist", path: "/database/list" , component: DatabaseListView   },
                     {name :"sqldatamodellist", path: "/sqldatamodel/list" , component: SqlDatamodelListView   },
                     {name :"sqldatamodeledit", path: "/sqldatamodel/edit/:modelId" , component: SqlDatamodelEditView   }
             ]
        }
    ]
})

const app = new Vue({
   store :store,
   router: router
}).$mount('#app')
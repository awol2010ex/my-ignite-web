import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const state = {
	pageName :[{name:"首页",path:"/"}]
}
const getters ={

}
const mutations = {
	 //设置当前页面名字
    setPageName(state, obj) {
        state.pageName = obj.pageName
    }
}
const actions ={
	change_page_name : ({commit}, param) => commit('setPageName',{pageName: param})
}
export default new Vuex.Store({
    state,
    mutations,
    actions,
    getters
})
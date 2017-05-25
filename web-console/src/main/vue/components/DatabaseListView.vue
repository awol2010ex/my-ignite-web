<template>
    <div style="width: 100%;padding:10px;">
         <el-table
                       :data="dataList"
                       border
                       style="width: 100%"

                       >
                       <el-table-column
                         prop="name"
                         label="数据源名称"
                         width="180">
                       </el-table-column>
                       <el-table-column
                         prop="jdbcurl"
                         label="地址"
                         width="360">
                       </el-table-column>
                       <el-table-column
                         prop="dbuser"
                         label="用户名">
                       </el-table-column>
                     </el-table>
    </div>
</template>

<script>
    import Vue from 'vue';
    import 'element-ui/lib/theme-default/table.css';
    import 'element-ui/lib/theme-default/pagination.css'
    import {Table,TableColumn,Pagination } from 'element-ui'
    Vue.component(Table.name, Table)
    Vue.component(TableColumn.name, TableColumn)
    Vue.component(Pagination.name, Pagination)
    import {serviceApi} from '../services/service.js';
    export default {
        data () {
            return {
                dataList:[]
            }
        },
        mounted() {
             this.$store.dispatch('change_page_name',[{name:"首页",path:"/"},{name:"数据源列表",path:"/database/list"}]);
             const me=this
             serviceApi.invokeApi("DatabaseService","getDatabaseList",null).then(
                    ret =>{
                         me.dataList =ret
                    }
             );

        }
    }
</script>

<style>
</style>
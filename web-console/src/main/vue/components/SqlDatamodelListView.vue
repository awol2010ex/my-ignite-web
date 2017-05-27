<template>
    <div style="width: 100%;padding:10px;">
      <el-row>
         <el-col :span="24" style="padding:10px">
              <el-button type="primary" >新增</el-button>
         </el-col>
         <el-col :span="24">
              <el-table
                       :data="dataList"
                       border
                       style="width: 100%"

                       >
                         <el-table-column
                                                 prop="id"
                                                 label="模型ID"
                                                 width="180">
                          </el-table-column>
                       <el-table-column
                         prop="name"
                         label="模型名称"
                         width="180">
                       </el-table-column>

                       <el-table-column label="操作">
                             <template scope="scope">
                               <el-button
                                 size="small"
                                 @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                               <el-button
                                 size="small"
                                 type="danger"
                                 @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                             </template>
                           </el-table-column>
               </el-table>
         </el-col>
        </el-row>
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

    import {Col,Row ,Button} from 'element-ui'
    Vue.component(Col.name, Col)
    Vue.component(Row.name, Row)
    Vue.component(Button.name, Button)
    import 'element-ui/lib/theme-default/icon.css';
    import 'element-ui/lib/theme-default/row.css';
    import 'element-ui/lib/theme-default/col.css';
    import 'element-ui/lib/theme-default/button.css';




    import {serviceApi} from '../services/service.js';
    export default {
        data () {
            return {
                dataList:[]
            }
        },
        mounted() {
             this.$store.dispatch('change_page_name',[{name:"首页",path:"/"},{name:"SQL模型列表",path:"/sqldatamodel/list"}]);

             const me=this
             //模型列表
             serviceApi.invokeApi("SqlDatamodelService","getSqlDatamodelList",null).then(
                    ret =>{
                         me.dataList =ret
                    }
             );

        },
        methods:{
             //newSqlDatamodel(){
                 // this.$router.push("/sqldatamodel/new")
             //}
             handleEdit(index ,row){
                 this.$router.push("/sqldatamodel/edit/"+row.id)
             }
        }
    }
</script>

<style>
</style>
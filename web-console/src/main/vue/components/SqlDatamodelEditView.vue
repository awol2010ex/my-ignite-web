<template>
    <div style="width: 100%;padding:10px;">
       <el-popover
                ref="selectTablePopover"
                placement="right"
                width="700"
                 trigger="click">
              <el-row>
                <el-col :span="24" style="padding:10px">
                    <el-form   label-width="130px">
                      <el-col :span="8">
                       <el-form-item label="数据源"  >
                        <el-select  placeholder="请选择"  v-model="selectDatabase" @change="onSelectDatabase">
                                         <el-option
                                            v-for="d in selectDatabases"
                                            :label="d.name"
                                            :value="d.id">
                                         </el-option>
                         </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                         <el-form-item label="表名"  >
                               <el-input placeholder="请输入搜索表名" icon="search"

                                         v-model="searchTableName" @change="onSelectDatabase"  >

                               </el-input>
                          </el-form-item>
                      </el-col>
                      <el-col :span="4" style="padding-left:10px">

                                  <el-button type="primary"  >确认</el-button>
                         </el-col>
                    </el-form>
                </el-col>
                <el-col :span="24" style="padding:10px">
                  <el-table :data="selectTables"  @selection-change="handleSelectionChange">
                      <el-table-column
                        type="selection"
                        width="55">
                      </el-table-column>
                  <el-table-column width="150" property="schem" label="模式"></el-table-column>
                  <el-table-column width="350" property="name" label="名称"></el-table-column>
                  <el-table-column width="100" property="type" label="类型"></el-table-column>
                  </el-table>
                 </el-col>
                 <el-col :span="24" style="padding:10px">
                  <el-pagination
                             @size-change="handleSelectTableSizeChange"
                             @current-change="handleSelectTableCurrentChange"
                             :current-page="selectTableCurrentPage"

                             :page-sizes="[7, 50, 100, 200]"
                             :page-size="selectTablePageSize"
                             layout="total, sizes, prev, pager, next, jumper"
                             :total="selectTableTotalCount">
                           </el-pagination>
                  </el-col>
               </el-row>
       </el-popover>
       <el-row>
          <el-col :span="24" style="padding:10px">
                       <el-button type="primary"   v-popover:selectTablePopover>导入</el-button>
           </el-col>
       </el-row>



    </div>
</template>

<script>
    import Vue from 'vue';
    import {serviceApi} from '../services/service.js';

    import {Col,Row ,Button,Input} from 'element-ui'
    Vue.component(Col.name, Col)
    Vue.component(Row.name, Row)
    Vue.component(Button.name, Button)
     Vue.component(Input.name, Input)
    import 'element-ui/lib/theme-default/icon.css';
    import 'element-ui/lib/theme-default/row.css';
    import 'element-ui/lib/theme-default/col.css';
    import 'element-ui/lib/theme-default/button.css';
    import 'element-ui/lib/theme-default/input.css';
    import {Popover} from 'element-ui'
    Vue.component(Popover.name, Popover)
    import 'element-ui/lib/theme-default/popover.css';

    import 'element-ui/lib/theme-default/form.css';
    import 'element-ui/lib/theme-default/option.css';
    import 'element-ui/lib/theme-default/select.css';
    import {Form,FormItem,Select ,Option } from 'element-ui'
    Vue.component(Form.name, Form)
    Vue.component(FormItem.name, FormItem)
    Vue.component(Select.name, Select)
    Vue.component(Option.name, Option)

    import { Loading } from 'element-ui';
    import 'element-ui/lib/theme-default/loading.css';


    export default {
        data () {
            return {
                 id :null,
                 selectTables:[],
                 selectDatabase :null,
                 selectDatabases :[],
                 selectTableCurrentPage:1,
                 selectTablePageSize:7,
                 selectTableTotalCount:0,
                 searchTableName:"",
                 checkedTables:[]
            }
        },
        mounted() {
            const me=this
            serviceApi.invokeApi("DatabaseService","getDatabaseList",null).then(
                                ret =>{
                                     me.selectDatabases =ret
                                }
             );
        },
         methods:{
               importSqlDatamodel(){
               },
               selectTableClick(){
               },
               onSelectDatabase(){
                  if(this.selectDatabase){
                              this.refreshSelectTables(this.selectTableCurrentPage,this.selectTablePageSize)
                  }
               },
               refreshSelectTables(page,pageSize){
                   const me=this
                   let loadingInstance = Loading.service({ fullscreen: true });
                   serviceApi.invokeApi("DatabaseService","getTableList",{id:this.selectDatabase,page:page,pageSize:pageSize,tableName:this.searchTableName}).then(
                             ret =>{
                                 me.selectTables=ret.objectList
                                 me.selectTableTotalCount =ret.totalcount
                                 loadingInstance.close();
                             }
                    );
               },
               handleSelectTableSizeChange(size){
                   this.selectTablePageSize=size
                   this.onSelectDatabase()
               },
               handleSelectTableCurrentChange(page){
                   this.selectTableCurrentPage=page
                   this.onSelectDatabase()
               },
               handleSelectionChange(val){
                   this.checkedTables=val
               }

         }
    }
</script>

<style>
</style>
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

                                  <el-button type="primary"  @click="importSqlDatamodel" >确认</el-button>
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

           <el-col :span="8" style="padding:5px"   v-for="m in itemList">
                <el-card class="box-card">
                  <div slot="header" class="clearfix">
                    <span style="line-height: 16px;font-size:14px">{{m.tablename}}</span>

                    <el-button type="danger"  @click="deleteSqlDatamodelItem(m.id)" >删除</el-button>
                  </div>
                   <div class="text item">
                       <el-table
                                             :data="m.columnList"
                                             border
                                             style="width: 100%"
                                              :row-style="{'font-size':'12px'}"
                                             >
                                             <el-table-column
                                               prop="columnname"
                                               label="字段名称">
                                             </el-table-column>
                                             <el-table-column
                                                  prop="columntype"
                                                   label="类型">
                                              </el-table-column>
                                     </el-table>

                    </div>
                </el-card>
           </el-col>
       </el-row>



    </div>
</template>

<script>
    import Vue from 'vue';
    import {serviceApi} from '../services/service.js';

    import {Col,Row ,Button,Input,Card} from 'element-ui'
    Vue.component(Col.name, Col)
    Vue.component(Row.name, Row)
    Vue.component(Button.name, Button)
     Vue.component(Input.name, Input)
     Vue.component(Card.name, Card)
    import 'element-ui/lib/theme-default/icon.css';
    import 'element-ui/lib/theme-default/row.css';
    import 'element-ui/lib/theme-default/col.css';
    import 'element-ui/lib/theme-default/button.css';
    import 'element-ui/lib/theme-default/input.css';
    import 'element-ui/lib/theme-default/card.css';


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

    import 'element-ui/lib/theme-default/table.css';
    import {Table,TableColumn } from 'element-ui'
    Vue.component(Table.name, Table)
    Vue.component(TableColumn.name, TableColumn)


    export default {
        data () {
            return {
                 id :null,//模型ID
                 selectTables:[],//可选表
                 selectDatabase :null,//选中数据源
                 selectDatabases :[],//可选数据源
                 selectTableCurrentPage:1,//可选表当前页
                 selectTablePageSize:7,//可选表每页行数
                 selectTableTotalCount:0,//可选表总页数
                 searchTableName:"",//查询表表名
                 checkedTables:[],//选中表

                 itemList:[] //模型列表

            }
        },
        watch: {
                    '$route' (to, from) {
                        // 对路由变化作出响应...
                        this.id =to.params.modelId;
                        //刷新模型
                        this.refreshItems(this.id)
                    }
        },
        mounted() {


            this.id =this.$route.params.modelId;
//刷新模型
            this.refreshItems(this.id)

//数据源下拉框列表
            const me=this
            serviceApi.invokeApi("DatabaseService","getDatabaseList",null).then(
                                ret =>{
                                     me.selectDatabases =ret
                                }
             );
        },
         methods:{
            //导入数据模型
               importSqlDatamodel(){
                  const me=this
                  let loadingInstance = Loading.service({ fullscreen: true });
                  serviceApi.invokeApi("SqlDatamodelService","importSqlDatamodel",{modelId:this.id ,databaseId:this.selectDatabase,tableList:this.checkedTables}).then(
                         ret =>{
                              loadingInstance.close();
                               console.log(me.$refs.selectTablePopover)
                               me.$refs.selectTablePopover.doClose()

                               me.refreshItems(me.id)
                         }
                  );

               },
               onSelectDatabase(){//选中数据源
                  if(this.selectDatabase){
                              this.refreshSelectTables(this.selectTableCurrentPage,this.selectTablePageSize)
                  }
               },
               refreshSelectTables(page,pageSize){//翻页
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
               //改变每页行数
               handleSelectTableSizeChange(size){
                   this.selectTablePageSize=size
                   this.onSelectDatabase()
               },
                //改变每页行数
               handleSelectTableCurrentChange(page){
                   this.selectTableCurrentPage=page
                   this.onSelectDatabase()
               },
                 //选中表列表
               handleSelectionChange(val){
                   this.checkedTables=val
               },

              //刷新模型
               refreshItems(modelId){
                   const me=this
                   let loadingInstance = Loading.service({ fullscreen: true });
                   serviceApi.invokeApi("SqlDatamodelService","getSqlDatamodel",{modelId :modelId}).then(
                             ret =>{
                                 me.itemList =ret.itemList
                                 me.$store.dispatch('change_page_name',[{name:"首页",path:"/"},{name:"SQL模型",path:"/sqldatamodel/list"},{name:ret.name}]);

                                 loadingInstance.close();
                             }
                    );

               },
               //删除元素
               deleteSqlDatamodelItem(itemId){
                   const me=this
                   let loadingInstance = Loading.service({ fullscreen: true });
                   serviceApi.invokeApi("SqlDatamodelService","deleteSqlDatamodelItem",{itemId:itemId}).then(
                             ret =>{
                                   me.refreshItems(me.id)
                                   loadingInstance.close();
                             }
                    );
               }

         }
    }
</script>

<style>
</style>
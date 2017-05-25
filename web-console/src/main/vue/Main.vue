<template>
    <div class="wholescreen" >
            <el-row :gutter="20" class="topBar" >
                     <el-breadcrumb separator="/">
                          <el-breadcrumb-item   v-for="p in $store.state.pageName"   >
                             <el-breadcrumb-item  :to="{path:p.path}" :replace="true">{{p.name}}</el-breadcrumb-item>
                          </el-breadcrumb-item >
                     </el-breadcrumb>
            </el-row>



            <el-row :gutter="20" >

                <el-col :span="4" style="padding:10px">
                   <el-card class="box-card menu-container" >
                      <el-row>
                           <el-col :span="24" class="menu-button-col">
                              <el-button type="primary" size="large" class="menu-button" @click="gotoDatabaseListView">数据源</el-button>
                           </el-col>
                            <el-col :span="24" class="menu-button-col">
                                <el-button type="primary" size="large" class="menu-button" @click="gotoSqlDatamodelListView">SQL模型</el-button>
                            </el-col>
                           <el-col :span="24" class="menu-button-col">
                               <el-button type="primary" size="large" class="menu-button">节点</el-button>
                            </el-col>
                      </el-row>
                    </el-card>
                </el-col>
                <el-col :span="20">
                      <router-view></router-view>
                 </el-col>
            </el-row>
   </div>
</template>

<script>
    import Vue from 'vue';

    import {Col,Row ,Card,Button} from 'element-ui'
    Vue.component(Col.name, Col)
    Vue.component(Row.name, Row)
    Vue.component(Button.name, Button)
    Vue.component(Card.name, Card)
    import 'element-ui/lib/theme-default/icon.css';
    import 'element-ui/lib/theme-default/row.css';
    import 'element-ui/lib/theme-default/col.css';
    import 'element-ui/lib/theme-default/button.css';
    import 'element-ui/lib/theme-default/card.css';


    import {Breadcrumb,BreadcrumbItem} from 'element-ui'
    Vue.component(Breadcrumb.name, Breadcrumb)
	Vue.component(BreadcrumbItem.name, BreadcrumbItem)
    import 'element-ui/lib/theme-default/breadcrumb-item.css';
    import 'element-ui/lib/theme-default/breadcrumb.css';

    export default {
        data () {
            return {
            }
        },
		components:{},
        mounted() {
            this.$store.dispatch('change_page_name',[{name:"首页",path:"/"}]);

        }
		,methods:{


            gotoDatabaseListView(){
                this.$router.push("/database/list")
            },
            gotoSqlDatamodelListView(){
                this.$router.push("/sqldatamodel/list")
            }

		}
    }
</script>

<style>
.wholescreen{
  width:100%;
}
.topBar{
  height:30px;
  padding:20px;
  font-size:14px;
}
.menu-button-col{
   padding:5px;
   text-align:center;
}
.menu-button {
  width:100px
}
.menu-container{
   height:500px;
   padding:5px;
}
</style>
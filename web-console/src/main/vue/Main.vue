<template>
    <div class="wholescreen" >
            <el-row :gutter="20" class="topBar">

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
    export default {
        data () {
            return {
            }
        },
		components:{},
        mounted() {
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
  background-color:#CCCCCC
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
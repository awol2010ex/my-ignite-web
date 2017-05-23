<template>
    <div class="wholescreen" >
            <el-row :gutter="20" class="topBar">

            </el-row>



            <el-row :gutter="20" class="topBar">

                <el-col :span="8">

                <el-col>
                <el-col :span="16">
                      <router-view></router-view>
                 <el-col>
            </el-row>
   </div>
</template>

<script>
    import Vue from 'vue';

    import {Col,Row } from 'element-ui'
    	Vue.component(Col.name, Col)
    	Vue.component(Row.name, Row)

    	import 'element-ui/lib/theme-default/icon.css';
    	import 'element-ui/lib/theme-default/row.css';
    	import 'element-ui/lib/theme-default/col.css';


    export default {
        data () {
            return {
            }
        },
		components:{},
        mounted() {
        }
		,methods:{




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

</style>
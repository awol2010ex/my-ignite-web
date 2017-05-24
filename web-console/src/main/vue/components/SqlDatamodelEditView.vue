<template>
    <div style="width: 100%;padding:10px;">
          <el-col :span="24" style="padding:10px">
                       <el-button type="primary" @click="importSqlDatamodelItem">导入</el-button>
           </el-col>
    </div>
</template>

<script>
    import Vue from 'vue';
    import {serviceApi} from '../services/service.js';

    import {Col,Row ,Button} from 'element-ui'
        Vue.component(Col.name, Col)
        Vue.component(Row.name, Row)
        Vue.component(Button.name, Button)
        import 'element-ui/lib/theme-default/icon.css';
        import 'element-ui/lib/theme-default/row.css';
        import 'element-ui/lib/theme-default/col.css';
        import 'element-ui/lib/theme-default/button.css';




    export default {
        data () {
            return {
            }
        },
        mounted() {


        },
         methods:{
               importSqlDatamodel(){
               }
         }
    }
</script>

<style>
</style>
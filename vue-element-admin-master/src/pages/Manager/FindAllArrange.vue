<template>
    <div>
          <div class="btns">
              <el-button type="primary" size="small" @click="back">返回</el-button>
              <el-button type="primary" size="small" @click="downLoad">下载</el-button>
          </div>
        
    <el-table :data="Arrange" style="width: 100%" >
        <el-table-column prop="date" label="练车时间" width="180"></el-table-column>
        <el-table-column prop="user.name" label="学员姓名" width="180"></el-table-column>
        <el-table-column prop="car.carNumber" label="教练车车牌" width="180"></el-table-column>
        <el-table-column prop="user.phonenum" label="电话" width="180"></el-table-column>
    </el-table>

    </div>
</template>

<script>
import request from "@/utils/request"
import qs from 'querystring'
export default {
     data(){
        return{
            Arrange:[],
        }
        },
        created(){

        },
        methods:{
            reLoadData(){
                request.get("/manager/findAllArrange")
                .then(result=>{
                     this.Arrange = result.data;
                })
            },
             back(){
            this.$router.go(-1);
            },
            downLoad(){
                request.get("/manager/download")
                 .then(response=>{
                        this.$message({
                            message:response.message,
                        })
            })
            }

        }
}
</script>
<style scoped>

</style>
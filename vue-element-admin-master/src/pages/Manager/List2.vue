<template>
    <div class="coach_list">
          <!--按钮 -->
        <div class="btns">
             <el-button type="primary" size="small" @click="back">返回</el-button>
        </div>
         <!--表格 -->
        <el-table :data="coach" style="width: 100%">
      <el-table-column prop="id" label="教练编号" width="180"></el-table-column>
      <el-table-column prop="name" label="教练姓名" width="180"></el-table-column>
      <el-table-column prop="age" label="年龄" width="180"></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="charges" label="收费"></el-table-column>
      <el-table-column prop="carNumber" label="车牌号码"></el-table-column>
       <el-table-column prop="carType" label="车辆类型"></el-table-column>
       <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column fixed="right" label="操作" align="center" width="200">
          <template slot-scope="scope">
              <el-button @click="toPass(scope.row.id)" type="text" size="small">批准</el-button>
          </template>
      </el-table-column>
    </el-table>
    </div>
</template>
<script>
import request from "@/utils/request"
export default {
    data(){
        return{
            coach:[]
        }
    },
    created(){
       this.reLoadData();
    },
    methods:{
        reLoadData(){
             request.get("/manager/findAll")
            .then(result=>{
                this.coach= result.data;
                })
        },
        toPass(id){
            console.log(id);
            let url = "/manager/check"
           request.get(url,{params:{coachId:id}})
           this.reLoadData();
        },
        toAddCoach(){
            //跳转到教练选择页面
            this.$router.push({path:"/manager/addCoach"})
        },
        back(){
            this.$router.go(-1);
        }
        
    }
}
</script>
<style scoped>

</style>
<template>
    <div class="Comments">
        <!--按钮 -->
        <div class="btns">
             <el-button type="primary" size="small" @click="back">返回</el-button>
        </div>
        <!--所有教练表格 -->
   <el-table :data="coachAccepts" style="width: 100%">
      <el-table-column prop="coachAccept.id" label="教练工号" width="180"></el-table-column>
      <el-table-column prop="coachAccept.name" label="教练姓名" width="180"></el-table-column>
      <el-table-column prop="coachAccept.age" label="年龄" width="180"></el-table-column>
      <el-table-column prop="coachAccept.gender" label="性别"></el-table-column>
      <el-table-column prop="coachAccept.charges" label="收费"></el-table-column>
      <el-table-column prop="carNumber" label="教练车车牌"></el-table-column>
      <el-table-column prop="type" label="车辆类型"></el-table-column>
      <el-table-column fixed="right" label="操作" align="center" width="200">
          <template slot-scope="scope">
              <el-button @click="toReview(scope.row.id)" type="text" size="small">查看评价</el-button>
              <el-button @click="toSelectCoach(scope.row.id)" type="text" size="small">选择教练</el-button>
          </template>
      </el-table-column>
    </el-table>

    <el-dialog title="教练评价信息" :visible.sync="visible">
        <el-table :data="comment" style="width: 100%">
        <el-table-column prop="commentTime" label="评论时间" width="180"></el-table-column>
        <el-table-column prop="content" label="评论内容" width="180"></el-table-column>
    </el-table>
    </el-dialog>

    </div>
</template>
<script>
import request from "@/utils/request"
import qs from 'querystring'
export default {
    data(){
        return{
            coachAccepts:[],
            comment:[],
            visible:false,
        }
    },
    created(){
       this.reLoadData();
    },
    methods:{
        reLoadData(){
            request.get("/users/findAll")
        .then(result=>{
            this.coachAccepts = result.data;
        })
        console.log(this.coachAccepts)
        },
        back(){
            this.$router.go(-1);
        },
        toReview(val){
            this.visible=true;
            let url="/users/findComment"
            request.get(url+"?coachId="+val)
            .then(result=>{
                    this.comment = result.data;
            }) 
            this.$message({
            message: response.message.content,
            center: true
            });
        },
        toSelectCoach(id){
            let url="/users/selectCoach"
            request.get(url+"?coachId="+id)
            .then(response=>{
                this.$message({message:response.message,type:'success'});
            })
        }
        
    }
}
</script>
<style scoped>

</style>
<template>
   <div class="Comments">
        <!--按钮 -->
        <div class="btns">
             <el-button type="primary" size="small" @click="saveOrUpdate">修改信息</el-button>
             <el-button type="primary" size="small" @click="toFindUsers">查看学员</el-button>
             <el-button type="primary" size="small" @click="toFindArranges">查看教学安排</el-button>
              <el-button type="primary" size="small" @click="toDownLoad">下载教学计划</el-button>
        </div>

         <el-table :data="coachAccept" style="width: 100%" >
            <el-table-column prop="coachAccept.name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="coachAccept.age" label="年龄" width="180"></el-table-column>
            <el-table-column prop="coachAccept.gender" label="性别" width="180"></el-table-column>
            <el-table-column prop="coachAccept.charges" label="收费标准" width="180"></el-table-column>
            <el-table-column prop="carNumber" label="车牌号码" width="180"></el-table-column>
            <el-table-column prop="type" label="车辆类型" width="180"></el-table-column>
      </el-table>


      <el-dialog title="修改教练信息" :visible.sync="visible">
      <el-form :model="form">
        <el-form-item label="年龄" label-width="80px">
          <el-input v-model="form.age" autocomplete="off">form.coachAccept</el-input>
        </el-form-item>
        <el-form-item label="收费标准" label-width="80px">
          <el-input v-model="form.charges" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="车辆类型" label-width="80px">
          <el-input v-model="form.carType" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="车牌号" label-width="80px">
          <el-input v-model="form.carNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="80px">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>  
       </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="visible = false" size="small">取 消</el-button>
        <el-button type="primary" size="small" @click="saveOrUpdate2">确 定</el-button>
      </div>
    </el-dialog> 

    <el-dialog title="所有学员信息" :visible.sync="visible2">
            <el-table :data="User" style="width: 100%" >
            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="age" label="年龄" width="180"></el-table-column>
            <el-table-column prop="gender" label="性别" width="180"></el-table-column>
            <el-table-column prop="phonenum" label="电话" width="180"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="180"></el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog title="教学安排" :visible.sync="visible3">
            <el-table :data="Arrange" style="width: 100%" >
            <el-table-column prop="date" label="练车时间" width="180"></el-table-column>
            <el-table-column prop="user.name" label="学员姓名" width="180"></el-table-column>
            <el-table-column prop="car.carNumber" label="教练车车牌" width="180"></el-table-column>
            <el-table-column prop="user.phonenum" label="电话" width="180"></el-table-column>
            <el-table-column fixed="right" label="操作" align="center" width="200">
          <template slot-scope="scope">
              <el-button @click="toPass(scope.row.date,scope.row.user.phonenum)" type="text" size="small">发送短信提醒练车</el-button>
          </template>
      </el-table-column>
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
            coachAccept:[],
            visible:false,
            form:[],
            visible2:false,
            User:[],
            visible3:false,
            Arrange:[]
        }
        },
        created(){
            this.reLoadData();
        },
        methods:{
            reLoadData(){
                request.get("/coach/findCoach")
                .then(result=>{
                     this.coachAccept = result.data;
                     this.form = this.coachAccept;
                })
            },
            saveOrUpdate(){
                this.visible=true;
                 request.get("/coach/findCoach")
                    .then(result=>{
                        this.form = result.data;
                    });
                    console.log(this.form);
            },
            saveOrUpdate2(){
                    request.request({
                    url:'/coach/saveOrUpdate',
                    method:'post',
                    headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                    },
                    data:qs.stringify(this.form)
                })
                .then(response=>{
                    this.visible = false;
                    this.$message({message:response.message,type:'success'});
                    this.reLoadData();
                })
            },
            toFindUsers(){
                this.visible2=true;
                request.get("/coach/findAllUsers")
                .then(result=>{
                     this.User = result.data;
                })
            },
            toFindArranges(){
                this.visible3=true;
                request.get("/coach/findAllArrangeByCoachId")
                .then(result=>{
                     this.Arrange = result.data;
                })
            },
            toDownLoad(){
                request.get("/coach/download")
            },
            toPass(date,phoneNum){
              let url = "/coach/sendMessages";
                request.get(url+"?dateTime="+date+"&phoneNum="+phoneNum);
            }
        }
    
}
</script>

<style scoped>

</style>
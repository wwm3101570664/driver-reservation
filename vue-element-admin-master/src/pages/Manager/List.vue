<template>
    <div class="coachAccept_list">
        <!--按钮 -->
        <div class="btns">
             <el-button type="primary" size="small" @click="toFindArrange">查看教学安排</el-button>
             <el-button type="primary" size="small" @click="toCheck">审批教练</el-button>
             <el-button type="primary" size="small" @click="toAdd">添加教练</el-button>
             <el-button type="primary" size="small" @click="toAddUser">添加学员</el-button>
             <el-button type="primary" size="small" @click="toPush">发布公告</el-button>
        </div>
        <!--表格 -->
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
          </template>
      </el-table-column>
    </el-table>

        <el-dialog title="添加教练" :visible.sync="visible">
                <el-form :model="addCoach">
                    <el-form-item label="教练姓名" label-width="80px">
                        <el-input v-model="addCoach.name" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="教练年龄" label-width="80px">
                        <el-input v-model="addCoach.age" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="教练性别" label-width="80px">
                        <el-select v-model="addCoach.gender" placeholder="请选择性别">
                            <el-option label="男" value="男"></el-option>
                            <el-option label="女" value="女"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="学费收费" label-width="80px">
                        <el-input v-model="addCoach.charges" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="登录密码" label-width="80px">
                        <el-input v-model="addCoach.password" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="车牌号" label-width="80px">
                        <el-input v-model="addCoach.carNum" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="车名" label-width="80px">
                        <el-input v-model="addCoach.carType" autocomplete="off" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="visible = false" size="small">取 消</el-button>
                    <el-button type="primary" size="small" @click="toAdd2">确 定</el-button>
                </div>
        </el-dialog>

        <el-dialog title="添加学员" :visible.sync="visible2">
                <el-form :model="addUser">
                    <el-form-item label="学员姓名" label-width="80px">
                        <el-input v-model="addUser.name" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="学员年龄" label-width="80px">
                        <el-input v-model="addUser.age" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="学员性别" label-width="80px">
                        <el-select v-model="addUser.gender" placeholder="请选择性别">
                            <el-option label="男" value="男"></el-option>
                            <el-option label="女" value="女"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="登录密码" label-width="80px">
                        <el-input v-model="addUser.password" autocomplete="off" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="visible2 = false" size="small">取 消</el-button>
                    <el-button type="primary" size="small" @click="toAddUser2">确 定</el-button>
                </div>
        </el-dialog>

         <el-dialog title="发布公告" :visible.sync="visible3">
          <el-form :model="form">
            <el-form-item>
                <el-input v-model="form.content" autocomplete="off" placeholder="请输入公告内容"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="visible3 = false" size="small">取 消</el-button>
              <el-button type="primary" size="small" @click="toPush2">确 定</el-button>
          </div>
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
            addCoach:[],
            visible:false,
            addUser:[],
            visible2:false,
            form:[],
            visible3:false
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
        },
        toReview(){
            let url = "/users/findMessages"
            request.get(url,{params:{coachId:id}})
            .then(response=>{
                this.$message({
                    message:response.message,
                })
            })
        },
        toCheck(){
            this.$router.push({path:"/Manager/List2"})
        },
        toFindArrange(){
            this.$router.push({path:"/Manager/FindAllArrange"})
        },
        toAdd(){
            this.visible=true;
        },
        toAdd2(){
            request.request({
                    url:'/coach/register',
                    method:'post',
                    headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                    },
                    data:qs.stringify(this.addCoach)
                })
                .then(response=>{
                    this.visible=false;
                    this.$message({message:response.message,type:'success'});
                    this.reLoadData();
                })
        },
        toAddUser(){
            this.visible2=true;
        },
        toAddUser2(){
            request.request({
                    url:'/users/register',
                    method:'post',
                    headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                    },
                    data:qs.stringify(this.addUser)
                })
                .then(response=>{
                    this.visible2=false;
                    this.$message({message:response.message,type:'success'});
                    this.reLoadData();
                })
        },
        toPush(){
            this.visible3=true;
        },
        toPush2(){
            request.request({
                    url:'/manager/pushAnnouncement',
                    method:'post',
                    headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                    },
                    data:qs.stringify(this.form)
                })
                .then(response=>{
                    this.visible3=false;
                    this.$message({message:response.message,type:'success'});
                    this.reLoadData();
                })
        }
        
    }
}
</script>
<style scoped>

</style>
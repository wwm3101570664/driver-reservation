<template>
    <div class="coachAccept_list">
        <!--按钮 -->
        <div class="btns" >
             <el-button type="primary" size="small" @click="toBinding">查看绑定个人信息</el-button>
             <el-button type="primary" size="small" @click="tofindMessage">查看教练详细信息</el-button>
             <el-button type="primary" size="small" @click="toArrange">预约</el-button>
        </div>
        <!-- 评价自己教练模态框 -->
        <el-dialog title="评价教练" :visible.sync="visible2">
          <el-form :model="form2">
            <el-form-item>
                <el-input v-model="form2.content" autocomplete="off" placeholder="请输入对自己教练的评价"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="visible2 = false" size="small">取 消</el-button>
              <el-button type="primary" size="small" @click="toComment2">确 定</el-button>
          </div>
        </el-dialog>

        <!-- 预约模态框 -->
        <el-dialog title="预约" :visible.sync="visible5">
          <el-form :model="newArrange">
            <el-form-item label="练车时间选择">
              <el-col :span="11">
                <el-form-item >
                  <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择练车日期" v-model="newArrange.date" @change="toArrange2"  style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-form-item>
            
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="visible5 = false" size="small">取 消</el-button>
              <el-button type="primary" size="small" @click="toArrange2">确 定</el-button>
          </div>
        </el-dialog>

        <!-- 查看自己教练详情模态框 -->
        <el-dialog title="查看教练详情" :visible.sync="visible3">
          <el-form :model="form3">
                <el-form-item label="教练姓名" label-width="80px">
                    <el-input v-model="coachAccept.name" autocomplete="off" readonly="true"></el-input>
                 </el-form-item>
                <el-form-item label="教练年龄" label-width="80px">
                    <el-input v-model="coachAccept.age" autocomplete="off" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="教练性别" label-width="80px">
                    <el-input v-model="coachAccept.gender" autocomplete="off" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="收费标准" label-width="80px">
                  <el-input v-model="coachAccept.charges" autocomplete="off" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="教练车牌号" label-width="80px">
                  <el-input v-model="form3.carNumber" autocomplete="off" readonly="true"></el-input>
                </el-form-item> 
                <el-form-item label="教练车类型" label-width="80px">
                  <el-input v-model="form3.type" autocomplete="off" readonly="true"></el-input>
                </el-form-item> 
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="visible3 = false" size="small">关 闭</el-button>
          </div>
        </el-dialog>

<!-- 预约状态 -->
       <el-table :data="Arrange" style="width: 100%" >
            <el-table-column prop="user.name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="date" label="练车时间" width="180"></el-table-column>
            <el-table-column prop="user.age" label="年龄" width="180"></el-table-column>
            <el-table-column prop="coachAccept.name" label="教练名称" width="180"></el-table-column>
            <el-table-column prop="car.carNumber" label="教练车车牌号" width="180"></el-table-column>
            <el-table-column prop="car.type" label="教练车车型" width="180"></el-table-column>
            <el-table-column fixed="right" label="操作" align="center" width="200">
                    <el-button @click="toDelete" type="text" size="small">删除预约</el-button>
      </el-table-column>
      </el-table>


      <!-- 修改学员信息模态框 -->
    <el-dialog title="修改用户信息" :visible.sync="visible">
      <el-form :model="form">
        <el-form-item label="姓名" label-width="80px">
          <el-input v-model="form.name" autocomplete="off" readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="年龄" label-width="80px">
          <el-input v-model="form.age" autocomplete="off" readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="80px">
          <el-select v-model="form.gender" placeholder="请选择性别" readonly="true">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" label-width="80px">
          <el-input v-model="form.phonenum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="80px">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item> 
       </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="visible = false" size="small">取 消</el-button>
        <el-button type="primary" size="small" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog> 
    <div>

    </div>
           <el-button type="primary" size="small" @click="toComment">评价</el-button>
           <el-button type="primary" size="small" @click="toCommets">查看其他教练评价</el-button>
    </div>
</template>

<script>
import request from "@/utils/request"
import qs from 'querystring'
export default {
    data(){
        return{
            Arrange:[{
              user:[],
              car:[],
              coachAccept:[]
            }],
           
            visible:false,
            form:[],
            visible2:false,
            form2:[],
            visible3:false,
            form3:[],
            coachAccept:[],

            newArrange:[{
              date:""
            }],
            visible5:false
        }
    },
    created(){
       this.reLoadData();
    },
    methods:{
        reLoadData(){
            request.get("/users/findArrange")
        .then(result=>{
            this.Arrange = result.data;
            this.user = this.Arrange.user
        })
        },
        toCommets(){
          this.$router.push({path:"/User/Comments"})
        },
            saveOrUpdate(){
                 request.request({
                    url:'/users/saveOrUpdate',
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
                toBinding(){
                    this.form = {};
                    this.visible = true;
                    request.get("/users/finduser")
                        .then(result=>{
                            this.form = result.data;
                        })
                },toComment(){
                    this.visible2=true;
                },toComment2(){
                     request.request({
                    url:'/users/makeComment',
                    method:'post',
                    headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                    },
                    data:qs.stringify(this.form2)
                })
                .then(response=>{
                    this.visible2 = false;
                    this.$message({message:response.message,type:'success'});
                    this.reLoadData();
                })
                },
                tofindMessage(){
                    this.visible3=true;
                    this.form3 = {};
                     request.get("/users/findMessages")
                        .then(result=>{
                            this.form3 = result.data;
                            this.coachAccept=this.form3.coachAccept
                        })
                },
                toArrange(){
                    this.visible5=true;
                },
                 toArrange2(val){
                   this.newArrange.date=val;
                     request.request({
                    url:'/users/booking',
                    method:'post',
                    headers:{
                    'Content-Type':'application/x-www-form-urlencoded'
                    },
                    data:qs.stringify(this.newArrange)
                })
                .then(response=>{
                    this.visible5 = false;
                    this.$message({message:response.message,type:'success'});
                    this.reLoadData();
                })
                },
                toDelete(){
                    request.get("/users/deleteBooking")
                    .then(response=>{
                    this.$message({message:response.message,type:'success'});
                    this.reLoadData();
                })
                }

            }

    }

</script>
<style scoped>

</style>
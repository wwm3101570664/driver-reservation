<template>
    <div class="addCoach">
        <el-button type="text" @click="back">返回</el-button>

        {{form}}
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="教练名称">
                <el-input v-model="form.name" ></el-input>
            </el-form-item>
            <el-form-item label="年龄">
                <el-input v-model="form.age"></el-input>
            </el-form-item>
             <el-form-item label="性别">
                <el-input v-model="form.gender"></el-input>
            </el-form-item>
             <el-form-item label="收费标准">
                <el-input v-model="form.charges"></el-input>
            </el-form-item>
             <el-form-item label="密码">
                <el-input v-model="form.password"></el-input>
            </el-form-item>
             <el-form-item label="车牌号码">
                <el-input v-model="form.carNum"></el-input>
            </el-form-item>
             <el-form-item label="车辆类型">
                <el-input v-model="form.carType"></el-input>
            </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
        </el-form>

    </div>
</template>
<script>
import request from "@/utils/request"
import qs from 'querystring'
export default {
    data(){
        return{
            form:{}
        }
    },
    methods:{
        back(){
            this.$router.go(-1);
        },
        onSubmit(){
            request.get("/manager/addCoachAndCar",{
                method:"GET",
                headers:{
                    "Content-Type":"application/x-www-form-urlencoded"
                },
                data:qs.stringify(this.form)
            })
            .then(result=>{
                alert(result.message)
            });
        }
    }
}
</script>
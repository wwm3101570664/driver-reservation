<template>
    <div class="Announcement">
        <el-table :data="form" style="width: 100%">
            <el-table-column prop="time" label="公告发表时间" width="180"></el-table-column>
            <el-table-column prop="content" label="公告内容" width="180"></el-table-column>
         </el-table>
    </div>
</template>

<script>
import qs from 'querystring'
import request from "@/utils/request"
export default {
     data(){
        return{
            form:[]
        }
     },
     created(){
         this.reLoadData();
     },
     methods:{
         reLoadData(){
            request.get("/manager/findAllAnnouncement")
            .then(result=>{
                this.form = result.data;
            });
     }
     }
         
    
}
</script>
<template>
  <el-dialog
    :title="!dataForm.requestId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="学号" prop="studentId">
      <el-input v-model="dataForm.studentId" placeholder="学号"></el-input>
    </el-form-item>
    <el-form-item label="考试编号" prop="examId">
      <el-input v-model="dataForm.examId" placeholder="考试编号"></el-input>
    </el-form-item>
    <el-form-item label="科目" prop="subject">
      <el-input v-model="dataForm.subject" placeholder="科目"></el-input>
    </el-form-item>
    <el-form-item label="处理状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="处理状态"></el-input>
    </el-form-item>
    <el-form-item label="反馈原因" prop="reason">
      <el-input v-model="dataForm.reason" placeholder="反馈原因"></el-input>
    </el-form-item>
    <el-form-item label="处理回复" prop="response">
      <el-input v-model="dataForm.response" placeholder="处理回复"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          requestId: 0,
          studentId: '',
          examId: '',
          subject: '',
          status: '',
          reason: '',
          response: ''
        },
        dataRule: {
          studentId: [
            { required: true, message: '学号不能为空', trigger: 'blur' }
          ],
          examId: [
            { required: true, message: '考试编号不能为空', trigger: 'blur' }
          ],
          subject: [
            { required: true, message: '科目不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '处理状态不能为空', trigger: 'blur' }
          ],
          reason: [
            { required: true, message: '反馈原因不能为空', trigger: 'blur' }
          ],
          response: [
            { required: true, message: '处理回复不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.requestId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.requestId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/reviewrequests/info/${this.dataForm.requestId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.studentId = data.reviewRequests.studentId
                this.dataForm.examId = data.reviewRequests.examId
                this.dataForm.subject = data.reviewRequests.subject
                this.dataForm.status = data.reviewRequests.status
                this.dataForm.reason = data.reviewRequests.reason
                this.dataForm.response = data.reviewRequests.response
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/reviewrequests/${!this.dataForm.requestId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'requestId': this.dataForm.requestId || undefined,
                'studentId': this.dataForm.studentId,
                'examId': this.dataForm.examId,
                'subject': this.dataForm.subject,
                'status': this.dataForm.status,
                'reason': this.dataForm.reason,
                'response': this.dataForm.response
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>

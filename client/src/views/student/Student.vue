<template>
    <div>
        <h3 class="text-left">Student List <button @click="showModal({firstname:'',lastname:'',age:''})" class="btn">Add</button></h3>
        <div  v-show="isModalVisible" @close="closeModal">
            <transition name="modal" >
                <div class="modal-mask" @click="close" >
                    <div class="modal-container" @click.stop>
                        <form class="form-horizontal text-left">
                            <div class="modal-header">
                                <h3>Student</h3>
                            </div>
                            <div class="modal-body">
                                <label class="form-label">
                                    First Name
                                    <input v-model="student.firstname" class="form-control">
                                </label>
                                <label class="form-label">
                                    Last Name
                                    <input v-model="student.lastname" class="form-control">
                                </label>
                                <label class="form-label">
                                    Age
                                    <input v-model="student.age" class="form-control">
                                </label>
                            </div>
                            <div class="modal-footer text-right">
                                <button class="modal-default-button" @click="closeModal()">
                                    Cancel
                                </button>
                                <button class="modal-default-button" @click="saveStudent(student)">
                                    Save
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </transition>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr  v-for="student in students" :key="student.id">
                    <td @click="showModal(student)">
                        {{student.firstname}}
                    </td>
                    <td @click="showModal(student)">
                        {{student.lastname}}
                    </td>
                    <td @click="showModal(student)">
                        {{student.age}}
                    </td>
                    <td @click="deleteStudent(student)">
                        <i  class="fa fa-close">X</i>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
    import StudentService from '../../services/student.service';
    export default {
        name:'StudentList',
        data(){
            return {
                students:[],
                isModalVisible: false,
                student:{firstname:'',lastname:'',age:''}
            }
        },
        created(){
            this.fetchStudents();
        },
        methods:{
            fetchStudents:function(){
                let that = this;
                StudentService.fetchStudents()
                    .then((result) => {
                        that.students = result.content
                    });
            },
            deleteStudent:function(student){
                let that = this;
                StudentService.deleteStudent(student.id)
                    .then((result) => {
                        if(result){
                            that.fetchStudents();
                        }
                    })
            },
            showModal(student) {
                this.student = student;
                this.isModalVisible = true;
            },
            close: function () {
                this.$emit('close');
            },
            saveStudent: function (student) {
                let that = this;
                StudentService.saveStudent(student)
                    .then((result) => {
                        that.closeModal();
                    });
            },
            closeModal() {
                this.close();
                this.isModalVisible = false;
            }
        }

    }
</script>
<style scoped>
    * {
        box-sizing: border-box;
    }
    .modal-mask {
        position: fixed;
        z-index: 9998;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .5);
        transition: opacity .3s ease;
    }
    .modal-container {
        width: 500px;
        margin: 40px auto 0;
        padding: 20px 30px;
        background-color: #fff;
        border-radius: 2px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
        transition: all .3s ease;
        font-family: Helvetica, Arial, sans-serif;
    }
    .modal-header h3 {
        margin-top: 0;
        color: #42b983;
    }
    .modal-body {
        margin: 20px 0;
    }
    .text-right {
        text-align: right;
    }
    .form-label {
        display: block;
        margin-bottom: 1em;
    }
    .form-label > .form-control {
        margin-top: 0.5em;
    }
    .form-control {
        display: block;
        width: 100%;
        padding: 0.5em 1em;
        line-height: 1.5;
        border: 1px solid #ddd;
    }
    .modal-enter {
        opacity: 0;
    }
    .modal-leave-active {
        opacity: 0;
    }
    .fa-close{
        color:red;
    }
    .modal-enter .modal-container,
    .modal-leave-active .modal-container {
        -webkit-transform: scale(1.1);
        transform: scale(1.1);
    }
</style>
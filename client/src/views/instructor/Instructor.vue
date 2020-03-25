<template>
    <div>
        <h3 class="text-left">Instructor List <button @click="showModal({firstname:'',lastname:'',age:''})" class="btn">Add</button></h3>
        <div v-show="isModalVisible" @close="closeModal">
            <transition name="modal" >
                <div class="modal-mask" @click="close" >
                    <div class="modal-container" @click.stop>
                        <form class="form-horizontal text-left">
                            <div class="modal-header">
                                <h3>Instructor</h3>
                            </div>
                            <div class="modal-body">
                                <label class="form-label">
                                    First Name
                                    <input v-model="instructor.firstname" class="form-control">
                                </label>
                                <label class="form-label">
                                    Last Name
                                    <input v-model="instructor.lastname" class="form-control">
                                </label>
                                <label class="form-label">
                                    Age
                                    <input v-model="instructor.age" class="form-control">
                                </label>
                            </div>
                            <div class="modal-footer text-right">
                                <button class="modal-default-button" @click="closeModal()">
                                    Cancel
                                </button>
                                <button class="modal-default-button" @click="saveInstructor(instructor)">
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
            <tr v-for="instructor in instructors" :key="instructor.id">
                <td @click="showModal(instructor)">
                    {{instructor.firstname}}
                </td>
                <td @click="showModal(instructor)">
                    {{instructor.lastname}}
                </td>
                <td @click="showModal(instructor)">
                    {{instructor.age}}
                </td>
                <td @click="deleteInstructor(instructor)">
                    <i class="fa fa-close">X</i>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</template>
<script>
    import InstructorService from '../../services/instructor.service';
    export default {
        name:'InstructorList',
        data(){
            return {
                instructors:[],
                isModalVisible: false,
                instructor:{firstname:'',lastname:'',age:''}
            }
        },
        created(){

            this.fetchInstructors();
        },
        methods:{
            fetchInstructors:function(){
                InstructorService.fetchInstructors()
                .then((result) => {
                    this.instructors = result.content
                });
            },
            deleteInstructor(instructor){
               InstructorService.deleteInstructor(instructor.id);
            },
            showModal(instructor) {
                this.instructor = instructor;
                this.isModalVisible = true;
            },
            closeModal() {
                this.isModalVisible = false;
            },
            close: function () {
                this.$emit('close');
            },
            saveInstructor: function (instructor) {
                let that = this;
                InstructorService.saveInstructor(instructor,function(json){
                        that.close();
                        that.isModalVisible = false;
                        that.fetchInstructors();
                });
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
    .fa-close{
        color:red;
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
    .modal-enter .modal-container,
    .modal-leave-active .modal-container {
        -webkit-transform: scale(1.1);
        transform: scale(1.1);
    }
</style>
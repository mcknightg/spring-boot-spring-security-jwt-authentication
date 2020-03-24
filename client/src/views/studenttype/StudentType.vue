<template>
    <div>
        <h3 class="text-left">Student Type List <button @click="showModal({firstname:'',lastname:'',age:''})" class="btn">Add</button></h3>
        <div  v-show="isModalVisible" @close="closeModal">
            <transition name="modal" >
                <div class="modal-mask" @click="close" >
                    <div class="modal-container" @click.stop>
                        <form class="form-horizontal text-left">
                            <div class="modal-header">
                                <h3>Studenttype</h3>
                            </div>
                            <div class="modal-body">
                                <label class="form-label">
                                    Name
                                    <input v-model="studenttype.name" class="form-control">
                                </label>
                            </div>
                            <div class="modal-footer text-right">
                                <button class="modal-default-button" @click="closeModal()">
                                    Cancel
                                </button>
                                <button class="modal-default-button" @click="saveStudenttype(studenttype)">
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
                <th>Name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr  v-for="studenttype in studenttypes" :key="studenttype.id">
                <td @click="showModal(studenttype)">
                    {{studenttype.name}}
                </td>
                <td @click="deleteStudenttype(studenttype)">
                    <i  class="fa fa-close">X</i>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
    export default {
        name:'StudenttypeList',
        data(){
            return {
                studenttypes:[],
                isModalVisible: false,
                studenttype:{name:''}
            }
        },
        created(){
            this.fetchStudenttypes();
        },
        methods:{
            fetchStudenttypes:function(){
                const baseURI = 'http://localhost:8083/studenttype';
                this.$http.get(baseURI)
                    .then((result) => {
                        this.studenttypes = result.data.content
                    })
            },
            deleteStudenttype:function(studenttype){
                const baseURI = 'http://localhost:8083/studenttype/' + studenttype.id;
                this.$http.delete(baseURI)
                    .then((result) => {
                        if(result){
                            this.fetchStudenttypes();
                        }
                    })
            },
            showModal(studenttype) {
                this.studenttype = studenttype;
                this.isModalVisible = true;
            },
            close: function () {
                this.$emit('close');
            },
            saveStudenttype: function (studenttype) {
                const baseURI = 'http://localhost:8083/studenttype';
                this.$http.post(baseURI,studenttype)
                    .then((result) => {
                        if(result){
                            this.fetchStudenttypes();
                            this.closeModal();
                        }
                    })
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
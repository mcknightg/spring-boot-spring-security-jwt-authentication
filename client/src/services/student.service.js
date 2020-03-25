import axios from 'axios';

const API_URL = 'http://localhost:8085/api/auth/';
class StudentService {
    deleteStudent(id) {
        return axios
            .delete(API_URL + 'student/' + id);
    }
    saveStudent(student){
        return axios
            .post(API_URL + 'student/', student)
            .then(response => {
                return response.data;
            });
    }
    fetchStudents(){
        return axios
            .get(API_URL + 'student')
            .then(response => {
                return response.data;
            });
    }
}

export default new StudentService();
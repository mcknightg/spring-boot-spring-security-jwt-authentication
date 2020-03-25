import axios from 'axios';

const API_URL = 'http://localhost:8085/api/auth/';
class StudenttypeService {
    deleteStudenttype(id) {
        return axios
            .delete(API_URL + 'studenttype/' + id);
    }
    saveStudenttype(studenttype){
        return axios
            .post(API_URL + 'studenttype/', studenttype)
            .then(response => {
                return response.data;
            });
    }
    fetchStudenttypes(){
        return axios
            .get(API_URL + 'studenttype')
            .then(response => {
                return response.data;
            });
    }
}

export default new StudenttypeService();
import axios from 'axios';

const API_URL = 'http://localhost:8085/api/auth/';
class InstructorService {
    deleteInstructor(id) {
        return axios.delete(API_URL + 'instructor/' + id);
    }
    saveInstructor(instructor){
        return axios
            .post(API_URL + 'instructor/', instructor)
            .then(response => {
                return response.data;
            });
    }
    fetchInstructors(){
        return axios
            .get(API_URL + 'instructor/')
            .then(response => {
                return response.data;
            });
    }
}

export default new InstructorService();
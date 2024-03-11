app.service('UserService', ($http) => {

    const BASE_URL = 'http://localhost:8080/passguard/api/users';

    
    this.create = (userData) => {
       return $http.post(BASE_URL, userData);
    }

    this.findAll = () => {
        return $http.get(`${BASE_URL}`);
    };

    this.findById = (userId) => {
        return $http.get(`${BASE_URL}/${userId}`);
    };

    this.update = (userId, userData) => {
        return $http.put(`${BASE_URL}/${userId}`, userData);
    };

    this.delete = (userId) => {
        return $http.delete(`${BASE_URL}/${userId}`);
    }

})
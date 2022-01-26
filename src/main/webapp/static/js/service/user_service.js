'use strict';

angular.module('myApp').factory('UserService', UserServiceFactory)

UserServiceFactory.$inject = ['$http']

function UserServiceFactory($http) {

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser: updateUser,
        deleteUser: deleteUser
    };

    return factory;

    function fetchAllUsers() {
        return $http.get(REST_SERVICE_URI).then((res) => res.data);
    }

    function createUser(user) {
        return $http.post(REST_SERVICE_URI, user);
    }


    function updateUser(user, id) {
        return $http.put(REST_SERVICE_URI + id, user);
    }

    function deleteUser(id) {
        return $http.delete(REST_SERVICE_URI + id);
    }

}

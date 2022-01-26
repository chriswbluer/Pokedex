'use strict';

// TODO: Refactor with John Papas style guide.
angular.module('myApp').controller('UserController', ['$scope', '$log', 'UserService', function ($scope, $log, UserService) {
    var self = this;
    self.user = {};
    self.users = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllUsers();

    function fetchAllUsers() {
        UserService.fetchAllUsers()
            .then(
                function (d) {
                    self.users = d;
                    $log.log('fetchAllUsers(): self.users ', self.users);
                },
                function (errResponse) {
                    $log.error('Error while fetching Users');
                }
            );
    }

    function createUser(user) {
        UserService.createUser(user)
            .then(
                fetchAllUsers,
                function (errResponse) {
                    $log.error('Error while creating User');
                }
            );
    }

    function updateUser(user, id) {
        UserService.updateUser(user, id)
            .then(
                fetchAllUsers,
                function (errResponse) {
                    $log.error('Error while updating User');
                }
            );
    }

    function deleteUser(id) {
        UserService.deleteUser(id)
            .then(
                fetchAllUsers,
                function (errResponse) {
                    $log.error('Error while deleting User');
                }
            );
    }

    function submit() {
        if (self.user.id === null) {
            $log.log('Saving New User', self.user);
            createUser(self.user);
        } else {
            updateUser(self.user, self.user.id);
            $log.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id) {
        $log.log('id to be edited', id);
        for (var i = 0; i < self.users.length; i++) {
            if (self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id) {
        $log.log('id to be deleted', id);
        if (self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset() {
        self.user = { id: null, username: '', address: '', email: '' };
        $scope.myForm.$setPristine(); //reset Form
    }

}]);

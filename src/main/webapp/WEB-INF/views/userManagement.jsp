<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>User Management</title>
            <style>
                .username.ng-valid {
                    background-color: lightgreen;
                }

                .username.ng-dirty.ng-invalid-required {
                    background-color: red;
                }

                .username.ng-dirty.ng-invalid-minlength {
                    background-color: yellow;
                }

                .email.ng-valid {
                    background-color: lightgreen;
                }

                .email.ng-dirty.ng-invalid-required {
                    background-color: red;
                }

                .email.ng-dirty.ng-invalid-email {
                    background-color: yellow;
                }

            </style>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
                integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
                crossorigin="anonymous">
            </link>

        </head>

        <body ng-app="myApp" class="ng-cloak bg-light">
            <div class="container" ng-controller="UserController as userController">
                <div class="panel panel-default">
                    <div class="panel-heading"><span class="lead">User Registration Form </span></div>
                    <div class="formcontainer">
                        <form ng-submit="userController.submit()" name="myForm" class="form-horizontal">
                            <input type="hidden" ng-model="userController.user.id" />
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="file">Name</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="userController.user.username" name="uname"
                                            class="username form-control input-sm" placeholder="Enter your name"
                                            required ng-minlength="3" />
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                            <span ng-show="myForm.uname.$error.minlength">Minimum length required is
                                                3</span>
                                            <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="file">Address</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="userController.user.address"
                                            class="form-control input-sm"
                                            placeholder="Enter your Address. [This field is validation free]" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="file">Email</label>
                                    <div class="col-md-7">
                                        <input type="email" ng-model="userController.user.email" name="email"
                                            class="email form-control input-sm" placeholder="Enter your Email"
                                            required />
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.email.$error.required">This is a required field</span>
                                            <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-actions floatRight">
                                    <input type="submit" value="{{!userController.user.id ? 'Add' : 'Update'}}"
                                        class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                                    <button type="button" ng-click="userController.reset()"
                                        class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
                                        Form</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><span class="lead">List of Users </span></div>
                    <div class="tablecontainer">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Address</th>
                                    <th>Email</th>
                                    <th width="20%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="u in userController.users">
                                    <td><span ng-bind="u.id"></span></td>
                                    <td><span ng-bind="u.username"></span></td>
                                    <td><span ng-bind="u.address"></span></td>
                                    <td><span ng-bind="u.email"></span></td>
                                    <td>
                                        <button type="button" ng-click="userController.edit(u.id)"
                                            class="btn btn-success custom-width" id="editButton{{u.id}}">Edit</button> <button type="button"
                                            ng-click="userController.remove(u.id)"
                                            class="btn btn-danger custom-width" id="removeButton{{u.id}}">Remove</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
            <script src="/static/js/app.js"></script>
            <script src="/static/js/service/user_service.js"></script>
            <script src="/static/js/controller/user_controller.js"></script>
        </body>

        </html>

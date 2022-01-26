<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>Sign Up</title>
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

        </head>

        <body ng-app="myApp" class="ng-cloak">
            <div class="container" ng-controller="UserController as userController">
                <div class="panel panel-default">
                    <div class="panel-heading"><span class="lead">Sign Up</span></div>
                    <div class="formcontainer">
                        <form ng-submit="userController.submit()" name="myForm" class="form-horizontal">
                            <input type="hidden" ng-model="userController.user.id" />
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="uname">Username</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="userController.user.username" id="uname"
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
                                    <label class="col-md-2 control-lable" for="email">Email</label>
                                    <div class="col-md-7">
                                        <input type="email" ng-model="userController.user.email" id="email"
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
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="password">Password</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="userController.user.password" id="password"
                                            class="email form-control input-sm" placeholder="Enter your Password"
                                            required />
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.password.$error.required">This is a required
                                                field</span>
                                            <span ng-show="myForm.password.$invalid">This field is invalid </span>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="form-group col-md-12">
                                    <div class="col-md-7">
                                        <input type="button" ng-click="userController.submit()" value="Submit"
                                            class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                                        <button type="button" ng-click="userController.reset()"
                                            class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
                                            Form</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
            <script src="/static/js/app.js"></script>
            <script src="/static/js/service/user_service.js"></script>
            <script src="/static/js/controller/user_controller.js"></script>
        </body>

        </html>

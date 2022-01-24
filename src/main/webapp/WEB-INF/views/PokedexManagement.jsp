<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Add Pokemon Page</title>
            <style>
                .name.ng-valid {
                    background-color: lightgreen;
                }

                .name.ng-dirty.ng-invalid-required {
                    background-color: red;
                }

                .name.ng-dirty.ng-invalid-minlength {
                    background-color: yellow;
                }

                .defense.ng-valid {
                    background-color: lightgreen;
                }

                .defense.ng-dirty.ng-invalid-required {
                    background-color: red;
                }

                .defense.ng-dirty.ng-invalid-defense {
                    background-color: yellow;
                }

            </style>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
                integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
                crossorigin="anonymous">
            </link>

        </head>

        <body ng-app="myApp" class="ng-cloak bg-light">
            <div
                class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
                <h5 class="my-0 mr-md-auto font-weight-normal">Add Pokemon to Pokedex</h5>
                <nav class="my-2 my-md-0 mr-md-3">
                    <a class="p-2 text-dark" href="/landingpage">Home</a>
                </nav>
            </div>
            <div class="container" ng-controller="PokemonController as pokemonController">
                <div class="panel panel-default">
                    <div class="formcontainer">
                        <form ng-submit="pokemonController.submit()" name="myForm" class="form-horizontal">
                            <input type="hidden" ng-model="pokemonController.pokemon.id" />
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="file">Pokemon Name</label>
                                    <div class="col-md-7">
                                        <input type="text" ng-model="pokemonController.pokemon.name" name="name"
                                            class="name form-control input-sm" placeholder="Enter Pokemons Name"
                                            required ng-minlength="3" />
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.name.$error.required">This is a required field</span>
                                            <span ng-show="myForm.name.$error.minlength">Minimum length required is
                                                3</span>
                                            <span ng-show="myForm.name.$invalid">This field is invalid </span>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="file">Attack</label>
                                    <div class="col-md-7">
                                        <input type="number" ng-model="pokemonController.pokemon.attack"
                                            class="form-control input-sm"
                                            placeholder="Enter the Pokemons Attack. [This field is validation free]" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="file">Defense</label>
                                    <div class="col-md-7">
                                        <input type="number" ng-model="pokemonController.pokemon.defense" name="defense"
                                            class="defense form-control input-sm"
                                            placeholder="Enter the Pokemons Defense" required />
                                        <div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.defense.$error.required">This is a required
                                                field</span>
                                            <span ng-show="myForm.defense.$invalid">This field is invalid </span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-md-12">
                                    <div class="col-md-7">
                                        <input type="submit"
                                            value="{{!pokemonController.pokemon.id ? 'Add' : 'Update'}}"
                                            class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                                        <button type="button" ng-click="pokemonController.reset()"
                                            class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
                                            Form</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><span class="lead">Pokedex of Pokemon </span></div>
                    <div class="tablecontainer">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Attack</th>
                                    <th>Defense</th>
                                    <th width="20%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="u in pokemonController.pokemons">
                                    <td><span ng-bind="u.id"></span></td>
                                    <td><span ng-bind="u.name"></span></td>
                                    <td><span ng-bind="u.attack"></span></td>
                                    <td><span ng-bind="u.defense"></span></td>
                                    <td>
                                        <button type="button" ng-click="pokemonController.edit(u.id)"
                                            class="btn btn-success custom-width">Edit</button> <button type="button"
                                            ng-click="pokemonController.remove(u.id)"
                                            class="btn btn-danger custom-width">Remove</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
            <script src="<c:url value='/static/js/app.js' />"></script>
            <script src="<c:url value='/static/js/service/pokemon_service.js' />"></script>
            <script src="<c:url value='/static/js/controller/pokemon_controller.js' />"></script>
        </body>

        </html>

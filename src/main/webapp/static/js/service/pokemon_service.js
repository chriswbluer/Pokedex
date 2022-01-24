'use strict';

angular.module('myApp').factory('PokemonService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8080/pokemon/';

    var factory = {
        fetchAllPokemons: fetchAllPokemons,
        createPokemon: createPokemon,
        updatePokemon: updatePokemon,
        deletePokemon: deletePokemon
    };

    return factory;

    function fetchAllPokemons() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Pokemons');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createPokemon(pokemon) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, pokemon)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while creating Pokemon');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updatePokemon(pokemon, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI + id, pokemon)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while updating Pokemon');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deletePokemon(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while deleting Pokemon');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

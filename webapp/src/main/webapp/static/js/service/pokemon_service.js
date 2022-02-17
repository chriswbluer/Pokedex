'use strict';

angular.module('myApp').factory('PokemonService', PokemonServiceFactory)

PokemonServiceFactory.$inject = ['$http']

function PokemonServiceFactory($http) {

    var REST_SERVICE_URI = 'http://localhost:8080/pokemon/';

    var factory = {
        fetchAllPokemons: fetchAllPokemons,
        createPokemon: createPokemon,
        updatePokemon: updatePokemon,
        deletePokemon: deletePokemon
    };

    return factory;

    function fetchAllPokemons() {
        return $http.get(REST_SERVICE_URI).then((res) => res.data);
    }

    function createPokemon(pokemon) {
        return $http.post(REST_SERVICE_URI, pokemon);
    }


    function updatePokemon(pokemon, id) {
        return $http.put(REST_SERVICE_URI + id, pokemon);
    }

    function deletePokemon(id) {
        return $http.delete(REST_SERVICE_URI + id);
    }

}

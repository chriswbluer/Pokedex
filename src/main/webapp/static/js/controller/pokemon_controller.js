'use strict';

// TODO: Refactor with John Papas style guide.
angular.module('myApp').controller('PokemonController', ['$scope', '$log', 'PokemonService', function ($scope, $log, PokemonService) {
    var self = this;
    self.pokemon = {};
    self.pokemons = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllPokemons();

    function fetchAllPokemons() {
        PokemonService.fetchAllPokemons()
            .then(
                function (d) {
                    self.pokemons = d;
                    $log.log('fetchAllPokemons(): self.pokemons ', self.pokemons);
                },
                function (errResponse) {
                    $log.error('Error while fetching Pokemons');
                }
            );
    }

    function createPokemon(pokemon) {
        PokemonService.createPokemon(pokemon)
            .then(
                fetchAllPokemons,
                function (errResponse) {
                    $log.error('Error while creating Pokemon');
                }
            );
    }

    function updatePokemon(pokemon, id) {
        PokemonService.updatePokemon(pokemon, id)
            .then(
                fetchAllPokemons,
                function (errResponse) {
                    $log.error('Error while updating Pokemon');
                }
            );
    }

    function deletePokemon(id) {
        PokemonService.deletePokemon(id)
            .then(
                fetchAllPokemons,
                function (errResponse) {
                    $log.error('Error while deleting Pokemon');
                }
            );
    }

    function submit() {
        if (self.pokemon.id === undefined) {
            $log.log('Saving New Pokemon', self.pokemon);
            createPokemon(self.pokemon);
        } else {
            updatePokemon(self.pokemon, self.pokemon.id);
            $log.log('Pokemon updated with id ', self.pokemon.id);
        }
        reset();
    }

    function edit(pokemon) {
        $log.log('pokemon.id to be edited', pokemon.id);
        for (var i = 0; i < self.pokemons.length; i++) {
            if (self.pokemons[i].id === pokemon.id) {
                self.pokemon = angular.copy(self.pokemons[i]);
                break;
            }
        }
    }

    function remove(id) {
        $log.log('id to be deleted', id);
        if (self.pokemon.id === id) {//clean form if the pokemon to be deleted is shown there.
            reset();
        }
        deletePokemon(id);
    }


    function reset() {
        self.pokemon = { id: null, name: '', attack: '', defense: '' };
        $scope.myForm.$setPristine(); //reset Form
    }

}]);

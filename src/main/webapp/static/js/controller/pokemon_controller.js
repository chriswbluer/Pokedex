'use strict';

angular.module('myApp').controller('PokemonController', ['$scope', 'PokemonService', function($scope, PokemonService) {
    var self = this;
    self.pokemon={id:null, name:'', attack:'', defense:''};
    self.pokemons=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllPokemons();

    function fetchAllPokemons(){
        PokemonService.fetchAllPokemons()
            .then(
            function(d) {
                self.pokemons = d;
                console.log('fetchAllPokemons(): self.pokemons ', self.pokemons);
            },
            function(errResponse){
                console.error('Error while fetching Pokemons');
            }
        );
    }

    function createPokemon(pokemon){
        PokemonService.createPokemon(pokemon)
            .then(
            fetchAllPokemons,
            function(errResponse){
                console.error('Error while creating Pokemon');
            }
        );
    }

    function updatePokemon(pokemon, id){
        PokemonService.updatePokemon(pokemon, id)
            .then(
            fetchAllPokemons,
            function(errResponse){
                console.error('Error while updating Pokemon');
            }
        );
    }

    function deletePokemon(id){
        PokemonService.deletePokemon(id)
            .then(
            fetchAllPokemons,
            function(errResponse){
                console.error('Error while deleting Pokemon');
            }
        );
    }

    function submit() {
        if(self.pokemon.id===null){
            console.log('Saving New Pokemon', self.pokemon);
            createPokemon(self.pokemon);
        }else{
            updatePokemon(self.pokemon, self.pokemon.id);
            console.log('Pokemon updated with id ', self.pokemon.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.pokemons.length; i++){
            if(self.pokemons[i].id === id) {
                self.pokemon = angular.copy(self.pokemons[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.pokemon.id === id) {//clean form if the pokemon to be deleted is shown there.
            reset();
        }
        deletePokemon(id);
    }


    function reset(){
        self.pokemon={id:null, name:'', attack:'', defense:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);

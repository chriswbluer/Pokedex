import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { PokedexService } from './services/pokedex.service';

@Component({
  selector: 'app-pokedex-page',
  templateUrl: './pokedex-page.component.html',
  styleUrls: ['./pokedex-page.component.css'],
})
export class PokedexPageComponent implements OnInit {

  pokemonForm = new FormGroup({
    id: new FormControl(null),
    name: new FormControl('', Validators.required),
    attack: new FormControl(null),
    defense: new FormControl(null, Validators.required),
  });

  pokemonArray: any;

  constructor(
    private pokedexService: PokedexService
  ) {}

  ngOnInit(): void {
    this.getPokemon();
  }

  onSubmit() {
    console.log(
      'On submit was called. this.pokemonForm.value: ',
      this.pokemonForm.value
    );
    this.onReset();
  }

  onEdit(pokemon: any) {
    console.log('On edit was called. pokemon: ', pokemon);
    this.pokemonForm.patchValue({
      id: pokemon.id,
      name: pokemon.name,
      attack: pokemon.attack,
      defense: pokemon.defense,
    });
  }

  onReset() {
    console.log('Resetting Form... ');
    this.pokemonForm.reset();
  }

  getPokemon() {
    this.pokedexService.getPokemon().subscribe((pokeArr: any) => {
      this.pokemonArray = pokeArr;
      console.log('Getting Pokemon... ', this.pokemonArray);
    });
  }

  onAdd(pokemon: any) {
    this.pokedexService.createPokemon(pokemon).subscribe(() => {
      this.getPokemon();
      console.log('Adding Pokemon... ', pokemon);
    });
  }

  onUpdate(pokemon: any) {
    this.pokedexService.updatePokemon(pokemon).subscribe(() => {
      this.getPokemon();
      console.log('Updating Pokemon... ', pokemon);
    });
  }

  onRemove(pokemonId: any) {
    this.pokedexService.deletePokemon(pokemonId).subscribe(() => {
      this.getPokemon();
      console.log('Removing Pokemon... ', pokemonId);
    });
  }
}

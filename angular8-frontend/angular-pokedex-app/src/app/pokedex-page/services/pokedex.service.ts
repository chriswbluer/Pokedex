import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PokedexService {

  restApiUrl: string = "http://localhost:8080/pokemon/";

  constructor(private http: HttpClient) {}

  getPokemon(){
    return this.http.get<any>(this.restApiUrl);
  }

  createPokemon(pokemon: any) {
    return this.http.post(this.restApiUrl, pokemon);
  }

  updatePokemon(pokemon: any) {
    return this.http.put(this.restApiUrl + pokemon.id, pokemon);
  }

  deletePokemon(pokemonId: any) {
    return this.http.delete("http://localhost:8080/pokemon/" + pokemonId);
  }
}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PokedexPageRoutingModule } from './pokedex-page-routing.module';
import { PokedexPageComponent } from './pokedex-page.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    PokedexPageComponent
  ],
  imports: [
    CommonModule,
    PokedexPageRoutingModule,
    ReactiveFormsModule
  ]
})
export class PokedexPageModule { }

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <title>Pokédex</title>
      <style>
        html {
          font-size: 14px;
        }

        @media (min-width: 768px) {
          html {
            font-size: 16px;
          }
        }

        .container {
          max-width: 960px;
        }

        .pricing-header {
          max-width: 700px;
        }

        .card-deck .card {
          min-width: 220px;
        }

        .border-top {
          border-top: 1px solid #e5e5e5;
        }

        .border-bottom {
          border-bottom: 1px solid #e5e5e5;
        }

        .box-shadow {
          box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
        }

      </style>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
      </link>
    </head>

    <body class="bg-light">
      <nav>
        <div
          class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
          <h5 class="my-0 mr-md-auto font-weight-normal">Pokédex</h5>
          <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/pokedexmanagement" id="addPokemonLink">Add Pokémon</a>
          </nav>
          <a class="btn btn-outline-primary" href="#" id="signUpLink">Sign up</a>
        </div>
      </nav>

      <main>
        <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
          <h1 class="display-4">A Pokédex for Pokémon.</h1>
          <p class="lead">Build a Pokédex of the Pokémon you encounter.</p>
        </div>
        <div class="container">
          <div class="card-deck mb-3 text-center">
            <div class="card mb-4 box-shadow">
              <div class="card-header">
                <h4 class="my-0 font-weight-normal">Welcome ${user}!</h4>
              </div>
              <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                  <li><img src="/static/resources/pokedex-pixel-art-large.gif" class="img-fluid"
                      alt="Responsive image"></li>
                </ul>
              </div>
            </div>
            <div class="card mb-4 box-shadow">
              <div class="card-header">
                <h4 class="my-0 font-weight-normal">What are Pokémon?</h4>
              </div>
              <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                  <li>A series of Japanese video games and related media such as trading cards and television programs,
                    featuring cartoon monsters that are captured by players and trained to battle each other.</li>
                </ul>
              </div>
            </div>
            <div class="card mb-4 box-shadow">
              <div class="card-header">
                <h4 class="my-0 font-weight-normal">What is a Pokédex?</h4>
              </div>
              <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                  <li>A Pokédex is an electronic device designed to catalogue and provide information regarding the
                    various species of Pokémon.</li>
                </ul>

              </div>
            </div>
          </div>

        </div>
      </main>
    </body>

    </html>

package br.senai.lab365.pokedex.controllers;

import br.senai.lab365.pokedex.dto.PokemonCapturadoRequest;
import br.senai.lab365.pokedex.dto.PokemonResponse;
import br.senai.lab365.pokedex.dto.PokemonSummary;
import br.senai.lab365.pokedex.dto.PokemonVistoRequest;
import br.senai.lab365.pokedex.services.PokemonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService pokemonService) {
        this.service = pokemonService;
    }

    @PostMapping("/visto")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraVisto(@Valid @RequestBody PokemonVistoRequest pokemonVistoRequest) {
        service.cadastraVisto(pokemonVistoRequest);
    }

    @PutMapping("/capturado")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void atualizaCapturado(
            @Valid @RequestBody PokemonCapturadoRequest pokemonCapturadoRequest) {
        service.atualizaCapturado(pokemonCapturadoRequest);
    }

    @PutMapping("/visto")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void atualizaVisto(@Valid @RequestBody PokemonVistoRequest pokemonVistoRequest) {
        service.atualizaVisto(pokemonVistoRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable(name = "id") Integer numero) {
        service.exclui(numero);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PokemonResponse busca(@PathVariable(name = "id") Integer numero) {
        return service.busca(numero);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonSummary> lista() {
        return service.lista();
    }
}





package br.senai.lab365.pokedex.services;

import br.senai.lab365.pokedex.dto.PokemonCapturadoRequest;
import br.senai.lab365.pokedex.dto.PokemonVistoRequest;
import br.senai.lab365.pokedex.models.Pokemon;
import br.senai.lab365.pokedex.repositories.PokemonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import static br.senai.lab365.pokedex.mappers.PokemonMapper.map;


@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.repository = pokemonRepository;
    }

    public void cadastraVisto(PokemonVistoRequest pokemonVistoRequest) {

        if (repository.existsById(pokemonVistoRequest.getNumero())) {
            throw new DuplicateKeyException("já existe um pokemon com esse número");
        }
        repository.save(map(pokemonVistoRequest));
    }

    public void atualizaCapturado(PokemonCapturadoRequest pokemonCapturadoRequest) {

        if (!repository.existsById(pokemonCapturadoRequest.getNumero())) {
            throw new EntityNotFoundException();
        }
        repository.save(map(pokemonCapturadoRequest));
    }

    public void atualizaVisto(PokemonVistoRequest pokemonVistoRequest) {
        Pokemon pokemon =
                repository
                        .findById(pokemonVistoRequest.getNumero())
                        .orElseThrow(EntityNotFoundException::new);

        pokemon.setNome(pokemonVistoRequest.getNome());
        pokemon.setImagemUrl(pokemonVistoRequest.getImagemUrl());
        pokemon.setHabitat(pokemonVistoRequest.getHabitat());

        repository.save(pokemon);
    }


}

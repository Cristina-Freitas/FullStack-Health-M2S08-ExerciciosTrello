package br.senai.lab365.pokedex.services;

import br.senai.lab365.pokedex.dto.PokemonCapturadoRequest;
import br.senai.lab365.pokedex.dto.PokemonResponse;
import br.senai.lab365.pokedex.dto.PokemonSummary;
import br.senai.lab365.pokedex.dto.PokemonVistoRequest;
import br.senai.lab365.pokedex.models.Pokemon;
import br.senai.lab365.pokedex.repositories.PokemonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;
import static br.senai.lab365.pokedex.mappers.PokemonMapper.map;
import static br.senai.lab365.pokedex.mappers.PokemonMapper.mapToSummary;


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

    public void exclui(Integer numero) {
        if (repository.existsById(numero)) {
            repository.deleteById(numero);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public PokemonResponse busca(Integer numero) {
        Pokemon pokemon = repository.findById(numero).orElseThrow(EntityNotFoundException::new);

        return map(pokemon);
    }

    public List<PokemonSummary> lista() {
        return mapToSummary(repository.findAll());
    }
}



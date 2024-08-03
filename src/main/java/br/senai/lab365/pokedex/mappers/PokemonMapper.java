package br.senai.lab365.pokedex.mappers;

import br.senai.lab365.pokedex.dto.PokemonCapturadoRequest;
import br.senai.lab365.pokedex.dto.PokemonResponse;
import br.senai.lab365.pokedex.dto.PokemonVistoRequest;
import br.senai.lab365.pokedex.models.Pokemon;

public class PokemonMapper {
    private PokemonMapper() {
    }

    public static Pokemon map(PokemonVistoRequest source) {
        if (source == null) return null;

        Pokemon target = new Pokemon();

        target.setNumero(source.getNumero());
        target.setNome(source.getNome());
        target.setImagemUrl(source.getImagemUrl());
        target.setHabitat(source.getHabitat());

        return target;
    }

    public static Pokemon map(PokemonCapturadoRequest source) {
        if (source == null) return null;

        Pokemon target = new Pokemon();

        target.setNumero(source.getNumero());
        target.setNome(source.getNome());
        target.setDescricao(source.getDescricao());
        target.setImagemUrl(source.getImagemUrl());
        target.setTipo(source.getTipo());
        target.setCategoria(source.getCategoria());
        target.setHabitat(source.getHabitat());
        target.setAltura(source.getAltura());
        target.setPeso(source.getPeso());
        target.setCapturado(true);

        return target;
    }

    public static PokemonResponse map(Pokemon source) {
        if (source == null) return null;

        PokemonResponse target = new PokemonResponse();

        target.setNumero(source.getNumero());
        target.setNome(source.getNome());
        target.setDescricao(source.getDescricao());
        target.setImagemUrl(source.getImagemUrl());
        target.setTipo(source.getTipo());
        target.setCategoria(source.getCategoria());
        target.setHabitat(source.getHabitat());
        target.setAltura(source.getAltura());
        target.setPeso(source.getPeso());
        target.setCapturado(source.isCapturado());

        return target;
    }

}

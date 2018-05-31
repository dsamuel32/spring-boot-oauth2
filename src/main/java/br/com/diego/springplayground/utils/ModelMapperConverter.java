package br.com.diego.springplayground.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class ModelMapperConverter {

    private ModelMapper mapper = new ModelMapper();

    public <D> D converter(Object source, Class<D> target){
        D retorno = mapper.map(source, target);

        return retorno;
    }

    public <D> D converterStrict(Object source, Class<D> target){

        if (source == null){
            return null;
        }

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        D retorno = mapper.map(source, target);

        return retorno;
    }

    public <D> D converterStrict(Object source, Type target){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return  mapper.map(source, target);

    }

    public <D> D converterLazyLoading(Object source, Class<D> target){
        return mapper.map(source, target);
    }

    public <D> D converterStrictLazyLoading(Object source, Class<D> target){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        D retorno = mapper.map(source, target);

        return retorno;
    }

    public <D> D converterListaStrictLazyLoading(Object source, Type target) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(source, target);
    }

}

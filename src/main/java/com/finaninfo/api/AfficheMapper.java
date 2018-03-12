package com.finaninfo.api;


import com.finaninfo.model.Affiche;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AfficheMapper {
    AfficheMapper INSTANCE = Mappers.getMapper(AfficheMapper.class);

    AfficheDTO AfficheToAfficheDTO(Affiche affiche);
}

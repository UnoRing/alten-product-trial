package com.james.alten_shop.mappers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.NonNull;

@Component
@Slf4j
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    /**
     * Maps from DTO to Entity requires an Entity instance.
     * @param dto source
     * @param defaultValue target
     */
    public void map(@NonNull final Object dto, Object defaultValue) {
        this.mapper.map(dto, defaultValue);
    }

    /**
     * Maps from DTO to Entity requires Entity class.
     * @param dto source
     * @param destinationType target class
     */
    public <D> D map(@NonNull final Object dto, Class<D> destinationType) {
        return this.mapper.map(dto, destinationType);
    }
}

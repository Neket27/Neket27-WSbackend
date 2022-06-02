package utils;

import model.dtos.DtoEntity;
import org.modelmapper.ModelMapper;

public class DtoUtils {

    public DtoEntity convertToDto(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    //создаёт объект по параметрам из контроллера
    public Object convertToEntity(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }


}

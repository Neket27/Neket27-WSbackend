package whitesoftapp.whitesoftapp.utils;

import org.modelmapper.ModelMapper;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;


public class DtoUtils {

    public DtoEntity convertToDto(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    //создаёт объект по параметрам из контроллера
    public Object convertToEntity(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }


}

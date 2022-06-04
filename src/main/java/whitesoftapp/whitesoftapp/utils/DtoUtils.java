package whitesoftapp.whitesoftapp.utils;

import org.modelmapper.ModelMapper;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;
import java.util.List;
import java.util.stream.Collectors;

public class DtoUtils {

    public DtoEntity convertToDto(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public Object convertToEntity(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }

    public <S, T> List<DtoEntity> convertListToDto(List<S> source, Class<T> targetClass) {
        List<T> EmployeeDto = source
                .stream()
                .map(element -> new ModelMapper().map(element, targetClass))
                .collect(Collectors.toList());
        return (List<DtoEntity>) EmployeeDto;
    }

}

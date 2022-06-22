package whitesoftapp.whitesoftapp.controller.utils.mapper.contacts;

import org.mapstruct.Mapper;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.dtos.contacts.ContactsDto;

@Mapper(componentModel = "spring")
public interface ContactsMapper {

    ContactsDto toDto(Contacts contacts);

    Contacts toEntity(ContactsDto contactsDto);
}
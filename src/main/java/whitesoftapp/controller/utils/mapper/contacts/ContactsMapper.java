package whitesoftapp.controller.utils.mapper.contacts;

import org.mapstruct.Mapper;
import whitesoftapp.arguments.CreateContactsArgument;
import whitesoftapp.model.Contacts;
import whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.model.dtos.contacts.CreateContactsDto;

@Mapper(componentModel = "spring")
public interface ContactsMapper {

    ContactsDto toDto(Contacts contacts);

    Contacts toEntity(ContactsDto contactsDto);

    CreateContactsArgument convertToCreateContactsArgument(CreateContactsDto createPostDto);

    Contacts toEntityFromCreateContactsArgument(CreateContactsArgument createContactsArgument);
}
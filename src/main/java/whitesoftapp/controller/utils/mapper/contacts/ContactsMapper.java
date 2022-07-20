package whitesoftapp.controller.utils.mapper.contacts;

import whitesoftapp.arguments.CreateContactsArgument;
import whitesoftapp.model.Contacts;
import whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.model.dtos.contacts.CreateContactsDto;

public interface ContactsMapper {

    ContactsDto toDto(Contacts contacts);

    Contacts toEntity(ContactsDto contactsDto);

    CreateContactsArgument toCreateContactsArgument(CreateContactsDto createPostDto);

    Contacts toEntityFromCreateContactsArgument(CreateContactsArgument createContactsArgument);
}
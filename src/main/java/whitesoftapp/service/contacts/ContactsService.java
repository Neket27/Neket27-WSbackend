package whitesoftapp.service.contacts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.arguments.CreateContactsArgument;
import whitesoftapp.controller.utils.mapper.contacts.ContactsMapper;
import whitesoftapp.model.Contacts;
import whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.repository.InMemoryContacts;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContactsService {

    private final InMemoryContacts inMemoryContacts;
    private final ContactsMapper contactsMapper;

    public Contacts getById(UUID id) {
        return inMemoryContacts.get(id);
    }

    public Contacts create(UUID id, CreateContactsArgument createContactsArgument) {
        Contacts contacts = contactsMapper.toEntityFromCreateContactsArgument(createContactsArgument);
        inMemoryContacts.put(id, contacts);
        return contacts;
    }
}

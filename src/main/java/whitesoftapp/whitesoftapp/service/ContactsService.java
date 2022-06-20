package whitesoftapp.whitesoftapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.whitesoftapp.repository.InMemoryContacts;
import whitesoftapp.whitesoftapp.utils.mapper.ContactsMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContactsService {

    private final InMemoryContacts inMemoryContacts;
    private final ContactsMapper contactsMapper;

    public ContactsDto getById(UUID id) {
        return contactsMapper.toDto(inMemoryContacts.get(id));
    }

    public ContactsDto create(UUID id, ContactsDto contactsDto) {
        Contacts contacts=contactsMapper.toEntity(contactsDto);
        inMemoryContacts.put(id,contacts);
        return contactsMapper.toDto(contacts);
    }
}

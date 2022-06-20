package whitesoftapp.whitesoftapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.dtos.contacts.ContactsDto;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class InMemoryContacts {

    private final Map<UUID, Contacts> contacts;

    public void add(UUID id, Contacts contacts) {
        this.contacts.put(id, contacts);
    }

    public Contacts get(UUID id) {
        return this.contacts.get(id);
    }

    public void put(UUID id, Contacts contacts) {
        this.contacts.put(id,contacts);
    }
}

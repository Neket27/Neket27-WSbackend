package whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.arguments.CreateContactsArgument;
import whitesoftapp.controller.utils.mapper.contacts.ContactsMapper;
import whitesoftapp.model.dtos.contacts.CreateContactsDto;

@RequiredArgsConstructor
@Component
public class CreateContactsArgumentAction {

    private final ContactsMapper contactsMapper;

    public CreateContactsArgument create(CreateContactsDto  createPostDto){
        return contactsMapper.convertToCreateContactsArgument(createPostDto);
    }

}

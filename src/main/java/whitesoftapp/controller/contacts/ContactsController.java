package whitesoftapp.controller.contacts;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.action.CreateContactsArgumentAction;
import whitesoftapp.action.CreatePostArgumentAction;
import whitesoftapp.arguments.CreateContactsArgument;
import whitesoftapp.arguments.CreatePostArgument;
import whitesoftapp.controller.utils.mapper.contacts.ContactsMapper;
import whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.model.dtos.contacts.CreateContactsDto;
import whitesoftapp.model.dtos.post.CreatePostDto;
import whitesoftapp.service.contacts.ContactsService;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private final ContactsService contactsService;
    private final CreateContactsArgumentAction createContactsArgumentAction;
    private final ContactsMapper contactsMapper;

    @PostMapping("/add/{id}")
    public ContactsDto create(@RequestParam UUID id, @Valid @RequestBody CreateContactsDto createContactsDto) {
        return contactsMapper.toDto(contactsService.create(id,createContactsArgumentAction.create(createContactsDto)));
    }

    @GetMapping("/getById")
    public ContactsDto getById(@PathVariable UUID id) {
        return contactsMapper.toDto(contactsService.getById(id));
    }

}

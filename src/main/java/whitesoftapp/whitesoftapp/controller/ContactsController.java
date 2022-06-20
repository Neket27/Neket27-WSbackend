package whitesoftapp.whitesoftapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.whitesoftapp.service.ContactsService;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private final ContactsService contactsService;

    @PostMapping("/add/{id}")
    public ContactsDto create(@RequestParam UUID id, @Valid @RequestBody ContactsDto contactsDto) {
        return contactsService.create(id, contactsDto);
    }

    @GetMapping("/getById")
    public ContactsDto getById(@PathVariable UUID id) {
        return contactsService.getById(id);
    }

}

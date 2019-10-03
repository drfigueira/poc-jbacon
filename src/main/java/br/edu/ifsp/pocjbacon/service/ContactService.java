package br.edu.ifsp.pocjbacon.service;

import br.edu.ifsp.pocjbacon.entity.Contact;
import br.edu.ifsp.pocjbacon.exception.ResourceNotFoundException;
import br.edu.ifsp.pocjbacon.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(final ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void save(final Contact contact) {
        contactRepository.save(contact);
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact findById(final Long id) {
        return contactRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteById(final Long id) {
        contactRepository.deleteById(id);
    }
}

package br.edu.ifsp.pocjbacon.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.edu.ifsp.pocjbacon.entity.Contact;
import br.edu.ifsp.pocjbacon.repository.ContactRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class ContactFactory extends JBacon<Contact> {

    private final ContactRepository contactRepository;

    private final Faker faker;

    public ContactFactory(final ContactRepository contactRepository,
                          final Faker faker) {
        this.contactRepository = contactRepository;
        this.faker = faker;
    }

    @Override
    protected Contact getDefault() {
        return Contact.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .build();
    }

    @Override
    protected Contact getEmpty() {
        return new Contact();
    }

    @Override
    protected void persist(final Contact contact) {
        contactRepository.save(contact);
    }
}

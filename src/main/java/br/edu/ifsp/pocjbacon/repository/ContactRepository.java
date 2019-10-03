package br.edu.ifsp.pocjbacon.repository;

import br.edu.ifsp.pocjbacon.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

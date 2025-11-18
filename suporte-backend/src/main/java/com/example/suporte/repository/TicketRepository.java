package com.example.suporte.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.suporte.domain.*;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(Status status);

    Page<Ticket> findByCriadorId(Long criadorId, Pageable pageable); // NOVO
}

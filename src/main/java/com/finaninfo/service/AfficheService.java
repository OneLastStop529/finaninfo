package com.finaninfo.service;

import com.finaninfo.model.Affiche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AfficheService {
    Page<Affiche> findAfficheByPage(Pageable pageable);
    Iterable<Affiche> findAllAffiche();
    Affiche findOne(Long id);
    void updateAffiche();
    void deleteAffiche();



}

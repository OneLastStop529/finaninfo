package com.finaninfo.service;

import com.finaninfo.api.AfficheDTO;
import com.finaninfo.model.Affiche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AfficheService {
    Page<Affiche> findAfficheByPage(Pageable pageable);
    Iterable<Affiche> findAllAffiche();
    Affiche findOne(Long id);
    void updateAffiche();
    void deleteAffiche();
    List<AfficheDTO> getAllAffiches();
    AfficheDTO getAfficheByTitleName(String title);
    AfficheDTO createAffiche();

}

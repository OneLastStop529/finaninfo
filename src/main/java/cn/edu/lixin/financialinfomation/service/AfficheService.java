package cn.edu.lixin.financialinfomation.service;

import cn.edu.lixin.financialinfomation.model.Affiche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AfficheService {
    Page<Affiche> findAfficheByPage(Pageable pageable);
    Affiche findOne(Long id);
    void updateAffiche();
    void deleteAffiche();



}

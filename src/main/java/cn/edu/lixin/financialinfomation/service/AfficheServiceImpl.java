package cn.edu.lixin.financialinfomation.service;

import cn.edu.lixin.financialinfomation.model.Affiche;
import cn.edu.lixin.financialinfomation.repository.AfficheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AfficheServiceImpl implements AfficheService{
    @Autowired
    private AfficheRepository afficheRepository;

    @Override
    public Page<Affiche> findAfficheByPage(Pageable pageable){

        return afficheRepository.findAll(pageable);
    }

    @Override
    public Affiche findOne(Long id) {
        return afficheRepository.findOne(id);
    }

    @Override
    public void updateAffiche() {

    }

    @Override
    public void deleteAffiche() {

    }
}

package com.finaninfo.service;

import com.finaninfo.api.AfficheDTO;
import com.finaninfo.api.AfficheMapper;
import com.finaninfo.model.Affiche;
import com.finaninfo.repository.AfficheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AfficheServiceImpl implements AfficheService{
    @Autowired
    private  AfficheRepository afficheRepository;

    @Autowired
    private  AfficheMapper afficheMapper;


    @Override
    public Page<Affiche> findAfficheByPage(Pageable pageable){

        return afficheRepository.findAll(pageable);
    }

    @Override
    public Iterable<Affiche> findAllAffiche() {
        return afficheRepository.findAll();
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

    @Override
    public List<AfficheDTO> getAllAffiches() {
        return afficheRepository.findAll()
            .stream()
            .map(afficheMapper::AfficheToAfficheDTO)
            .collect(Collectors.toList());

    }

    @Override
    public AfficheDTO getAfficheByTitleName(String title) {
        return null;
    }

    @Override
    public AfficheDTO createAffiche() {
        return null;
    }
}

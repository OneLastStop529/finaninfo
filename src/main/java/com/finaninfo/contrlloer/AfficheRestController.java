package com.finaninfo.contrlloer;

import com.finaninfo.api.AfficheDTO;
import com.finaninfo.api.AfficheListDTO;
import com.finaninfo.service.AfficheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/api/v1/affiche")
@Controller
public class AfficheRestController {
    private AfficheService afficheService;

    public AfficheRestController(AfficheService afficheService) {
        this.afficheService = afficheService;
    }

    @GetMapping
    public ResponseEntity<AfficheListDTO> getAllAffiche(){
        return   new ResponseEntity<AfficheListDTO>(new AfficheListDTO(afficheService.getAllAffiches()),HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<AfficheDTO> getAfficheByName(@PathVariable String title){
     return  new ResponseEntity<AfficheDTO>(afficheService.getAfficheByTitleName(title),HttpStatus.OK);
    }
}

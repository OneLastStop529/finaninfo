package com.finaninfo.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AfficheListDTO {
    List<AfficheDTO> affiches;
}

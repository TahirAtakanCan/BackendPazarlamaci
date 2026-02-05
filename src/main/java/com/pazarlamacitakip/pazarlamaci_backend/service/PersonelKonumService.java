package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.KonumSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.entity.PersonelKonum;
import com.pazarlamacitakip.pazarlamaci_backend.repository.PersonelKonumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PersonelKonumService {

    private final PersonelKonumRepository repository;

    public void saveKonum(KonumSaveRequest request) {
        PersonelKonum konum = new PersonelKonum();
        konum.setPersonelId(request.getPersonelId());
        konum.setFirstName(request.getFirstName());
        konum.setLatitude(request.getLatitude());
        konum.setLongitude(request.getLongitude());
        konum.setTimestamp(LocalDateTime.now());
        
        repository.save(konum);
    }
}

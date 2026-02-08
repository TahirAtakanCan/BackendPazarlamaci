package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.PersonelKonum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface PersonelKonumRepository extends JpaRepository<PersonelKonum, UUID> {
    // Son konumları getirmek için tarihe göre sıralı liste gerekebilir
    List<PersonelKonum> findByPersonelIdOrderByTimestampDesc(UUID personelId);

    // Her personelin en son konumunu getir
    @Query("SELECT pk FROM PersonelKonum pk WHERE pk.timestamp = " +
           "(SELECT MAX(pk2.timestamp) FROM PersonelKonum pk2 WHERE pk2.personelId = pk.personelId)")
    List<PersonelKonum> findLatestKonumForEachPersonel();
}

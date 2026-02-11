package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.FirmaWithYoneticiRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.FirmaResponse;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.FirmaWithYoneticiResponse;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.UserResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Firma;
import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.entity.UserRole;
import com.pazarlamacitakip.pazarlamaci_backend.repository.FirmaRepository;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import com.pazarlamacitakip.pazarlamaci_backend.repository.YetkiliRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final FirmaRepository firmaRepository;
    private final UserRepository userRepository;
    private final YetkiliRepository yetkiliRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Şirket + Yönetici birlikte oluşturur.
     * Firma ve User aynı sirketkodu ile ilişkilendirilir.
     */
    @Transactional
    public FirmaWithYoneticiResponse createFirmaWithYonetici(FirmaWithYoneticiRequest request) {
        // Sirketkodu: Her zaman yeni UUID üret (benzersiz izolasyon)
        String sirketkodu = UUID.randomUUID().toString();

        // Firma oluştur
        Firma firma = new Firma();
        firma.setAdi(request.getFirmaAdi());
        firma.setAdres(request.getFirmaAdres());
        firma.setTelefon(request.getFirmaTelefon());
        firma.setEmail(request.getFirmaEmail());
        firma.setSektor(request.getFirmaSektor());
        firma.setSehir(request.getFirmaSehir());
        firma.setIlce(request.getFirmaIlce());
        firma.setSirketkodu(sirketkodu);
        firma.setAktifmi(true);
        firma.setBakiye(0.0);
        
        Firma savedFirma = firmaRepository.save(firma);

        // Yönetici (User) oluştur - aynı sirketkodu ile
        User user = new User();
        user.setAdi(request.getYoneticiAdi());
        user.setEmail(request.getYoneticiEmail());
        user.setSifre(passwordEncoder.encode(request.getYoneticiSifre()));
        user.setTelefon(request.getYoneticiTelefon());
        user.setSirketkodu(sirketkodu);
        user.setRole(UserRole.ADMIN);
        user.setAktifmi(true);
        
        User savedUser = userRepository.save(user);

        // Response oluştur
        FirmaResponse firmaResponse = FirmaResponse.builder()
                .id(savedFirma.getId())
                .adi(savedFirma.getAdi())
                .adres(savedFirma.getAdres())
                .telefon(savedFirma.getTelefon())
                .email(savedFirma.getEmail())
                .sektor(savedFirma.getSektor())
                .sehir(savedFirma.getSehir())
                .ilce(savedFirma.getIlce())
                .bakiye(savedFirma.getBakiye())
                .aciklama(savedFirma.getAciklama())
                .aktifmi(savedFirma.getAktifmi())
                .sirketkodu(savedFirma.getSirketkodu())
                .build();

        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setAdi(savedUser.getAdi());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setTelefon(savedUser.getTelefon());
        userResponse.setRole(savedUser.getRole().name());
        userResponse.setAktifmi(savedUser.getAktifmi());
        userResponse.setSirketkodu(savedUser.getSirketkodu());

        return FirmaWithYoneticiResponse.builder()
                .firma(firmaResponse)
                .yonetici(userResponse)
                .build();
    }

    /**
     * Şirketi ve bağlı yetkilileri kalıcı olarak siler.
     */
    @Transactional
    public void deleteFirmaHard(UUID firmaId) {
        Firma firma = firmaRepository.findById(firmaId)
                .orElseThrow(() -> new RuntimeException("Firma bulunamadı: " + firmaId));
        // Önce firmaya bağlı yetkilileri sil
        yetkiliRepository.deleteAll(yetkiliRepository.findByFirmaId(firmaId));
        firmaRepository.delete(firma);
    }

    /**
     * Yönetici/Kullanıcıyı kalıcı olarak siler.
     */
    public void deleteUserHard(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + userId));
        userRepository.delete(user);
    }
}

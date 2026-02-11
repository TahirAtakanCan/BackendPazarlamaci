package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.UserSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.UserResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.entity.UserRole;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Repository'yi otomatik inject eder (Constructor Injection)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Tüm kullanıcıları getir
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Şirket koduna göre kullanıcıları getir (Yönetici için)
    public List<UserResponse> getUsersBySirketkodu(String sirketkodu) {
        return userRepository.findBySirketkodu(sirketkodu).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ID'ye göre kullanıcı getir
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
        return mapToResponse(user);
    }

    // Kullanıcı Kaydet (Genel amaçlı - Admin kendi personelini güncellerken kullanılır)
    public UserResponse createUser(UserSaveRequest request) {
        User user = new User();
        user.setAdi(request.getAdi());
        user.setEmail(request.getEmail());
        user.setSifre(passwordEncoder.encode(request.getSifre()));
        user.setTelefon(request.getTelefon());
        user.setBolge(request.getBolge());
        user.setSirketkodu(request.getSirketkodu());
        user.setRole(UserRole.PERSONEL); // Varsayılan olarak personel
        user.setAktifmi(true);

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    // Yardımcı Metot: Entity -> Response DTO Çevirici
    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setAdi(user.getAdi());
        response.setEmail(user.getEmail());
        response.setTelefon(user.getTelefon());
        response.setRole(user.getRole().name());
        response.setBolge(user.getBolge());
        response.setAktifmi(user.getAktifmi());
        response.setSirketkodu(user.getSirketkodu());
        response.setYer(user.getYer());
        response.setPlaka(user.getPlaka());
        return response;
    }

    // Kullanıcı Sil
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + id));
        userRepository.delete(user);
    }

    // Kullanıcı Aktif/Pasif Toggle
    public UserResponse toggleActive(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + id));
        user.setAktifmi(!Boolean.TRUE.equals(user.getAktifmi()));
        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }
}
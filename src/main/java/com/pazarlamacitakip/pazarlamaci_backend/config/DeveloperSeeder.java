package com.pazarlamacitakip.pazarlamaci_backend.config;

import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.entity.UserRole;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Uygulama başlatıldığında veritabanında DEVELOPER kullanıcı yoksa otomatik oluşturur.
 * Bu, sistemin ilk kullanıcısıdır ve tüm yetkilere sahiptir.
 * 
 * Varsayılan Giriş:
 *   Email: developer@pazarlamaci.com
 *   Şifre: Developer123!
 *   Şirket Kodu: MASTER
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DeveloperSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String DEVELOPER_EMAIL = "developer@pazarlamaci.com";
    private static final String DEVELOPER_PASSWORD = "Developer123!";
    private static final String DEVELOPER_NAME = "Developer";
    private static final String DEVELOPER_SIRKETKODU = "MASTER";

    @Override
    public void run(String... args) {
        // Veritabanında hiç DEVELOPER kullanıcı var mı kontrol et
        boolean developerExists = userRepository.findByEmail(DEVELOPER_EMAIL).isPresent();

        if (!developerExists) {
            User developer = new User();
            developer.setAdi(DEVELOPER_NAME);
            developer.setEmail(DEVELOPER_EMAIL);
            developer.setSifre(passwordEncoder.encode(DEVELOPER_PASSWORD));
            developer.setRole(UserRole.DEVELOPER);
            developer.setSirketkodu(DEVELOPER_SIRKETKODU);
            developer.setAktifmi(true);

            userRepository.save(developer);
            log.info("========================================");
            log.info("DEVELOPER kullanıcı oluşturuldu!");
            log.info("Email: {}", DEVELOPER_EMAIL);
            log.info("Şifre: {}", DEVELOPER_PASSWORD);
            log.info("Şirket Kodu: {}", DEVELOPER_SIRKETKODU);
            log.info("========================================");
        } else {
            log.info("Developer kullanıcı zaten mevcut. Seed atlandı.");
        }
    }
}

package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "license_number", length = 11, nullable = false, unique = true)
    private String licenseNumber;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Driver() {
    }

    public Driver(String name, String cpf, String licenseNumber) {
        this.name = name;
        this.cpf = cpf;
        this.licenseNumber = licenseNumber;
    }

    private void validateName() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do motorista é obrigatório");
        }
    }

    private void validateCPF() {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }

        String tempCPF = cpf;
        tempCPF = tempCPF.replaceAll("\\D", "");

        if (tempCPF.length() != 11 || tempCPF.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido");
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (tempCPF.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        if (primeiroDigito != (tempCPF.charAt(9) - '0')) {
            throw new IllegalArgumentException("CPF inválido");
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (tempCPF.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        if (segundoDigito != (tempCPF.charAt(10) - '0')) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private void validateLicenseNumber() {
        if (licenseNumber == null || !licenseNumber.matches("\\d{11}") || licenseNumber.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CNH inválida");
        }

        int d1 = 0, d2 = 0;
        int[] cnhArray = new int[11];

        for (int i = 0; i < 11; i++) {
            cnhArray[i] = licenseNumber.charAt(i) - '0';
        }

        for (int i = 0, j = 9; i < 9; i++, j--) {
            d1 += cnhArray[i] * j;
        }

        d1 = d1 % 11;
        if (d1 >= 10) d1 = 0;

        for (int i = 0, j = 1; i < 9; i++, j++) {
            d2 += cnhArray[i] * j;
        }

        d2 = d2 % 11;
        if (d2 >= 10) d2 = 0;

        if ((cnhArray[9] == 0 && cnhArray[10] == 0) && (d1 == 10 || d2 == 10)) {
            d1 = 0;
            d2 = 0;
        }

        if (d1 != cnhArray[9] || d2 != cnhArray[10]) {
            throw new IllegalArgumentException("CNH inválida");
        }
    }

    public void validate() {
        validateName();
        validateCPF();
        validateLicenseNumber();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format(
                "Driver{id=%d, name=%s, licenseNumber=%s, createdAt=%s, updatedAt=%s}",
                id, name, licenseNumber, createdAt, updatedAt
        );
    }
}

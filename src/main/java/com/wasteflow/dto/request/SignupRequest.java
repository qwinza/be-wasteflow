package com.wasteflow.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
    @NotBlank
    private String nama;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String alamat;
    
    private String role; // optional, default is WARGA usually

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

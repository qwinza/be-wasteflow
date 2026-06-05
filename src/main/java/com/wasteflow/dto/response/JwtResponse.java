package com.wasteflow.dto.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String nama;
    private String role;
    private Long locationId;
    private String locationName;

    public JwtResponse(String accessToken, Long id, String nama, String email, String role) {
        this.token = accessToken;
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.role = role;
    }

    public JwtResponse(String accessToken, Long id, String nama, String email, String role, Long locationId, String locationName) {
        this.token = accessToken;
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.role = role;
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
}

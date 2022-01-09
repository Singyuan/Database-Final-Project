package com.lwdevelop.backend.payload.response;

public class JwtResponse {
    private int status;
    private String message;
    private Jwt jwt;

    public static class Jwt {
        private String token;
        private String type = "Bearer";
        private Long id;

        public Jwt(String accessToken, Long id) {
            this.token = accessToken;
            this.id = id;
        }

        public String getAccessToken() {
            return token;
        }

        public void setAccessToken(String accessToken) {
            this.token = accessToken;
        }

        public String getTokenType() {
            return type;
        }

        public void setTokenType(String tokenType) {
            this.type = tokenType;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
    public JwtResponse(int status, String message, Jwt jwt) {
        this.status = status;
        this.message = message;
        this.jwt = jwt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Jwt getUser() {
        return jwt;
    }

    public void setUser(Jwt jwt) {
        this.jwt = jwt;
    }

}

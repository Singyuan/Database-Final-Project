package com.lwdevelop.backend.payload.response;

import java.util.List;

public class UserResponse {
    private int status;
    private String message;
    private User user;

    public static class User {
        private Long id;
        private String name;
        private List<String> type;
        private String email;
        private String phone;

        public User(Long id, String name, List<String> type, String email, String phone) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.email = email;
            this.phone = phone;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public UserResponse(int status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
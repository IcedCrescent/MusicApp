package com.example.trungspc.module3_musicapp;

public class Register {
    public class RegisterRequest {
        private String username;
        private String password;
        private String role;

        public RegisterRequest(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public class RegisterResponse {
        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

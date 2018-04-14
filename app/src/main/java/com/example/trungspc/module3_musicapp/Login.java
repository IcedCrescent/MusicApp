package com.example.trungspc.module3_musicapp;

public class Login {
    public class LoginRequest {
        private String username;
        private String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public class LoginResponse {
        private boolean success;
        private String token;
        private String userID;
        private String role;

        public LoginResponse(boolean success, String token, String userID, String role) {
            this.success = success;
            this.token = token;
            this.userID = userID;
            this.role = role;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getToken() {
            return token;
        }

        public String getUserID() {
            return userID;
        }

        public String getRole() {
            return role;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return String.format("Success: %b\nToken: %s\nUserID: %s\nRole: %s", success, token, userID, role);
        }
    }

}

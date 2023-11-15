package com.example.demo.models;

public record User(
        Long Id,
        String Name,
        String Email,
        String HashPass,
        Role role
) {
    public class UserBuilder{
        public UserBuilder(){
//            this.Id = null;
            // TODO: автоинкремент айдишника
            this.role = Role.USER;
        }
        Long Id;
        String Name;
        String Email;
        String HashPass;
        Role role;

        public void setName(String name) {
            Name = name;
        }
        public void setEmail(String email) {
            Email = email;
        }
        public void setHashPass(String hashPass) {
            HashPass = hashPass;
        }
        public void setRole(Role role) {
            this.role = role;
        }

        public User build(){
            return new User(this.Id, this.Name, this.Email, this.HashPass, this.role);
        }


    }
    public enum Role{
        USER, ADMIN
    }
}

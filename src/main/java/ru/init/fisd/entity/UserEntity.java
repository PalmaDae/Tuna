    package ru.init.fisd.entity;

    public class UserEntity {
        private Long id;
        private String name;
        private String hashPass;
        private String role;

        public UserEntity(String name, String hashPass, String role) {
            this.name = name;
            this.hashPass = hashPass;
            this.role = role;
        }

        public UserEntity(Long id, String name, String hashPass, String role) {
            this.id = id;
            this.name = name;
            this.hashPass = hashPass;
            this.role = role;
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

        public String getHashPass() {
            return hashPass;
        }

        public String getRole() {
            return role;
        }
    }

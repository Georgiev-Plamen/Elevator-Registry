INSERT INTO roles VALUES (1, 'USER'), (2, 'ADMIN');

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `username`)
VALUES
    (1,'plamen_gg@mail.bg','Plamen','Georgiev','ad3d31544e41586c8448d9cc06c632d8fd59ec7b3b69a2aa6290d52689583ea1964098251eb2f762d19811e3c1008cd2', 'plamen');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2);
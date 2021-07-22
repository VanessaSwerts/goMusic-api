INSERT INTO USERS(name, email, password, avatar, country) VALUES('User Default', 'user@email.com', '$2a$10$oEfUNPSGE6XTFG3YQ3/C3uQX6YQlRtBFLSifQjNH4l0ZqltWGhMqW', 'default-avatar.png', 'Brasil');
INSERT INTO USERS(name, email, password, avatar, country) VALUES('Ana Silva', 'anasilva@email.com', '$2a$10$oEfUNPSGE6XTFG3YQ3/C3uQX6YQlRtBFLSifQjNH4l0ZqltWGhMqW', 'default-avatar.png', 'Brasil');
INSERT INTO USERS(name, email, password, avatar, country) VALUES('José Oliveira', 'joseoliveira@email.com', '$2a$10$oEfUNPSGE6XTFG3YQ3/C3uQX6YQlRtBFLSifQjNH4l0ZqltWGhMqW', 'default-avatar.png', 'Brasil');

INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('My first playlist', '', 'default-playlist.png', 1);
INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('My favorites', 'My favs songs of 2021', 'default-playlist.png', 2);
INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('Dance songs', '', 'default-playlist.png', 2);
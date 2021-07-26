INSERT INTO USERS(name, email, password, avatar, country) VALUES('User Default', 'user@email.com', '$2a$10$oEfUNPSGE6XTFG3YQ3/C3uQX6YQlRtBFLSifQjNH4l0ZqltWGhMqW', 'goMusic/goMusic-dev/user/default-avatar.png', 'Brasil');
INSERT INTO USERS(name, email, password, avatar, country) VALUES('Ana Silva', 'anasilva@email.com', '$2a$10$oEfUNPSGE6XTFG3YQ3/C3uQX6YQlRtBFLSifQjNH4l0ZqltWGhMqW', 'goMusic/goMusic-dev/user/ana-avatar.png', 'Brasil');
INSERT INTO USERS(name, email, password, avatar, country) VALUES('José Oliveira', 'joseoliveira@email.com', '$2a$10$oEfUNPSGE6XTFG3YQ3/C3uQX6YQlRtBFLSifQjNH4l0ZqltWGhMqW', 'goMusic/goMusic-dev/user/jose-avatar.png', 'Brasil');
INSERT INTO USERS(name, email, password, avatar, country) VALUES('Gustavo dos Santos', 'gustavodossantos@email.com', '$2a$10$oEfUNPSGE6XTFG3YQ3/C3uQX6YQlRtBFLSifQjNH4l0ZqltWGhMqW', 'goMusic/goMusic-dev/user/gustavo-avatar.png', 'Brasil');

INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('My first playlist', '', 'goMusic/goMusic-dev/playlist/default-playlist.png', 1);
INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('My favorites', 'My favs songs of 2021', 'goMusic/goMusic-dev/playlist/default-playlist.png', 2);
INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('Dance songs', '', 'goMusic/goMusic-dev/playlist/default-playlist.png', 2);
INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('Happy Hour', 'Best songs to a happy hour', 'goMusic/goMusic-dev/playlist/default-playlist.png', 3);
INSERT INTO PLAYLIST(title, description, avatar, owner_id) VALUES('Love Ballads', 'Some of the worlds most beautiful love songs', 'goMusic/goMusic-dev/playlist/default-playlist.png', 4);

INSERT INTO FOLLOW (follower_id, following_id) VALUES (1, 2);
INSERT INTO FOLLOW (follower_id, following_id) VALUES (1, 3);
INSERT INTO FOLLOW (follower_id, following_id) VALUES (3, 1);
INSERT INTO FOLLOW (follower_id, following_id) VALUES (3, 4);
INSERT INTO FOLLOW (follower_id, following_id) VALUES (4, 2);
INSERT INTO FOLLOW (follower_id, following_id) VALUES (4, 3);

INSERT INTO LIKES (playlist_id, user_id) VALUES (2, 1);
INSERT INTO LIKES (playlist_id, user_id) VALUES (4, 1);
INSERT INTO LIKES (playlist_id, user_id) VALUES (1, 3);
INSERT INTO LIKES (playlist_id, user_id) VALUES (2, 4);
INSERT INTO LIKES (playlist_id, user_id) VALUES (3, 4);







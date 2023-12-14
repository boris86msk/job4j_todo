ALTER TABLE tasks ADD user_id int;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES todo_user(id);

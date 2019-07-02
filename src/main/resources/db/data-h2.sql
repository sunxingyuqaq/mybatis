DELETE FROM user;

INSERT INTO user (id, name, age, email ,deleted,enable) VALUES
(1, 'Jone', 18, 'test1@baomidou.com',1,1),
(2, 'Jack', 20, 'test2@baomidou.com',1,0),
(3, 'Tom', 28, 'test3@baomidou.com',1,1),
(4, 'Sandy', 24, 'test4@baomidou.com',0,1),
(5, 'Billie', 24, 'test5@baomidou.com',0,0);
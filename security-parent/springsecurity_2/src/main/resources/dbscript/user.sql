create user 'admin'@'%' identified by 'admin';
create user 'admin'@'localhost' identified by 'admin';
grant all privileges on *.* to 'admin'@'%' with grant option;
grant all privileges on *.* to 'admin'@'localhost' with grant option;
flush privileges;
use mysql;
select user, host
from user;
show grants for 'admin'@'%';
show grants for 'admin'@'localhost';
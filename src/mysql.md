# MySQL cheats

#### Contents

- **[Installation](#installation)**
- **[Service controls](#service-controls)**
- **[Create users and databases](#create-users-and-databases)**
- **[Importing data](#importing-data)**
- **[Integrity constraints](#integrity-constraints)**
- **[Optimisation](#optimisation)**
- **[Backups](#backups)**


## Installation

The following instructions assume a Debian-flavoured OS such as Ubuntu.

```sh
$ sudo apt-get install mysql-server
```

When prompted, set a password for MySQL's "root" user.

Secure MySQL by entering the following command to open `mysql_secure_installation` utility. 

```sh
$ sudo mysql_secure_installation
```

Follow the instructions to remove anonymous user accounts, disable remote root login, and remove the test database.

- Remove anonymous users?
- Disallow root login remotely?
- Remove test database and access to it?
- Reload privileges table now?

MySQL consumes a lot of memory when using the default configuration. To set resource constraints, you'll need to edit the MySQL configuration file. 

```sh
$ sudo nano /etc/mysql/my.cnf
```

Comment out all lines beginning with `key_buffer`. This is a deprecated setting.

Update the following settings, as appropriate given available resources and demands on your server:

```
max_connections = 75
max_allowed_packet = 1M
thread_stack = 128K
```

Add the following lines to the end of `my.cnf` (change the values as appropriate for your application's requirements):

```
table_open_cache = 32M
key_buffer_size = 32M
```

Save the changes to MySQL's configuration file by pressing `Ctrl+x y`.

Restart MySQL to save the changes.

```sh
$ sudo service mysql restart
```


## Service controls

To start the MySQL Service:

```sh
$ systemctl start mysqld
```

To stop the MySQL Service:

```sh
$ systemctl stop mysqld
```

To restart the MySQL Service:

```sh
$ systemctl restart mysqld
```

To get status of the MySQL Service:

```sh
$ systemctl status mysqld
```

To start MySQL and set it to run at boot:

```sh
$ systemctl enable mysqld
$ systemctl start mysqld
```


## Create users and databases

Log in to MySQL by entering the following command:

```sh
$ mysql -u root -p
```

Enter MySQL’s root password. You will then be presented with a MySQL prompt.

Instead of doing everything as the root user, with full access to all of the databases and every MySQL command, it is better to create limited accounts with specific permissions.

First, create an empty database by entering the following command. Replace `scheme_name` with your own database name:

```sql
CREATE DATABASE scheme_name;
```

Now create a new user. The hostname ('localhost') is optional.

```sql
CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'a_long_password';
```

At this point, the new user has no permissions to do anything with any databases. The user can't even login.

```sql
GRANT ALL PRIVILEGES ON *.* TO 'newuser'@'localhost';
```

This grants full access to all databases and all tables to the user. The only thing the user cannot do is grant access to other users. To limit access to a particular database:

```sql
GRANT ALL PRIVILEGES ON schema_name.* TO 'newuser'@'localhost';
```

Once you have finalised the permissions that you want to set up for your new users, be sure to reload all the privileges. Otherwise, the changes may go unnoticed until you restart the server.

```sql
FLUSH PRIVILEGES;
```

Here is a short list of other common possible permissions that users can enjoy:

- `ALL PRIVILEGES`: Full access
- `CREATE`: Create new tables or databases
- `DROP`: Delete tables or databases
- `DELETE`: Delete rows from tables
- `INSERT`: Insert rows into tables
- `SELECT`: Use the `SELECT` command
- `UPDATE`: Use the `UPDATE` command
- `GRANT OPTION`: Grant or remove other users' privileges

Syntax:

```
GRANT [type of permission] ON [database name].[table name] TO '[username]'@'localhost';
```

If you want to give them access to any database or to any table, put an asterisk (*) in the place of the database name or table name.

All users will need `SELECT` access to the `mysql.user` table. This is where user permissions are stored.

```sql
GRANT SELECT ON mysql.user TO '[username]'@'localhost';
```

To revoke a permission:

```sql
REVOKE [type of permission] ON [database name].[table name] FROM '[username]'@'localhost';
```

To remove a user entirely:

```
DROP USER '[username]'@'localhost';
```

To test a new user, first logout:

```
quit
```

Then log back in:

```sh
$ mysql -u [username]-p
```


## Importing data

```sh
$ mysql -u username -p database_name < EXPORT.sql
```


## Integrity constraints

#### UNIQUE constraints

This simplest way to enforce unique values in each entry in a column:

```
CREATE TABLE table_name (
  column_name data_type UNIQUE
)
```

MySQL can evaluate uniqueness based on the values of two or more columns.

```
CREATE TABLE table_name (
  column_name1 column_definition,
  column_name2 column_definition,
  UNIQUE(column_name1,column_name2)
)
```

MySQL will automatically assign a name to the unqiue constraint. If you want to specify that yourself, you must use the `CONSTRAINT` syntax.

```
CONSTRAINT constraint_name UNIQUE(column_list)
```

Examples:

```
CREATE TABLE suppliers (
  supplier_id INT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(15) NOT NULL UNIQUE,
  address VARCHAR(255) NOT NULL,
  PRIMARY KEY (supplier_id),
  CONSTRAINT uc_name_address UNIQUE (name, address)
);
```

To add a constraint to an existing table:

```
ALTER TABLE table_name
ADD CONSTRAINT constraint_name 
UNIQUE (column_list)
```

To remove a constraint, use one of the following two commands:

```
DROP INDEX index_name ON table_name;
ALTER TABLE table_name DROP INDEX index_name;
```


## Optimisation

Over time, MySQL tables will get fragmented and queries will take longer to complete. Run `OPTIMIZE TABLE` regularly to defragment the data and keep performance optimised. You could do this via a cron task.

```sh
$ crontab -e
```

```
@weekly mysqlcheck -o –user=root –password=<your password here> -A
```


## Backups

The excellent `automysqlbackup` utility can automatically make daily, weekly, and monthly backups of your MySQL database.

```sh
$ sudo apt-get install automysqlbackup
```

Open the configuration file:

```sh
$ sudo nano /etc/default/automysqlbackup
```

By default, your database backups get stored in `/var/lib/automysqlbackup` which isn’t very intuitive. I recommend changing it to a folder within your home directory. To do this, find the line that begins with `BACKUPDIR=` and change it to `BACKUPDIR="/home/<your username>/backups/"`.

You also want to get an email if an error occurs, so you’ll know if automatic backups stop working for some reason. Find the line that begins with `MAILADDR=` and change it to `MAILADDR="<your email address>"`.

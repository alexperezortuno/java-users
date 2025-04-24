# java-users

### Environment variables

```shell
DB_FORMAT_SQL=true;DB_SHOW_SQL=true;DB_DDL_AUTO=create;DB_CREATE_NAMESPACES=true;JWT_SECRET=V6xBhEri2PHaO6g8nrTLHIZ3bu3kvu8tAjcSRVfHzrDpQsEO44QJSOgjS169sNDf;DB_INIT_MODE=always;H2_CONSOLE_ENABLED=true
```

## Requirements
- Docker
- Java 17
- Maven 3.8.6
- PostgreSQL 16
- Memcached 

---

### Use Docker

If you prefer to use Docker, you can run Memcached with the following command:

```shell
docker run -d --name memcached -p 11211:11211 memcached
```

make image for java-users

```shell
docker image build -t juser:latest .
```

### Run the application with Docker compose

```shell
docker-compose up -d
```
---

## Install Java
To install Java on Linux, use sdkman. For example, on Ubuntu or Debian:

```shell
sudo apt update
sudo apt install curl -y
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk list java
sdk install java 17.0.8-tem
```

---

### Install PostgreSQL
To install PostgreSQL on Linux, use your package manager. For example, on Ubuntu or Debian:

```shell
sudo apt update
sudo apt install postgresql postgresql-contrib -y
```

On CentOS, Fedora, or RHEL:

```shell
sudo dnf install postgresql-server postgresql-contrib
```

After installation, you can start and enable the PostgreSQL service:

```shell
sudo systemctl start postgresql
sudo systemctl enable postgresql
```
Check the status of PostgreSQL:

```shell
sudo systemctl status postgresql
```

### Create PostgreSQL Database

```shell
sudo -u postgres psql
```

Then, in the PostgreSQL shell, create a new database and user:
```sql
```

---
## Install Memcached
To install Memcached on Linux, use your package manager. For example, on Ubuntu or Debian:

```shell
sudo apt update
sudo apt install memcached -y
```

On CentOS, Fedora, or RHEL:
```shell
sudo dnf install memcached
```

After installation, you can start and enable the service:
```shell
sudo systemctl start memcached
sudo systemctl enable memcached
```

Check the status of Memcached:
```shell
sudo systemctl status memcached
```
# java-users

### Environment variables

```shell
DB_FORMAT_SQL=true;DB_SHOW_SQL=true;DB_DDL_AUTO=create;DB_CREATE_NAMESPACES=true;JWT_SECRET=V6xBhEri2PHaO6g8nrTLHIZ3bu3kvu8tAjcSRVfHzrDpQsEO44QJSOgjS169sNDf;DB_INIT_MODE=always;H2_CONSOLE_ENABLED=true
```

## Requirements
- Java 17
- Maven 3.8.6
- PostgreSQL 16
- Memcached 

### Install Memcached
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
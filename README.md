# Gestión de Prácticas CFGS

## Requisitos

Para la conexión a la base de datos, se necesita añadir un archivo "config.ini" al directorio raíz.

El archivo debe tener la siguiente estructura:

```
user=USUARIO
password=CONTRASEÑA
url=jdbc:mysql://HOST_BBDD/NOMBRE_BBDD
mailgun=key-LLAVE_API
```
La base de datos se debe de haber inicializado con el script de creación incluido en el directorio raíz del repositorio.

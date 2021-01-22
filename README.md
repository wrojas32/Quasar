# Fuego de Quasar

La aplicacion tiene la capacidad de obtener la localización de una nave espacial perdida y también el contenido del mensaje de auxilio emitido.

Para este fin existen 3 satélites que se encargan de tringular la posición de la nave teniendo en cuenta la distancia desde la cual fue recibido el mensaje.

## Tecnologías Usadas

- Spring Boot 2.4.1
- Java 11
- Base de datos en memoria H2
- Junit 4.13


## Servicios Rest Desarrollados

- ### Servicio topsecret

  Servicio que envía en el body la información de cada satélite para obtener la ubicación y mensaje enviado por la nave.

  path: POST /topsecret
  
  Body: 
  ```
  {
    "satellites": [
        {
            "name": "kenobi",
            "distance": 2,
            "message": ["este", "", "", "mensaje", ""]
        },
        {
            "name": "skywalker",
            "distance": 2,
            "message": ["", "es", "", "", "secreto"]
        },
        {
            "name": "sato",
            "distance": 3,
            "message": ["este", "", "un", "", ""]
        }
    ]
}
   ``` 
  Response: 
  
  ```
  {
      "position": {
          "x": "1,0",
          "y": "0,0"
      },
      "message": "este es un mensaje secreto"
  }
  ```

- ### Servicio topsecret_split

  Servicio encargado de guardar la información de cada satélite de manera individual.

  path: POST /topsecret_split/{satellite_name}
  
  Path Parameter Variable {satellite_name}: Parámetro variable que indentifica el nombre del satélite.
  
  Body: 
    ```
  {
    "distance": 20,
   "message": ["este", "", "", "mensaje", ""]
  }
  ```
  Response: 
  ```
  {
      "name": "kenobi",
      "distance": 20.0,
      "message": [
          "este",
          "",
          "",
          "mensaje",
          ""
      ]
  }
    ```
 - ### Servicio topsecret_split
 
   Servicio encargado de obtener la localización y mensaje enviando por la nave, tomando los datos ya almacenados previamente.

   path: GET /topsecret_split


   Response: 
  ```
   {
     "position": {
         "x": "1,0",
         "y": "0,0"
     },
     "message": "este es un mensaje secreto"
   }
  ```



 


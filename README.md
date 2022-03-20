# DvD lib

- Frontend: vaadin
- Backend: Spring Boot + MyBatis + MySQL

## Running the application

The project is a standard Maven project. To run it from the command line, type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any Maven project.

## Layout
![image](https://user-images.githubusercontent.com/46221024/159126400-4774ecfc-3fcc-40be-a623-74053d557403.png)
![image](https://user-images.githubusercontent.com/46221024/159167473-3e4c499d-de81-4255-91a6-1eb97bcc50b2.png)

## Project structure

- `views` package contains the server-side Java views of application.
- `themes` folder in `frontend/` contains the custom CSS styles.
- `entity` package contains the entity class.
- `dao` package contains the dao interface and mybatis mapper file.
- `service` package contains the service interface and implementation class.
- `utils` package contains the UUIDUtil class for setting id.
- `exception` package contains the self-defined exception.


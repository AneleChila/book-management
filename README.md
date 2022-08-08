# books-management
As a book collector I want the ability to create, and keep up to date, a catalogue of all my books. The catalogue service should provide a list of all catalogued books from where I need to be able to add new books, update existing information of books and remove books.

Application layering
https://www.baeldung.com/wp-content/uploads/2021/06/Layered-Architecture.png
![Layered-Architecture](https://user-images.githubusercontent.com/40594521/183267307-484d1613-0613-49ca-844d-b5f7214be6a0.JPEG)

## Requirements

**1. Expose a Rest Api for the following actions:**
- List with paging
- Add
- Update
- Delete


**2. Utilise an in-memory DB to support the above-mentioned operations.**
- Book data required:
- Name
- ISBN Number
- Publish date (dd/MM/yyyy)
- Price (ZAR)
- Book Type (Hard Cover, Soft Cover, eBook, etc.)

**3. Technical:**
- Spring Boot 2 application
- Java version: 1.8
- Maven project


## Getting Started


**1. Clone the application**

```bash
git clone https://github.com/AneleChila/books-management.git
```
**2. Build the application**

```bash
mvn clean install
```

**3. Run the application**

```bash
mvn spring-boot:run
```

## Documentation

**1. Swagger UI**
```bash
http://localhost:8080/swagger-ui/index.html#/
```

**2. Import JSON Collection**
```bash
http://localhost:8080/api-docs/
```

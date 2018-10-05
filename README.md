### Spring Data JPA Projections with content negotiation
This is a demo application which showcases how content negotiation and Spring Data JPA Projections can be combined.

### Running the application
`mvn spring-boot:run`

### Sending requests
- Basic request (will return the `NameOnly` projection): `curl -v http://localhost:8080/product`
- Request the `NameOnly` representation of the `Product` entity: `curl -v -H 'Accept: application/vnd.product-name-only+json' http://localhost:8080/product`
- Request the `CodeOnly` representation of the `Product` entity: `curl -v -H 'Accept: application/vnd.product-code-only+json' http://localhost:8080/product`

### Adding another projection
Simply create your projection in the `be.bluemagma.product.projection` package and make sure to define an `ACCEPT_HEADER` value. Include that in the `ProductController`'s `findAll()` method in the `produces` parameter and you'll be able to test it out. 

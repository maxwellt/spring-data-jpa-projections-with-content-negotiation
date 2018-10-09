### Spring Data JPA Projections with content negotiation
This is a demo application which showcases how content negotiation and Spring Data JPA Projections can be combined.

For details on the implementation you can read a detailed blog post on [Bluemagma's website](https://www.bluemagma.be/2018/10/content-negotiation-with-spring-data-jpa-projections/).

### Running the application
`mvn spring-boot:run`

### Sending requests
- Basic request (will return the `ProductNameOnly` projection): `curl -v http://localhost:8080/product`
- Request the `ProductNameOnly` representation of the `Product` entity: `curl -v -H 'Accept: application/vnd.product-name-only+json' http://localhost:8080/product`
- Request the `ProductCodeOnly` representation of the `Product` entity: `curl -v -H 'Accept: application/vnd.product-code-only+json' http://localhost:8080/product`

### Adding another projection
Simply create your projection in the `be.bluemagma.product.projection` package and make sure to define an `ACCEPT_HEADER` value. Include that in the `ProductController`'s `findAll()` method in the `produces` parameter and you'll be able to test it out. 

# Date-dependant product price
The goal of this test is to create a REST endpoint with Spring Boot that returns information about the price of a product.
The service receives the ids of the brand and the product, plus a date to evaluate the price, because it may change due to promotions and whatnot.
It returns the ids of product, brand and fee, plus the dates the fee is applicable, and the price.

As a Spring Boot application, run DatePriceApplication to start a local server at localhost:8080.
The service is only Get, so you can test it from the url bar with /brand/{brandId}/product/{productId}/price?date=YYYY-MM-DDThh:mm:ss.
The date parameter is optional. If not provided, the service uses the current date and time.
Postman_tests.json contains a collection of get requests for Postman to help test the endpoint.

The implementation uses Swagger. The machine-readable documentation is in http://localhost:8080/v3/api-docs in JSON format, and in http://localhost:8080/v3/api-docs.yaml in YAML format.
Human friendly information is in http://localhost:8080/swagger-ui/index.html.

Keep in mind that the database population includes only brand 1, product 35455, and dates from 2020-06-14 to 2020-12-31. Querying dates outside the range, or different brands and products produce a 404 response.
# Design choices
## Model
The model's core class is ProductPrice. Each instance represents a particular price for a particular product, within some time constraints. There is also a priority field to disambiguate. It is related with Brand and Product, both many-to-one since, obviously, there may be several price information for the same combinations of brand and product id. I set those connections to fetch lazy because in most cases I can imagine where I want price information of a product, I either have the brand and product information, or I don't care.
Brand and Product cases I only wrote the id fields. In a real case there would be information of name, fabrics, and whatnot.
## Repository
Since this test only requires accessing to ProductPrice, I only create a ProductPriceRepository, using JPA repositories. To feed the information required by the requested endpoint I use a custom read method that uses ids of product and brand, and a date of application, to obtain the applicable price lists. By default, the query sorts descending by priority, so higher priority items are first. Since I already know that my use case needs only one element, I limit the results to one and return an Optional. 
I also use a projection with only the information I want back. This way, if future developments change or add information to ProductPriceList, as long as that information doesn't change how my endpoint should work, there is no need to modify this method.
## Service
In our case, the service only wraps the access to the repository. Imagine our storage changes. We would change ProductPriceRepository not to implement JPA repository, although it would still declare the findFirstByProductBrandDate method. Our current ProductPriceRespository would become a different JPARepository, maybe ProductPriceRepositoryJpa, and out new storage method would just need to implement the new ProductPriceRepository, with no reference whatsoever to JPA.
## Controller
The controller wraps the call to the service method, but it does a bit more.
First, if the date parameter is not provided, it uses the current date and time. This is because we cannot reasonably choose a brand or a product without the id information, but we can expect that in a significant amount of cases we care about the current price. That changes, of course, if we are returning an item, for instance, because we would care about the price at the time of the purchase.
Second, we define the url to access the resource. In our case, we use brand/{}/product/{}/price to identify "price information belonging to this product of this brand". I understand that date is a parameter that does not identify the resource although it complements the information recovery. I could use just {brandId}/{productId}, but I don't know how many other combinations of two numbers could be. I consider that the url I chose have less risk to change due toadding more endpoints in the future.
In both repository and service I return an Optional, the controller returns a ProductPriceSummary. If the price could not be found, it throws a custom exception, ProductPriceNotFoundException. This Exception includes a response status 404 Not Found. I could do that with an ExceptionHandler, but I think the Exception's scope is close enough that it always make sense for it to be 404.
I also add OpenAPI information by using swagger annotations Operation, Parameter, ApiResponse, Content and Schema. This makes easier to understand the service to other developers who care only about using it and generates nice resources for automatic detection, if needed. I use an additional ProductPriceSummarySchema class to add OpenApi information on the result of the endpoint without using swagger annotations in my Projection. If I were to separate my persistence classes to a library, for instance, this prevents me needing swagger where I wouldn't need it.
# Future developments
These ideas are not implemented, but would probably be of interest.
The current project includes information about the currency, but not the country. However, price could be country-sensitive. Some products may be avaiable at different dates in different countries, and that implies that what is trending in one country may be brand new in a second one or ending its life in a third one.
Also, different countries may have different dates suitable for promotions. Not all countries have Mother's Day in the same date, for instance.
Last, each country has different tax regulations. If we want to offer prices including taxes, this is something we have to consider.
If we talk about countries, we can also talk about currencies other than Euro. Even if Europe is our main market, it still includes countries such as United Kingdom, or neighboring countries like Morocco, who don't use Euro. This widens the previous case regarding both promotions and taxes.

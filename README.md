# Date-dependant product price
The goal of this test is to create a REST endpoint with Spring Boot that returns information about the price of a product.
The service receives the ids of the brand and the product, plus a date to evaluate the price, because it may change due to promotions and whatnot.
It returns the ids of product, brand and fee, plus the dates the fee is applicable, and the price.

As a Spring Boot application, run DatePriceApplication to start a local server at localhost:8080.
The service is only Get, so you can test it from the url bar with /brand/{brandId}/product/{productId}?date=YYYY-MM-DDThh:mm:ss.
The date parameter is optional. If not provided, the service uses the current date and time.
Postman_tests.json contains a collection of get requests for Postman to help test the endpoint.

Keep in mind that the database population includes only brand 1, product 35455, and dates from 2020-06-14 to 2020-12-31. Querying dates outside the range, or different brands and products produce a 404 response.
## Other features
These ideas are not implemented, but would probably be of interest.
The current project includes information about the currency, but not the country. However, price could be country-sensitive. Some products may be avaiable at different dates in different countries, and that implies that what is trending in one country may be brand new in a second one or ending its life in a third one.
Also, different countries may have different dates suitable for promotions. Not all countries have Mother's Day in the same date, for instance.
Last, each country has different tax regulations. If we want to offer prices including taxes, this is something we have to consider.
If we talk about countries, we can also talk about currencies other than Euro. Even if Europe is our main market, it still includes countries such as United Kingdom, or neighboring countries like Morocco, who don't use Euro. This widens the previous case regarding both promotions and taxes.

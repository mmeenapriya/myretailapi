# Getting Started

This is a Rest API based on Spring boot. 
This APi enables users to get product details  [name and price] and to update the price with the product id. 

## Config
Mongo DB Atlas is used to store pricing info and the product details are retrieved from external API - https://redsky.target.com

##Hosting
This application is deployed in Amazon EBS. 
Currently this is hosted manually as Jar but can be automated later.

If a product is in External API but no pricing info, it just displays the product name
if a product has pricing info in mongo it displays both

####To get the product details:

curl -X GET \
  http://myretail-env.eba-6mmu6dpq.us-east-1.elasticbeanstalk.com/myretail/v1/products/13860431 \
  -H 'cache-control: no-cache' 
  
  
####To update the product price:

curl -X PUT \
  http://myretail-env.eba-6mmu6dpq.us-east-1.elasticbeanstalk.com/myretail/v1/products/13860431 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
    "id": "13860431",
    "price": "12.29",
    "name": "The Hunters (DVD)",
   "currency": "USD"
}'


##Local Setup 
1. Git clone the application
    git clone https://github.com/mmeenapriya/myretailapi.git
2. Run gradle task 
    ./gradlew clean build
3. Access API using url
    http://localhost:8080/myretail/v1/products/{id}
    
    example:
    http://localhost:8080/myretail/v1/products/13860432
    
    NOTE: No authentication is added at this point
    
##Using Docker
1. docker pull mmeenapriya/myretailapi:1.0  
2. docker run -p 5000:5000 --rm mmeenapriya/myretailapi:1.0
   




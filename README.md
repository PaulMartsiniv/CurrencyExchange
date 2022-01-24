 #Currency Exchange 
is a simple application for monitoring currencies against a base currency

 ### typical ways to work with the application

 - `http://localhost:8081/source/get_all`
    - this example will return the result with information about the third
   source and what details it caused (date and base currency) 
   and in the base currency there will be info about rates
 - `http://localhost:8081/details/get_all`
   - this example will return the result with details and a list
   of names and ratings relative to the currency
      - you can also use a date filter for it (like `?dateIn=2022-01-22&dateIn=2021-12-01`)
 - `http://localhost:8081/currency/get_all`
   - this example will return the result with title and rating information
     - you can also use a name filter for it (like `?nameIn=UAH&nameIn=USD`)
 - `http://localhost:8081/currency/available`
   - this example will return the result with information about the current set of currency codes
   
 - Docker site `https://hub.docker.com/r/pavlomartsiniv/currency-exchange`
 
 - Docker Pull Command `docker pull pavlomartsiniv/currency-exchange`
![](http://i.imgur.com/y8g506n.png?1)

## Tasks

- Have at least 4 entities, with relationships between them. ==> /Order, Item, Payment, Users/
- At least 2 entities must have full CRUD operations ==> / Order, Item, Payment /
- Have at least one Dynamic Query, one Named query, and one Criteria Query. ==> 
- localhost:8080/item/hasPriceMoreThanCriteria ==> Criteria
- localhost:8080/payment/findMax ==> namedQuery
- localhost:8080/payment/getAllByYearAndMonth ==> dynamic query
- localhost:8080/order/findByYearAndMonth ==> dynamic query
- localhost:8080/order/findMaxByTotalAmount ==> dynamic query
- At least one query involving 3 entities. ==> localhost:8080/findOrderByItemName
- You must have JMS (with two projects, one being a sender and the other a receiver of the message)
- You must have content negotiation. / Order and Item REST API-s /
- You must have Spring Security with at least one API that is public, and at least two roles (and one API for each role).
- localhost:8080/order/findByYearAndMonth => ROLE_MANAGER
- localhost:8080/order/findMaxByTotalAmount => ROLE_CASHIER
- Demonstrate AOP with one use case (using AspectJ) ==> Printing UserController functions executed time
- Upload your work to GitHub, but submit your project to Sakai before the deadline, what is uploaded to Sakai will be graded. You will get extra points if you manage to deploy your work to any platform.
- Demonstrate two profiles (development using H2, and deployment using MySQL)
- Make sure your REST API is stateless.
- Make sure you address concurrency in all your entities, and demonstrate both types of locking.


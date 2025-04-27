# challenge0425
coding challenge Guida Dias: 04/25

# Running project:
* Java version 24
* Using make commands run: make create-remove-infra (this will stop a docker container named postgres if you have on running)
* Using make commands run: make create-infra-local (this will put a postgres named docker container to run with the database needed for this challenge)
* Run Migration Application with ActiveProfile Local
* Run app/DeviceManager with ActiveProfile Local

# Improvements: 
* I wouldn't rely so heavily on JPA and would create a repository with all the queries in order to have more control over
how we are getting all the data. And with more complex queries it becomes a necessity anyway.
* Using human made Queries makes it way  more important to have Integration Tests.
* I would Create a ErrorHandler for the Controller in order to remove the tries and catches from the use cases.
* we can have Tags configuration Tests to guarantee that no one would accidentally change a config. And to make it more intentional for when it's needed.

# Possible Bugs:

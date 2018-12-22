**Uber Analytics:**

The purpose of this project is to gather data from online taxi cab and transportation network company, in our case Uber, from various sources, enrich the collected data and deliver it Data Science team for further analytics.
During this test you will work a bit on all of these steps. Please follow the tasks in the given order and use the technologies listed below.  
You may use any additional library that might help you and version of the technologies below, but we recommend sticking to the most recent one.  


Technologies used:
* Programing language: Scala
* Processing framework: Spark, Spark Streaming
* Queuing technology: Kafka
* Database technology: MySQL.

Database tables:*
`driver (id BIGINT, date_created TIMESTAMP, name CHARACTER VARYING(255))`  
`passenger (id BIGINT, date_created TIMESTAMP, name CHARACTER VARYING(255))`  
 `booking ( id BIGINT, date_created TIMESTAMP, id_driver BIGINT, id_passenger BIGINT, rating INT, start_date TIMESTAMP, end_date TIMESTAMP,  tour_value BIGINT)`

Analysis Performed:

#Analysis 01:
    ** Create a report listing the TOP 12 drivers from 2016. Drivers with a high tour value and a good average rating.

#Analysis 02:
    ** Show relationships between drivers and passengers. In order to know how successful UBER is at creating a good passenger driver relationship, provide a list of the
    TOP 10 strongest relationships between passengers and drivers. This relationship is defined by the number of tours they did together.

#Analysis 03:
    ** Show overall performance of the company.
    Provide a KPI report containing the yearly figures for bookings, the average driver evaulation and revenue for specified year.

#Analysis 05:
    ** Setup your own Kafka Broker locally and publish the results of above analysis to different kafka topics.
   
    

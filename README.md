sf-foodTruck
============

Web app to get sf food trucks at a given latitude and longitude.

How to run the Food Truck web app 

1)You could do the following from command line
   a) mvn package
   b) java -jar target/foodTruck-1.1.4.RELEASE.jar

2) From Eclipse
   a) Import the project into eclipse.
   b) Run as spring boot app.

Once the web app is running, the below is an example of the rest api's available from the web app.

 
Sample output

1) curl -H 'Accept: application/json' 'http://localhost:8080/listOfFoodTrucks?latitude=37.7267279782571&longitude=-122.388658428832'

{
   "errors":null,
   "result":[
      {
         "address":"2115 JENNINGS ST",
         "applicant":"Singh Brothers Ice Cream",
         "approved":"2014-03-11T12:05:34",
         "block":"4828",
         "blocklot":"4828021",
         "cnn":"7479000",
         "expirationdate":"2015-03-15T00:00:00",
         "facilitytype":"Truck",
         "fooditems":"Ice Cream: Pre-Packaged Chips: Candies: Bottled Water & Canned SODA",
         "latitude":37.7267279782571,
         "location":{
            "latitude":37.7267279920895,
            "longitude":-122.388658428822,
            "needs_recoding":false
         },
         "locationdescription":"JENNINGS ST: VAN DYKE AVE to WALLACE AVE (2100 - 2199)",
         "longitude":-122.388658428832,
         "lot":"021",
         "objectid":"525575",
         "permit":"14MFF-0018",
         "priorpermit":"1",
         "received":"Mar 11 2014 11:57AM",
         "schedule":"http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=14MFF-0018&ExportPDF=1&Filename=14MFF-0018_schedule.pdf",
         "status":"APPROVED",
         "x":"6015486.282",
         "y":"2092591.619",
         "noisent":null
      },
      {
         "address":"2115 JENNINGS ST",
         "applicant":"Paul's Catering",
         "approved":"2014-04-23T11:44:19",
         "block":"4828",
         "blocklot":"4828021",
         "cnn":"7479000",
         "expirationdate":"2015-03-15T00:00:00",
         "facilitytype":"Truck",
         "fooditems":"Cold Truck: Pre-packaged sandwiches: snacks: fruit: various beverages",
         "latitude":37.7267279782571,
         "location":{
            "latitude":37.7267279920895,
            "longitude":-122.388658428822,
            "needs_recoding":false
         },
         "locationdescription":"JENNINGS ST: VAN DYKE AVE to WALLACE AVE (2100 - 2199)",
         "longitude":-122.388658428832,
         "lot":"021",
         "objectid":"534699",
         "permit":"14MFF-0098",
         "priorpermit":"1",
         "received":"Apr 23 2014 11:28AM",
         "schedule":"http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=14MFF-0098&ExportPDF=1&Filename=14MFF-0098_schedule.pdf",
         "status":"APPROVED",
         "x":"6015486.282",
         "y":"2092591.619",
         "noisent":null
      }
   ]
}

2) curl -H 'Accept: application/json' 'http://localhost:8080/initializeCache'

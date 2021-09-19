# SpaceXInsights

This repository provides insights on SpaceX data. For now, it compares
how much weight SpaceX rockets could ship throughout time while progress is going on.

The code extracts the needed data from the [SpaceX API](https://github.com/r-spacex/SpaceX-API), creates
a graph and transforms it in a base64 string which can then be shipped over a REST API and be shown in the browser.

#### Running the code
1. The code heavily depends on Maven. When you don't have Maven installed, you can do so by following [this](https://maven.apache.org/download.cgi)
    link.   
2. Clone the repository:
```
git clone https://github.com/mpospelova/SpaceXInsights.git
```
3. Go to cloned repository and install all the maven dependencies:
```
mvn install
```
4. The code can be run in 2 different ways:
    - Run the code from the class `Main.java`. Here, you only create the graph.
    - Run the code from the class `RestServiceApplication`. If you do that, a REST API is created and you can see the displayed image from the browser. To do that, go to the web   address `http://localhost:8080/image`. The graph will be displayed there.


##### General process
1. Code is started from `Main.java` or  `RestServiceApplication`. From there, `EntryPoint` is called.

2. `EntryPoint` is responsible for connecting to the SpaceX API, extracting data, preparing data for graph drawing and shipping it to console/web browser.
    3. At first, `EntryPoint` needs to connect to SpaceX API. It does so by calling the class `APIConnector`.
        4. `APIConnector` opens a connection to the API and extracts data streams.
    5. `EntryPoint` receives data streams and passes them over to `DataExtractor` class.
        6. `DataExtractor` extracts data from streams to lists.
  
3.`EntryPoint` uses the created lists and draws a graph by calling `ImageDrawer`. The image is then converted to a base64 string and returned.

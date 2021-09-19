# SpaceXInsights

This repository provides insights on SpaceX data. For now, it compares
how much weight SpaceX rockets could ship throughout time while progress is going on.

The code extracts the needed data from the [SpaceX API](https://github.com/r-spacex/SpaceX-API), creates
a graph and transforms it in a base64 string which can then be shipped over an API.

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
Now you can run the code.
    
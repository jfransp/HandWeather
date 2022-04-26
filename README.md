# HandWeather App
Weather app developed on top of the OpenWeather API, using Jetpack Compose, Koin, Ktor and multiple custom components.

<p float="left"
align="center">
  <img src="https://github.com/jfransp/HandWeather/blob/main/Captura%20de%20Tela%202022-04-19%20a%CC%80s%2023.48.06.png?raw=true" width="200" />
  <img src="https://github.com/jfransp/HandWeather/blob/main/Captura%20de%20Tela%202022-04-19%20a%CC%80s%2023.25.30.png?raw=true" width="200" /> 
  <img src="https://github.com/jfransp/HandWeather/blob/main/Captura%20de%20Tela%202022-04-19%20a%CC%80s%2023.26.04.png?raw=true" width="200" />
</p>

# Libraries and Implementations

### UI
Jetpack Compose
### Room
Persistence library for caching
### Ktor
Network requests
### Coroutines
Concurrency
### Koin
Dependency injection

### Accompanist
Multiple accompanist dependencies to facilitate certain functions within the Jetpack Compose framework.

### Custom Components
Several custom components, including a custom ViewModel implementation that facilitates the usage and maintenance
of states and actions when using the Compose framework


# OpenWeather API
This app was developed on top of the [OpenWeather](https://openweathermap.org/api) API.

# Architecture
MVVM, Clean Architecture application -> Mappers, UseCases, DataSources, Interfaces, etc. 

There's also a functional caching strategy - the UI only observes local data sources, which are updated according to location updates from the fusedLocationProvider in the case of the HomeScreen current location, or using a timer in the case of the cities. In this way, we have almost a real time climate reading, with updates between 10 and 30 seconds. API calls are only made when data flow is necessary for a good user experience. In the case of this application, the repositories do all the mediation between the local and remote data sources, fetching data from RemoteDataSource, collecting from LocalDataSource and fetching/upserting in case of a null result, there's no particular class responsible for caching strategy - the repository manages it.

Most of the architectural ideas are arguably not suited for such a small project, but it's interesting and educational anyways. (:

# Simplified Dependency and Architecture Graph
![HandWeather DephGraph](https://user-images.githubusercontent.com/74152618/164141025-c95caa9a-fbe8-4716-b789-1bebe54f9458.jpeg)

# How to use it
If you wish to build this code, you first have to make and account (for free) and get an API key for the OpenWeather API. Then, go to
the class Constants on the Util folder at the data module, and set the API_KEY constant as your new API key. This way of storing and using
an API Key isn't ideal from a production standpoint due to it being accessible through decompilation, but for the purposes of this project it works.
Also, I didn't even bother hiding my key in the original source code, if you want you can just build the code as it is, I don't mind!

# TODO(Not yet implemented...)
I've actually written a buch of code for error handling and error dialogs - most of the error handling is done(with handling classes, Resource utility and error entities), but there's still the actual UI and action handling left to be made. In some cases, like on the city screen, I've handled API call errors in the UI through a simplified popup, but there's no error dialog management anywhere else on the application.



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
Persistence library
### Ktor
Network requests
Pagination of fetched data
### Coroutines
Concurrency
### Koin
Dependency injection

### Accompanist
Multiple accompanist dependencies to facilitate certain functions within the Jetpack Compose framework.

### Custom Components
Several custom components, including a custom ViewModel implementation that facilitates the usage and maintenance
of states and actions when using the Compose framwork


# OpenWeather API
This app was developed on top of the [OpenWeather](https://openweathermap.org/api) API.

# Architecture
MVVM, Clean Architecture application -> Mappers, UseCases, DataSources, Interfaces, etc.

# Simplified Dependency and Architecture Graph
![HandWeather DephGraph](https://user-images.githubusercontent.com/74152618/164141025-c95caa9a-fbe8-4716-b789-1bebe54f9458.jpeg)

# Release
There will be a realease apk available on the app folder of this repository if you wish to download a working version of the application.

# How to use it
If you wish to build this code, you first have to make and account (for free) and get an API key for the OpenWeather API. Then, go to
the class Constants on the Util folder at the data module, and set the API_KEY constant as your new API key. This way of storing and using
an API Key isn't ideal from a production standpoint due to it being accessible through decompilation, but for the purposes of this project it works.
The release apk version available at the app package utilizes my own personal API Key - there's not much of an issue with it I guess.






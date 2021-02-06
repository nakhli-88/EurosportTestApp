# EuroSport@Nakhli
Test for EuroSport : Clean Archi, MVVM, Rxjava, liveData, Koin

# EuroSport@Nakhli
Test for EuroSport : Clean Architecture, MVVM, Rxjava, liveData, LifeCycle, Koin, Unit Test, ExoPlayer, Retrofit

This project is an Android test for EuroSport.
 the principal fonctionnalities are :
- List of News in the first screen
- Details of each News in the second screen.
- PlayVideo of each videos by id


In this project I implemented different Android development techno like:
.Clean Architecture :  with 3 modules (app, domain, data)
.MVVM (Android Jetpack)
.Koin for dependency injection
.Room for local database
.Retrofit
.ExoPlayer for play video
.LifeCycle for unified the LifeCycle
.Mockito and Junit for testing

also
- I implemented a transition animation (photo) between list screen and details screen
- I implemented a flavor to setup the different standard environment (dev, integration, recette, prod).
- I implemented Room DB for offline mode (not complete ..)
- I play video for each videos List


- for test : I focused my tests on the "usecase" and the ViewModel because generally this is where the application logic is, and especially the usecases
- In the StoryDetails Screen : I just put the image and a long text, and I add an animation for the image in appbar
- In the VideoPlayer Screen : I Play video using ExoPlayer

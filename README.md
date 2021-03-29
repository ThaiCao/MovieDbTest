<h1 align="center">MovieDb</h1>

<p align="center">
MovieDb is a sample Android project using <a href="https://www.themoviedb.org/">The Movie DB</a> API based on MVVM architecture. It showcases the app development with well-designed architecture and up-to-date Android tech stacks.
</p>

## Features
* 100% Kotlin
* MVVM architecture
* Android architecture components and Jetpack
* Single activity
* Dependency injection
* Testing (Base)

## Tech Stacks
* [Retrofit](http://square.github.io/retrofit/) + [OkHttp](http://square.github.io/okhttp/) - RESTful API and networking client.
* [Koin](https://insert-koin.io/) - Dependency injection.
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - A collections of libraries that help you design rebust, testable and maintainable apps.
    * [Room](https://developer.android.com/training/data-storage/room) - Local persistence database. (Upcoming)
    * [Paging](https://developer.android.com/topic/libraries/architecture/paging) - Pagination loading for RecyclerView.
    * [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) - UI related data holder, lifecycle aware.
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Observable data holder that notify views when underlying data changes.
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Declarative way to bind data to UI layout.
* [RxJava](https://github.com/ReactiveX/RxJava) - Asynchronous programming with observable streams.
* [Epoxy](https://github.com/airbnb/epoxy) - Simplified way to build complex layout in RecyclerView.
* [Glide](https://github.com/bumptech/glide) - Image loading.

## Architectures

I follow Google recommended [Guide to app architecture](https://developer.android.com/jetpack/guide) to structure our architecture based on MVVM, reactive UI using LiveData / RxJava observables and data binding.

* **View**: Activity/Fragment with UI-specific logics only.
* **ViewModel**: It keeps the logic away from View layer, provides data streams for UI and handle user interactions.
* **Model**: Repository pattern, data layers that provide interface to manipulate data from both the local and remote data sources.

## Package Structures

```
com.app.moviedb # Root Package
├── core                # For base class
│   ├── model           # Model and response model
│   ├── network         # Remote data source
│   └── repository      # Repositories for single source of data
│   └── ui              # Base activity, base fragment, base view
│   └── viewmodel       # Base viewmodel
|
├── di                  # Dependency injection modules
│
├── features            # Fragment / View layer
│   ├── home            # Main screen Fragment and ViewModel
|   │   ├── controller  # Epoxy controller for RecyclerView
|   │   └── models      # Epoxy models for RecyclerView
│   └── details         # Detail screen Fragment and ViewModel
|
├── utils               # Utility Classes / Kotlin extensions
├── MainActivity        # Single activity
├── BaseApplication     # Application

```


## API Key 🔑
You will need to provide developer key to fetch the data from TMDB API.
* Generate a new key (v3 auth) from [here](https://www.themoviedb.org/settings/api). Copy the key and go back to Android project.
* Define key "movieApiSecret"  in the file `.gradle.properties`.

```kotlin
movieApiSecret = e54dxxxxxxxxxxxxxxxxxxxxxx
```
### BaseHQ Marketplace

1. Display Categories
2. Display Products.
3. Offline Cart (Add and Delete)
4. Display Cart.

### Screenshots

<img src="https://github.com/dev-juri/BaseHQ/assets/category.jpg" width="48" height="80"/>
<img src="https://github.com/dev-juri/BaseHQ/assets/products.jpg" width="48" height="80"/>
<img src="https://github.com/dev-juri/BaseHQ/assets/details.jpg" width="48" height="80"/>
<img src="https://github.com/dev-juri/BaseHQ/assets/cart.jpg" width="48" height="80"/>

### Demo

Download and test the apk [here](https://github.com/dev-juri/BaseHQ/assets/basehq-debug.apk).
The minimum SDK required to run the apk is SDK 24 (Android Version 7).

### Architecture, Library and Tools

1. Dagger Hilt for Dependency Injection
2. Navigation Components
3. Kotlin Coroutines
4. Room database for offline caching
5. Retrofit for Networking
6. Glide to load images
7. Android Architecture Components(ViewModel, LiveData)
8. Clean architecture, MVVM Repository pattern

### Architecture
The app is built with the Model-View-ViewModel (MVVM) Architecture.
MVVM is a software design pattern that separates the user interface from the underlying data layer.
This makes it easier to develop and maintain complex applications.
In MVVM, the Model represents the data(remote sources and local sources) of the application.
The View represents the UI of the application. The ViewModel is a layer between the Model and the
View.
It is responsible for transforming the data from the Model into a format that the View can
understand.

### Structure

```
| - java
| - - com
| - - -oluwafemi
| - - - - basehq
| - - - - - adapter (contains Recyclerview adapters)
| - - - - - data (contains folders and files responsible for API requests and database interaction)
| - - - - - di (contains Dependency modules)
| - - - - - ui (contains the Views and ViewModel)
| - - - - - utils (Contains utility methods, extension and helper functions for different use across the project).
```

### Contributing

Clone the project run ```git clone https://github.com/dev-juri/BaseHQ.git``` and build on Android
Studio.
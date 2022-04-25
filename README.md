# FoodStore

## Index

- [Screenshots](#screenshots)
- [App Features](#app features)
- [Architecture](#architecture)
- [Modules](#modules)
- [Testing](#testing)
- [Libraries](#libraries)
- [Future Enhancements](#future-enhancements)


## ScreenShots
<img src="screenshots/product_list.png" width=300> <img src="screenshots/product_details.png" width=300>


## App Features

1. **Multi Features Modules:** Multi feature modules architecture have been followed in this project
   where every feature have their own module
2. **Clean Architecture:** The app is built using Uncle Bob's clean architecture
3. **MVI Architecture:** UI management is in the form of states by using state flows.
4. **Navigation** Each feature module will have their own internal module navigation, But navigation
   between different modules will be handled using navigation module

## Architecture

This app is using stack of architectures like multi feature modules, Clean Architecture, MVI
Architecture that makes it highly scale-able SOLID principles were kept in mind while developing the
project. By using this architecture every feature have their own clean architecture implemented
within it. Every module will have their own resources and testing logics If its not being used
anywhere else.

## Modules

To make this app highly scale-able every feature is separated out in a new module, with feature
based modules there are also some service based modules i.e., 'data'

### App Module

App module is connecting all the modules using di module, It have activities and control of flow
between modules.

### DI Module

DI module contains dependency injection logic for all the application.

### Features

Features Directory have all relevant feature modules, to navigate to a feature navigation module is
used. All features are using shared modules to use common components.

### Shared Modules

Core, data, navigation modules are being shared to all feature modules to reuse components and data.

### Local

Local module contains logic for db and It can contain shared preference logic as well.

## Testing

Most of the testing Domain module is tested mostly using mocks by mockk.io DB is tested in local
module. The Api communication is tested in dashboard module using mock web server

## Libraries

- **AndroidX** - ViewModel
- **Material Design** - UI designing
- **KotlinX** - StateFlow,Coroutines, Flow, Serialization
- **Hilt** - Dependency Injection
- **Navigation Component** - User navigation
- **Coil** - Loading Images
- **Room** - Database Storage
- **Retrofit** - API Calls

## Future Enhancements

Use jetpack compose for UI Writing UI tests Use dependency injection for unit testing

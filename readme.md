Appetiser Coding Challenge
==================================
- Submission of Appetiser Coding Challenge as follows:

>Create a master-detail application that contains at least one dependency. This application should display a list of items obtained from a iTunes Search API and show a detailed view of each item. The URL you must obtain your data from is as follows:

>https://itunes.apple.com/search?term=star&country=au&media=movie&;all

>(iTunes Web Service Documentation: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching)

Prerequisites
--------------

- Android Studio
- Kotlin Support Library
- Latest Android Build Tools
- AndroidX Repository

Getting started
---------------

Clone this repository by using the command lines below

- git clone https://github.com/rjmangubat23/coding-challenge.git
- cd coding-challenge
- Import via Android Studio


Project Structure
---------------
This project is utilizing the Model-View-ViewModel with the Architecture components

Main package structure items:

    ├─ domain
    ├─ model
    ├─ view
    ├─ viewmodel

- **domain** - contains the domain methods pertaining to network, dagger, utility classes and etc.
- **model** - The sources of the DATA in our app. Business Logic will be involved inside to process the data.
- **view** - contains the UI displays to user. It’s know what DATA need to be displayed to users. It contain Pure UI Logic that control the change of the UIs.
- **viewmodel** - responsible for exposing (converting) the DATA from the model in such a way that objects are easily managed and presented. It’s more modal than view

More information on why I used MVVM with its pros and cons can be found [here](https://www.tutorialspoint.com/mvvm/mvvm_advantages.htm)

Tech stack used
---------------
- Kotlin
- MVVM
- RxKotlin/Java
- Room
- Dagger 2

Authors and Acknowledgment
---
This is for Appetiser Apps submission purposes only.

© Reuben James Mangubat


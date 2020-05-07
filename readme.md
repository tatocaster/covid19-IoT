# COVID-19 stats for Georgia on Android Things
__Runs on mobile as well__
  
Weekend project which uses NXP Pico i.MX7 board  

### Intent
Lays on top of the table.  
The device is to keep track of the current stats for COVID-19 in Georgia.  
In every 15 minutes, work manager kicks off the periodic job to fetch new data and stores it in the   
database as well for the cache. Manual update is available using the refresh button.  
That's it, really.   
uses external API.  

##### Photo
![Final photo](/assets/product.jpg)

#### Future
- Maybe some charts and more detailed info.
- Actually it can be easily built for phones also, needs some tweaks and new flavour.


#### What it lacks
- Gradle files can be scripted in Kotlin.
- There is still a lot of room (pun intended) for improvement and to improve overall code quality.
- Navigation component for multi-module navigation, possibly in the future?
- Flavor for mobile-only build? 

#### How it's built
Overcomplication at its best. uses Android Architecture Components, Dagger2, AssistedInject, Retrofit and hey! it's modular


##### Static code analysis
`./gradlew evaluateViolations`


### License
MIT. See [LICENSE](LICENSE)

Copyright 2020 Merab Tato Kutalia

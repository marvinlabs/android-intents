Android Intents
===============

A small library which will save you from writing the same intent creation code again and again for the most simple tasks. I found myself writing my own 
library to create some common intents I was using across projects, so we decided to push that code to a project everyone could contribute to.

Here are some sample factory methods that will allow you to do some simple things:

- Send emails: 

    `startActivity( EmailIntents.newEmailIntent( "me@example.com", "My subject", "Hey there!" ) );`

- Use the phone:

    `startActivity( PhoneIntents.newCallNumberIntent( "123456789" ) );`
	
    `startActivity( PhoneIntents.newSmsIntent( "123456789", "Hi!" ) );`
	
- Display an address on a map:

    `startActivity( GeoIntents.newMapsIntent( "Musée du Louvre 75058 Paris", "Le Louvre" ) );`
	
- Open your application on the available market application (be it Google Play, Amazon, ...):

    `startActivity( SystemIntents.newMarketForAppIntent( getApplicationContext() );`
	
	
This project has now been initiated with a few intents but we are looking forward to integrating your own intents to ease each developer's life.

Some rules for contributors: 

- If the intent you are creating does not fit into any of the provided utility classes (EmailIntents, GeoIntents, ...), do not hesitate to create your own. 
Those classes are meant to be simple factories with only static methods.
- If the intent you are creating is specific to an application (like a particular Twitter client), please put the utility class in a sub-package named after 
that application.

## Usage

The easiest way to get the library included in your project is by using Gradle. Simply add the following line to your dependencies block:

    dependencies {
        compile 'com.marvinlabs:android-intents:1.2.+@aar'
    }
    
Of course, you can replace the version number by whichever version you need (you can have a look at this repository's tags to know which is the latest).

## About Vincent & MarvinLabs

I am a freelance developer located in Biarritz, France. You can [have a look at my website](http://vincentprat.info) to get to know me a little better. If you want to follow me, here are some links:

* [Follow me on Twitter](http://twitter.com/vpratfr)
* [Follow me on Google+](https://plus.google.com/+VincentPrat)
* [Follow me on Facebook](http://www.facebook.com/vpratfr)

MarvinLabs is my digital studio specialised in native mobile applications and web sites. You can [browse our website](http://www.marvinlabs.com) to get to know us a little better. If you want to get updates about our work, you can also:

* [Follow us on Twitter](http://twitter.com/marvinlabs)
* [Follow us on Google+](https://plus.google.com/+Marvinlabs)
* [Follow us on Facebook](http://www.facebook.com/studio.marvinlabs)

## Change log

### 1.2.0 (2013-06-19)

- Added a demo
- Added Android Studio support
- Added maven repository support

### 1.1 (2013-06-19)

- Added intents to open the app markets
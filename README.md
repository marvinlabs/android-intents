android-intents
===============

A small library which will save you from writing the same intent creation code again and again for the most simple tasks. I found myself writing my own library to create some common intents I was using across projects, so we decided to push that code to a project everyone could contribute to.

Here are some sample factory methods that will allow you to do some simple things:

- Send emails: 

    `startActivity( EmailIntents.newEmailIntent( "me@example.com", "My subject", "Hey there!" ) );`

- Use the phone:

    `startActivity( PhoneIntents.newCallNumberIntent( "123456789" ) );`
	
    `startActivity( PhoneIntents.newSmsIntent( "123456789", "Hi!" ) );`
	
- Display an address on a map:

    `startActivity( GeoIntents.newMapsIntent( "Musée du Louvre 75058 Paris", "Le Louvre" ) );`
	
	
This project has now been initiated with a few intents but we are looking forward to integrating your own intents to ease each developer's life.

Some rules for contributors: 

- If the intent you are creating does not fit into any of the provided utility classes (EmailIntents, GeoIntents, ...), do not hesitate to create your own. Those classes are meant to be simple factories with only static methods.
- If the intent you are creating is specific to an application (like a particular Twitter client), please put the utility class in a sub-package named after that application.

License
-------

Copyright 2013 Vincent Mimoun-Prat

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

More info
---------

- Get some more info and other Android tutorials on our blog: [blog.marvinlabs.com][1]
- Get to know what MarvinLabs does on our website: [www.marvinlabs.com][2]

  [1]: http://blog.marvinlabs.com
  [2]: http://www.marvinlabs.com
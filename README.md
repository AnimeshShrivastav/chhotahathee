# chhotahathee
Here is code for User-Interface.

HomePage.java :- source code in android-java for user-interface screen.
MyLay.java    :- an overlay api of openstreetmap has been extended & customized for providing realtime locations of trucks & cargos.
Locator.java  :- an inbuilt-api interface for GPS/NETWORK locations has been customized to keep a record of current location in it.

'Locator' class will maintain  current-position in it inside 'fix' variable-member-field of Location data-type.
'MyLay' class will super-impose or overlay icons for trucks/users gathered from 'Locator' for self and 'Backed' for others.

'HomePage' class is activity-class of android extended & customized to have input of cargo-type/size/price etc. & truck-characteristics.
'Initially a toggle button will convert truck-driver mobile-app to cargo-owner mobile-app

Later it will be unchangeable..so that truck-drivers remain what they are & cargo-owners remain what they are.

Initially, trucks positions were simulated rather than getting from back-end by random location generation within code.

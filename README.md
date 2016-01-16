TweetsFinder
-----------------
An Android App that based on keywords entered by the user shows the Tweets related to that terms

Screenshots
-----------------
![Home](/etc/home.png?raw=true)
![Search](/etc/search.png?raw=true)

Support
-----------------
If you've found an error in this project, please file an issue: https://github.com/feragusper/TweetsFinder/issues

Patches are encouraged, and may be submitted by forking this project and submitting a pull request through GitHub.

Contribute
-----------------
Pull requests are welcome :)

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

Build Configuration
-----------------
In order to set up the oauth keys you'll need to get them from your twitter account and replace the build config variables within the build.gradle of the app module

Requirements
-----------------
- [Android SDK](http://developer.android.com/sdk/index.html).
- Android [5.1 (API 23) ](http://developer.android.com/tools/revisions/platforms.html#5.1).
- Android Build Tools 23.0.1

Architecture
-----------------
This project follows architecture guidelines that are based on [Fernando Ceja's blog post] (http://fernandocejas.com/2015/07/18/architecting-android-the-evolution). 

Libraries and tools included
-----------------
- Support Libraries
- RecyclerViews and CardViews
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) 
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Picasso](http://square.github.io/picasso/)

Licensing
---------
Please see the file called LICENSE.
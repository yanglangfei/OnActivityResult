# OnActivityResult

[![Build Status](https://travis-ci.org/vanniktech/OnActivityResult.svg)](https://travis-ci.org/vanniktech/OnActivityResult)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

* Generates boilerplate code for OnActivityResult callbacks and lets you focus on what matters.
* Generated code is fully traceable and debuggable.
* Everything is generated during compile time with appropriate errors / warnings.
* No reflection used!

## Gradle

```groovy
buildscript {
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}

apply plugin: 'com.neenbedankt.android-apt'

dependencies {
    compile 'com.vanniktech:onactivityresult:0.1.0'
    apt 'com.vanniktech:onactivityresult-compiler:0.1.0'
}
```

### Snapshots

```groovy
compile 'com.vanniktech:onactivityresult:0.2.0-SNAPSHOT'
apt 'com.vanniktech:onactivityresult-compiler:0.2.0-SNAPSHOT'
```

Modules are located on [Maven Central](https://oss.sonatype.org/#nexus-search;quick~onactivityresult).

## Example

Override `onActivityResult` in your Activity / Fragment and call `ActivityResult.onResult`

```java
@Override
protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    ActivityResult.onResult(requestCode, resultCode, data).into(this);
}
```

Annotate your methods and get the callback

```java
@OnActivityResult(requestCode = 33)
void onActivityResultTestActivity() { /* Do something */ }

@OnActivityResult(requestCode = 1, resultCodes = { Activity.RESULT_OK })
void onActivityResultActivityOk() { /* Only do something when ok */ }

@OnActivityResult(requestCode = 1, resultCodes = { Activity.RESULT_CANCELED })
void onActivityResultActivityCanceled() { /* Only do something when canceled */ }

@OnActivityResult(requestCode = 2)
void onActivityResultPickImage(final int resultCode, final Intent intent) { /* Do something */ }
```

Various parameters are supported:

* `none`
* `int`
* `Intent`
* `int, Intent`
* `Intent, int`

Where int parameters will get the resultCode and Intent parameters will get the Intent. Note: Each annotated method shall only have one int and / or Intent variable.

In addition to that other parameter annotations are supported like:

* [@IntentData](onactivityresult-annotations/src/main/java/onactivityresult/IntentData.java) `Uri uri`. Since 0.2.0

See more examples [here](./onactivityresult-sample/src/main/java/com/vanniktech/onactivityresult/sample/MainActivity.java
).

## Advantages over [AfterMath](https://github.com/MichaelEvans/Aftermath)

* Gives you compile error(s) when using invalid RequestCode
* Annotated method does not require resultCode & Intent to be present. It'll work with every combination (no params, resultCode, Intent, resultCode & Intent, Intent & resultCode)
* The annotations are on mavenCentral
* More detailed error messages
* Almost 100% unit test coverage
* Inheritance support
* [@IntentData](onactivityresult-annotations/src/main/java/onactivityresult/IntentData.java) annotation. Since 0.2.0
* Specify resultCodes with e.g. `resultCodes = { Activity.RESULT_OK }`. Since 0.2.0

## Thanks

[Thanks to JakeWharton's ButterKnife](https://github.com/JakeWharton/butterknife)

[Thanks to Hannes Dorfmann's Annotation Processing 101](http://hannesdorfmann.com/annotation-processing/annotationprocessing101/)

# License

Copyright (C) 2015 Vanniktech - Niklas Baudy

Licensed under the Apache License, Version 2.0
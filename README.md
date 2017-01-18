# Toasty
[![](https://jitpack.io/v/GrenderG/Toasty.svg)](https://jitpack.io/#GrenderG/Toasty)

The usual Toast, but with steroids. **(Screenshots at the end of the file.)**

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Dependency
--

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	compile 'com.github.GrenderG:Toasty:1.0'
}
```

Usage
--

To display an error Toast:

``` java
Toasty.error(yourContext, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
```
To display a success Toast:

``` java
Toasty.success(yourContext, "Success!", Toast.LENGTH_SHORT, true).show();
```
To display an info Toast:

``` java
Toasty.info(yourContext, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
```
To display a warning Toast:

``` java
Toasty.warning(yourContext, "Beware of the dog.", Toast.LENGTH_SHORT, true).show();
```
To display the usual Toast:

``` java
Toasty.normal(yourContext, "Normal toast w/o icon").show();
```
To display the usual Toast with icon:

``` java
Toasty.normal(yourContext, "Normal toast w/ icon", yourIconDrawable).show();
```

You can also create your custom Toasts with the `custom` method:
``` java
Toasty.custom(yourContext, "I'm a custom Toast", yourIconDrawable, textColor, tintColor, duration, withIcon, true).show();
```

There are variants of each method, feel free to explore this library.

Screenshots
--

<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/scr1.png" width="250">
<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/scr2.png" width="250">
<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/scr3.png" width="250">
<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/scr4.png" width="250">
<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/scr5.png" width="250">
<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/scr6.png" width="250">

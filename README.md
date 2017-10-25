# Toasty
[![API](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9) [![](https://jitpack.io/v/GrenderG/Toasty.svg)](https://jitpack.io/#GrenderG/Toasty) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Toasty-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5102) [![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=XUUEWEHJYFYV2)

<div align="center">
	<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/web_hi_res_512.png" width="128">
</div>

The usual Toast, but with steroids.

## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	compile 'com.github.GrenderG:Toasty:1.2.5'
}
```

## Configuration

This step is optional, but if you want you can configure some Toasty parameters. Place this anywhere in your app:

```java
Toasty.Config.getInstance()
    .setErrorColor(@ColorInt int errorColor) // optional
    .setInfoColor(@ColorInt int infoColor) // optional
    .setSuccessColor(@ColorInt int successColor) // optional
    .setWarningColor(@ColorInt int warningColor) // optional
    .setTextColor(@ColorInt int textColor) // optional
    .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
    .setToastTypeface(@NonNull Typeface typeface) // optional
    .setTextSize(int sizeInSp) // optional
    .apply(); // required
```

You can reset the configuration by using `reset()` method:

```java
Toasty.Config.reset();
```

## Usage

Each method always returns a `Toast` object, so you can customize the Toast much more. **DON'T FORGET THE `show()` METHOD!**

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

You can also create your custom Toasts with the `custom()` method:
``` java
Toasty.custom(yourContext, "I'm a custom Toast", yourIconDrawable, tintColor, duration, withIcon, 
shouldTint).show();
```
### Extra
[You can pass formatted text to Toasty!](https://github.com/GrenderG/Toasty/blob/master/app/src/main/java/es/dmoral/toastysample/MainActivity.java#L98-L107)

**There are variants of each method, feel free to explore this library.**

## Screenshots

**Please click the image below to enlarge.**

<img src="https://raw.githubusercontent.com/GrenderG/Toasty/master/art/collage.png">

Apps using Toasty
--

Want to be here? Open an `issue` or make a `pull request`.

<table>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/vmch41lYF_TKb1MKgtYrSgz2rKQ4T1EnGRCGpWSMqLRSzi_pgNWoZpw9WJE8UV4t614" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.trivisionzero.chromophoto">ChromoPhoto - Colorize B&W</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/2EYJPs-qBlKJ3L6cy7idQpzKfZkTzA2G4UQfbs-96VGMftQ-7aV4Dvj77ejzZlAAVx_C" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.serg.chuprin.tageditor">AutoTagger - редактор тегов.</a></td>
	</tr>
	<tr>
		<td><img src="https://archive.org/download/ic_launcher_colorhub/ic_launcher_colorhub.png" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=cheetatech.com.colorhub">ColorHub - Color Palette</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/Z9tz0izoW0CuBS59w9hbxbn3a7cSSwZUeGr1o9TpapngTKb4MKaGunZP-B306CxBAI8" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.fa.touch.free">Touch for Facebook</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/rXB22UBHujsK2uYpN-kAkVFBjTcnAp6ltSZYf9-LdYvRkM-kF-xtwPwR8kEInhludA" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.fa.daily.free">Daily – News flipped around</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/ISQPSPA__uWU4Csw4N0quI0IPi_WcWN0pY4PK86yljf39vaCObvohT9ak2ubQ7iLDQ" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=es.jmoral.ozreader">Oz! Comic Reader</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/KxzCiu-csleONAW9kfAYBCaCe4iAnhyO1ziuKjKK_yEDE0xPQMfy_-sYVYkj4RBE-Srt" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.andreacioccarelli.impactor">Impactor Unroot</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/7e0iTo60TJXz6U-zQl6pXcfgRCLifQaTp_DczwNA5ZSnrEssBwH6K0MU88gC9BzQlMY" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.andreacioccarelli.fusemounter">Fusemounter</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/gdGrQHkHsfRAY9ivf8wt9vgaX9KPxpFHdFq5AXY_zw2P8Wat3KNstvf-BkNaKrNX8Qg" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.thesrb.bluewords">BlueWords</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/ipeHvI63HPVwGK3VmyP-flSbTDoh2q93Fte-xwYKgf4OTsEvjC_wqUfKSejjAWZElS8p" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.levionsoftware.photos">Levipic - Photo Gallery & Map</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/RL082J8D9AyVJdyoT8sN8Mb47LUJEn3ssvp8jgrke_K_sWAXgEl9F8tjudqDoL7y5A0=w300" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.sunshine.makilite">Maki for Facebook & Twitter</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/u4zbQiMobr58biVKT67ka1N-cU_URBAdD6MDU2fGQYsWePvqH9UncMpGnWAFzczfSA=w300" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=com.sunshine.eva">Eva: Everything for Telegram</a></td>
	</tr>
	<tr>
		<td><img src="https://lh3.googleusercontent.com/zhky42kxZcdlt1u7D6lzc3Mwq0gV0nTiFdm-T3DygQVcNPOofNba3Q2_MWtqoJFgdF8a=w300" width="64"/></td>
		<td><a href="https://play.google.com/store/apps/details?id=org.imperium.imperious.Pastebin">Pastebin</a></td>
	</tr>
</table>

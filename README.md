# colors-ktx
Colors is a Kotlin first library that provides useful functions to work with colors. It works with integer values of colors which are supported by the Android framework. All the color parameters it accepts and returns are annotated with the `@ColorInt` annotation. Only 8-bit colors are supported as of now.

### Capabilities
- __Color darkness__ - Can tell if a color is dark or not. A color is said to be dark if a light color (example - White) can be legible on the given color or not.
- __Random color generation__ - Unique algorithms that can generate up to _131K_ different shades of colors that are guaranteed to be dark (based on above definition).
- __Alpha channel manipulation__ - Lets you manipulate the alpha channels of a color easily.  
- __Kotlin specific extensions__ - Extension properties and functions on color Ints are available to make working with them much easier.

### Usage
The `ColorUtils` object has all the goodies.

##### Color darkness
You can know if a color is dark or not by using the following function. Will return `true` if the color is dark, false otherwise.
```kotlin
val isDark = ColorUtils.isColorDark("#009933".asColorIntOrBlack())
```

If you have custom taste in color darkness, you can pass an additional parameter in the same function to tweak the darkness requirement.
```kotlin
val isDark = ColorUtils.isColorDark("#009933".asColorIntOrBlack(), assertionFactor = 0.5)
```
Higher the `assertionFactor`, more "dark" the color needs to be in order to be called as dark from the function call.

##### Darker or lighter shade of a color
You can get the darker or lighter shade of a color easily by using the following functions/extensions.
```kotlin
val color = "#92f1b4".asColorIntOrWhite()

val darkShade = ColorUtils.getDarkenedColor(color) // #8349A2
val darkerShade = darkShade.darkerShade // #764292

val lightShade = ColorUtils.getLightenedColor(color) // #A159C6
val lighterShade = lightShade.lighterShade // #B162DA
```
If you want to tweak how much dark/light the color needs to be, you can pass a float parameter in functions whose value must lie between 0 and 1. Higher the value, the darker/lighter the shade will be.
In case of Int extension, you can use the `darkenBy` or `lightenBy` extension functions.

##### Random color generation
You can generate random colors easily using the following functions. There is no hard-coded colors, as it can generate 131K dark colors.
```kotlin
val darkColor = ColorUtils.getRandomDarkColor()
val lightColor = ColorUtils.getRandomLightColor()
val color = ColorUtils.getRandomColor()
```
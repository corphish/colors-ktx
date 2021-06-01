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
You can know if a color is dark or not by using the following method. Will return `true` if the color is dark, false otherwise.
```kotlin
val isDark = ColorUtils.isColorDark("#009933".asColorIntOrBlack())
```

If you have custom taste in color darkness, you can pass an additional parameter in the same function to tweak the darkness requirement.
```kotlin
val isDark = ColorUtils.isColorDark("#009933".asColorIntOrBlack(), assertionFactor = 0.5)
```
Higher the `assertionFactor`, more "dark" the color needs to be in order to be called as dark from the function call.
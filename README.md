MeFab
=====
todo add the maven bacge here

Floating Action Button but MOVEABLE and EXPANDALBE

Based on the power of [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout) MeFab can change the fab positions while moving it around the screen giving you a butiful design and user friendly experience for user and developer 

Installation
--------
use Gradle:

```gradle
repositories {
  mavenCentral()
}

dependencies {
  //todo
}
```

Or Maven:

```xml
<dependency>
  <groupId>io.github.hussienfahmy</groupId>
  <artifactId>todo</artifactId>
  <version>todo</version>
</dependency>
```

Compatibility
-------------
 **Minimum Android SDK**: API level of 21.
 
 How to use
-------------
Create a menu Describe the icon and id for each edge fab (MeFab currentlly support maximmum 3 items)

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_check"
        android:icon="@drawable/ic_baseline_check_24"
        android:title="Check" />

    <item
        android:id="@+id/menu_clear"
        android:icon="@drawable/ic_baseline_clear_24"
        android:title="Reset" />

    <item
        android:id="@+id/menu_add"
        android:icon="@drawable/ic_baseline_add_24"
        android:title="Add" />
</menu>
```

 In the view XML you want to place MeFab
```xml
    <com.hfahmy.mefab.MovableFloatingExpandedActionButton
        android:id="@+id/me_fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:closeAfterEdgeFabClick="true" // default: false, the fabs on the edge return to center when one of them clicked
        app:menu="@menu/fabs_menu" // the menu describing the edge fabs
        />
```
Finally add click listener to recieve the id as a callback when the edge fab cliked
```kotlin
        binding.meFab.setOnEdgeFabClickListener(OnEdgeFabClickListener { id ->
            Toast.makeText(
                context,
                when (id) {
                    R.id.menu_add -> "Add Clicked"
                    R.id.menu_check -> "Check Mark Clicked"
                    R.id.menu_clear -> "Clear Clicked"
                    else -> ""
                }, Toast.LENGTH_SHORT
            ).show()
        })
```

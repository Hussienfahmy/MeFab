MeFab
=====
todo add the maven badge here

Floating Action Button but MOVABLE and EXPANDABLE

Based on the power
of [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout) MeFab can
change the fab positions while moving it around the screen giving you a beautiful design and user
friendly experience for user and developer

Installation
--------
use Gradle:

```gradle
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
Create a menu Describe the icon and id for each edge fab (MeFab currently support maximum 3 items)

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
        app:closeAfterEdgeFabClick="true"
        app:menu="@menu/fabs_menu"
        />
<!--    app:closeAfterEdgeFabClick: default: false, the fabs on the edge return to center when one of them clicked-->
<!--    app:menu: the menu describing the edge fabs-->
```

Finally add click listener to receive the id as a callback when the edge fab clicked

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

Using By
--------
TODO

Feel free to mak a pull request and your link here

Contribution
--------
All contributions are welcome for resolving issues or enhancing performance

License
--------

    Copyright (c) 2021 Hussien Fahmy

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

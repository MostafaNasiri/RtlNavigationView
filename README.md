<h1>RtlNavigationView</h1>

<p align="center">
<img src="https://github.com/MostafaNasiri/RtlNavigationView/blob/master/RtlNavigationView.png" />
</p>

<p>RtlNavigationView is an extension of Android support library's NavigationView which enables you to have an Rtl NavigationView which is especially useful in apps that are in Right-To-Left languages like Persian.</p>

<h2>How to Use</h2>
<p>Add this line of code in your dependencies:</p>

```
compile 'ir.basalam.rtlnavigationview:rtl-navigationview:1.2'
```

<p>Define your items in a menu resource file. For example I have a menu file called activity_main_drawer.xml:</p>

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
        <item
            android:id="@+id/nav_camera"
            android:icon="@drawable/ic_menu_camera"
            android:title="دوربین" />
        <item
            android:id="@+id/nav_gallery"
            android:icon="@drawable/ic_menu_gallery"
            android:title="گالری" />
        <item
            android:id="@+id/nav_slideshow"
            android:icon="@drawable/ic_menu_slideshow"
            android:title="اسلایدشو" />
        <item
            android:id="@+id/nav_manage"
            android:icon="@drawable/ic_menu_manage"
            android:title="مدیریت" />

        <item android:title="ارتباطات">
            <menu>
                <item
                    android:id="@+id/nav_share"
                    android:icon="@drawable/ic_menu_share"
                    android:title="به اشتراک گذاری" />
                <item
                    android:id="@+id/nav_send"
                    android:icon="@drawable/ic_menu_send"
                    android:title="ارسال" />
            </menu>
        </item>
</menu>
```

<p>Then add RtlNavigationView to your layout and pass your menu file to it like the example below:</p>

```xml
<ir.basalam.rtlnavigationview.RtlNavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="right"
        android:fitsSystemWindows="true"
        app:rtl_menu="@menu/activity_main_drawer"/>
```

<p>If you don't know how to handle NavigationView item clicks you can check the sample app source code.</p>

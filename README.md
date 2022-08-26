# AndroidUI
A light-weight library for building UI in declarative way based on android view system.
* **Declarative:** 

### Main layouts

* **VStack:** Vertical LinearLayout
* **HStack:** Horizontal LinearLayout
* **ZStack:** FrameLayout
* **CStack:** ConstraintLayout
* **RStack:** RecyclerView


### Widgets

* Text
* InputText
* Image
* Button
* ActionButton
* Check
* Switcher
* Seeker
* Spacer
* Divider
* Progress
* StatusProgress
* SuggestionInputText
* Searcher
* Pager
* TabSelector
* SideNavigation
* BottomNavigation
* Toolbar
* Surface
* Video
* Web

## Usage

### Activity
```java
public class MainActivity extends VueActivity {
    @Override
    protected Vue onContent() {
        return Text.create(this)
                .text("Hello World");
    }
}
```

### Fragment
```java
public class MainFragment extends Container {
    @Override
    protected Vue onContent() {
        return Text.create(context())
                .text("Hello World");
    }
}
```

## Other examples

```java
VStack.create(this)
    .child(Text.create(this)
            .text("Text 1"))
    .child(Text.create(this)
            .text("Text 2"))
    .child(Text.create(this)
            .text("Text 3"));
```

```java
Text.create(this)
    .fullSize()
    .textAlign(Gravity.CENTER)
    .textSize(14)
    .textColor(Color.BLACK)
    .text("Hello World")
    .font(R.font.custom_font)
    .background(back -> back
            .backgroundColor(Color.BLUE)
            .strokeColor(Color.RED)
            .strokeWidth(1)
            .rippleColor(Color.GRAY)
            .corner(8)
    );
```

```java
InputText.create(this)
    .fullWidth()
    .height(200)
    .hint(R.string.hint)
    .maxLength(10)
    .numberInput()
    .background(back -> back
            .gradientColors(Color.WHITE, Color.GRAY)
            .gradientOrientation(GradientDrawable.Orientation.TOP_BOTTOM)
            .gradientType(GradientDrawable.LINEAR_GRADIENT)
            .strokeWidth(1)
            .strokeColor(Color.RED)
            .strokeDashWidth(8)
            .strokeDashGap(8)
            .corner(8)
    );
```



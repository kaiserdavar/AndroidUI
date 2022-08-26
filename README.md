# AndroidUI
A light-weight library for building UI in declarative way based on android view system.

## Main layouts

* **VStack:** Vertical LinearLayout
* **HStack:** Horizontal LinearLayout
* **ZStack:** FrameLayout
* **CStack:** ConstraintLayout
* **RStack:** RecyclerView


## Widgets

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

## License

    Copyright 2022 Kaiser Davar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


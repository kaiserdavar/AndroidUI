package com.kaiserdavar.androidui.modifier;

interface CStackModifier<T> {

    T allSidesToParent();
    T horizontalSidesToParent();
    T verticalSidesToParent();
    T topToTopOfParent();
    T topToBottomOfParent();
    T bottomToTopOfParent();
    T bottomToBottomOfParent();
    T startToStartOfParent();
    T startToEndOfParent();
    T endToStartOfParent();
    T endToEndOfParent();
    T topToTopOf(int id);
    T topToBottomOf(int id);
    T bottomToTopOf(int id);
    T bottomToBottomOf(int id);
    T startToStartOf(int id);
    T startToEndOf(int id);
    T endToStartOf(int id);
    T endToEndOf(int id);
    T baselineToBaselineOf(int id);
    T baselineToTopOf(int id);
    T baselineToBottomOf(int id);
    T aroundOf(int id);
    T aroundRadius(int radius);
    T aroundAngle(float angle);
    T horizontalBias(float value);
    T verticalBias(float value);
    T horizontalWeight(float value);
    T verticalWeight(float value);
    T horizontalChain(int style);
    T verticalChain(int style);
    T constrainedWidth(boolean constrained);
    T constrainedHeight(boolean constrained);
    T dimensionRatio(String ratio);
    
}

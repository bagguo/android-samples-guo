# use

## 位置约束

### 基线对齐

```
layout_constraintBaseline_toBaselineOf 
```

### 角度约束

一个控件在某个控件的某个角度的位置

```
app:layout_constraintCircle=""         目标控件id
app:layout_constraintCircleAngle=""    对于目标的角度(0-360)
app:layout_constraintCircleRadius=""   到目标中心的距离

```

### 百分比偏移

让控件在父布局的水平方向或垂直方向的百分之多少的位置

```
app:layout_constraintHorizontal_bias=""   水平偏移 取值范围是0-1的小数
app:layout_constraintVertical_bias=""     垂直偏移 取值范围是0-1的小数
```

### Gone Margin

当依赖的目标view隐藏时会生效的属性，例如B被A依赖约束，当B隐藏时B会缩成一个点，自身的margin效果失效，A设置的GONE
Margin就会生效

## 控件尺寸

###

同时控制多个布局可见性

```
    <androidx.constraintlayout.widget.Group
        android:id="@+id/group_amount"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:visibility="gone"
        app:constraint_referenced_ids="tv_total_amount, tv_total_amount_currency, tv_total_amount_title"
        tools:visibility="visible" />
```
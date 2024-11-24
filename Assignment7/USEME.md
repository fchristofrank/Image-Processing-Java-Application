## Program Execution Documentation

___

<p>
The following documentation summarizes which script commands are supported by our application, and provides examples of using each command.
</p>

___

## Table of Contents

- [Run](#Run)
- [Exit](#Exit)
- [Load](#Load)
- [Save](#Save)
- [Blur](#Blur)
- [Sepia](#Sepia)
- [Sharpen](#Sharpen)
- [Compress](#Compress)
- [Vertical-Flip](#Vertical-Flip)
- [Horizontal-Flip](#Horizontal-Flip)
- [Color-Correct](#Color-Correct)
- [Levels-Adjust](#Levels-Adjust)
- [Red-Component](#Red-Component)
- [Blue-Component](#Blue-Component)
- [Green-Component](#Green-Component)


___
## Things to Note
The user must hit the apply button in order to apply the selected modification to the image.
The image that is user is looking at is considered the preview image, which is different from the actual image.
Whenever the user hits the apply button, the preview image would be the same as the actual image, but otherwise it is not.
Whenever the user saves an image, the actual image is saved, not the previewed image.
If a user has selected a compression of 90%, hit the compression radio button, 
the user will be able to preview what the operation looks like, but if they don't hit the apply button , 
the file that is saved will still be the original file.


___
## Run

Description:

```
Start the GUI by compiling and running the program with no arguments passed in.
```
___
## Exit

Description:

```
Exit the GUI by exiting the window on the top right corner
```
___
## Load

Description:

```
Loads an image into the GUI application on the top left button, the image and it's color histogram will be viewable in the application
```

___

## SAVE

Description:

```
Save the modified image in the application to the file system. The image saved might not be the same as the image displayed, if the user is previewing a image modification.
```

___

## Red-Component

Description:

```
Click on the red-component radio button, and make sure the preview slider is greater than 0 to see the modification.
```

___

## Green-Component

Description:

```
Click on the green-component radio button, and make sure the preview slider is greater than 0 to see the modification.
```

___

## Blue-Component

Description:

```
Click on the blue-component radio button, and make sure the preview slider is greater than 0 to see the modification.

```

___

## Luma-Component

Description:

```
Click on the luma radio button, and make sure the preview slider is greater than 0 to see the modification.
```

___

## Horizontal-Flip

Description:

```
Click on the horizontal radio button.
```

___

## Vertical-Flip

Description:

```
Click on the vertical radio button.
```

___

## Blur

Description:

```
Click on the blur radio button, and make sure the preview slider is greater than 0 to see the modification.

```

___

## Sharpen

Description:

```
Click on the sharpen radio button, and make sure the preview slider is greater than 0 to see the modification.

```

___

## Sepia

Description:

```
Click on the sepia radio button, and make sure the preview slider is greater than 0 to see the modification.
```

___

## Compress

Description:

```
Adjust the compression slider, and then click on the compression radio button to see the modification.\
```

___

## Color-Correct

Description:

```

Click on the color-correct radio button, and make sure the preview slider is greater than 0 to see the modification.

```

___

## Levels-Adjust

Description:

```
Enter valid integers for black, mid and white, and click the levels-adjust compression to see the modification.
```

___

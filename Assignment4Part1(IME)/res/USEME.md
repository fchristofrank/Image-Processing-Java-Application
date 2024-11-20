# Image Processing Application Documentation

This document outlines the script commands supported by our image processing application, along with
examples of their usage and specific conditions.

## Supported Commands

### 1. Load Image

Loads an image into the workspace.

```
load <image_path> <destination_name>
```

**Example:**

```
load res/boston.png boston
```

### 2. Save Image

Saves an image from the workspace.

```
save <destination_path> <source_name>
```

**Example:**

```
save res/boston-bright.png boston-bright
```

### 3. Brighten

Increases the brightness of an image.

```
brighten <amount> <source_name> <destination_name>
```

**Example:**

```
brighten 50 boston boston-bright
```

### 4. Darken

Decreases the brightness of an image.

```
darken <amount> <source_name> <destination_name>
```

**Example:**

```
darken 50 boston boston-dark
```

### 5. Vertical Flip

Flips an image vertically.

```
vertical-flip <source_name> <destination_name>
```

**Example:**

```
vertical-flip boston boston-vflip
```

### 6. Horizontal Flip

Flips an image horizontally.

```
horizontal-flip <source_name> <destination_name>
```

**Example:**

```
horizontal-flip boston boston-hflip
```

### 7. Blur

Applies a blur effect to an image. Optionally, a preview width can be specified.

```
blur <source_name> <destination_name> [preview_width]
```

**Examples:**

```
blur boston boston-blur
blur boston boston-blur-50 50
```

### 8. Sharpen

Sharpens an image. Optionally, a preview width can be specified.

```
sharpen <source_name> <destination_name> [preview_width]
```

**Examples:**

```
sharpen boston boston-sharp
sharpen boston boston-sharp-40 40
```

### 9. Sepia

Applies a sepia tone to an image. Optionally, a preview width can be specified.

```
sepia <source_name> <destination_name> [preview_width]
```

**Examples:**

```
sepia boston boston-sep
sepia boston boston-sep-50 50
```

### 10. RGB Split

Splits an image into its red, green, and blue channels.

```
rgb-split <source_name> <red_dest> <green_dest> <blue_dest>
```

**Example:**

```
rgb-split boston boston-r boston-g boston-b
```

### 11. RGB Combine

Combines separate red, green, and blue channel images into a single RGB image.

```
rgb-combine <destination_name> <red_source> <green_source> <blue_source>
```

**Example:**

```
rgb-combine boston-rgb boston-r boston-g boston-b
```

### 12. Component Extraction

Extracts various components from an image.
Optionally, this command can be used with preview widht.

```
<component>-component <source_name> <destination_name> [preview_width]
```

Where `<component>` can be: value, luma, intensity, red, green, or blue.

**Examples:**

```
value-component boston boston-value 50
luma-component boston boston-luma 40
intensity-component boston boston-intensity 30
red-component boston boston-red 20
green-component boston boston-green 60
blue-component boston boston-blue 70
```

### 13. Compress

Compresses an image with a specified quality factor.

```
compress <quality_factor> <source_name> <destination_name>
```

**Example:**

```
compress 20 boston boston-compress-20
```

### 14. Histogram

Generates a histogram for an image.

```
histogram <source_name> <destination_name>
```

**Example:**

```
histogram boston boston-histogram
```

### 15. Color Correction

Applies color correction to an image. Optionally, a preview width can be specified.

```
color-correct <source_name> <destination_name> [preview_width]
```

**Examples:**

```
color-correct boston boston-color-correction
color-correct boston boston-color-correction-50 50
```

### 16. Levels Adjustment

Adjusts the levels of an image. Optionally, a preview width can be specified.

```
levels-adjust <min> <mid> <max> <source_name> <destination_name> [preview_width]
```

**Examples:**

```
levels-adjust 50 100 150 boston boston-ld
levels-adjust 50 100 150 boston boston-ld-50 50
```

## Notes

- Always load an image before applying any operations to it.
- For operations that generate new images (like rgb-split), remember to save each new image
  separately.
- The order of commands matters. Ensure that source images exist before using them in operations.
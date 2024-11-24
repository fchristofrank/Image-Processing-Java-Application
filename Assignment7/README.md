## Summary

___

<p>
When completing Assignment 4 we leveraged the Model, View, Controller (MVC) representation 
to ingest user-provided commands and properly execute the commands passed. 
The View class, acted as our visual representation that the user interacts with entering commands via the command line.
The Model class acted as our backend. 
The Model class would ingest commands passed through the Controller which originated in the View class and execute the appropriate method based on the command passed.
Finally, the Controller acted as the middle-ware between the View and Model classes.
Acting as the mediator, the View would pass the user input to the Controller which would in turn call the Model class to execute the appropriate method.
</p>

___

## Table of Contents

- [Summary](#Summary)
- [Classes](#Classes)
- [Interfaces](#Interfaces)
- [Saving_and_Loading](#Saving_and_Loading)
- [Images](#Images)
- [MVC_Model](#MVC_Model)
- [Program_Execution](#Program_Execution)
- [Works_Cited](#Works_Cited)

___

## Classes

1. App
2. JPGUtil
3. PNGUtil
4. PPMUtil
5. Constants
6. BasicPixel
7. BasicImage
8. ImageView
9. MockModel
10. ImageModel
11. ImageController
12. GUIImageController
13. JFrameView

___

## Interfaces

1. FileUtil
2. CustomPixel
3. CustomModel
4. CustomImage
5. CustomController
6. CustomView

___

## Saving_and_Loading

1. Each file type has their own custom method of writing to a file and loading from a file,
   implementing the FileUtils interface, which can be found in the file utils folder.
2. Files cannot be overwritten, you cannot write to a file that already exists
3. The model(backend), stores images in a hashMap, where the key represents the name, and the value
   is the image. A value can be overwritten multiple times.

___

## Images

1. The data that is stored on the Model class is a BasicImage, which is essentially a nested list of
   CustomPixel objects.
2. The image contains all the relevant transformations (vertical flip, filters), and returns a new
   object created from the transformation.
3. Model does not implement any of the image transformations, it just checks if the image exists,
   and calls the respective image functions.

___

## MVC_Model

1. The View is only responsible for asking the user for input, can be a shell, execution file, or a
   GUI.
2. The Controller is responsible for parsing and cleaning user input, and giving the controller the
   correct CustomImage objects to save, and converting the CustomImage objects to files.
3. The Model is responsible for storing the CustomImage objects, and forwarding the commands given
   from the controller to the correct CustomImage objects.
4. The Controller and Model communicate with the use of integer status codes, allowing the shell to
   be responsive and help the user identify if commands are incorrect, or files don't exist, or
   invalid arguments.

---

## GUIView and GUIImageController

1. GUIView and GUIImageController allows the application to be a event-driven application.
2. The model class is now only responsible for storing two images, the actual image which is used to
   be downloaded, and the preview image, which is what is seen on the GUI.
3. Whenever an action is performed, the GUIImageController calls the updateImage function, which
   then takes
   in the commands and arguments that are selected, creates the command, and call the parent
   ImageController to run the command and save it to the preview BasicImage
4. Whenever the apply button is pressed, the GUIImageController, takes in the arguments that are
   selected, creates the command, and then passes the command to the parent ImageController class,
   which then runs the command and save it to the image BasicImage object.
   ImageController

---

## Program_Execution

<p>In order to run the application please follow the directions below:</p>

`Compile the App.java to a App.class file`
<p>Run the file by with the -file filename argument when starting the program to execute a file</p>
<p>Run the file by with the -text argument when starting the program to execute in a shell-based environment</p>
<p>Run the file with no argument to start a GUI-based view</p>
___

## Adaptations from Assignment 4 (Part 1) to Assignment 5 (Part 2)

- Removed the view class, and add the ImageController read in from a InputStream variable that is
  declared during the creation of the ImageController object
- CustomController, CustomModel, and CustomImage now is able to parse the new commands (compression,
  color-correction, levels-adjust), and also accept the split option for preview operations. The
  existing implementations of these interfaces have been modified as well to perform these new
  operations correctly.
- Modified existing functions in BasicImage to accept the preview argument
- Refactored code in ImageController, ImageMode, and BasicImage to improve code reuse
- Added skyline.png to test the compression and visually verify the compression function
- App.java is able to take in arguments when initially running the file, allowing a user to pass in
  a file as an argument, the application would then run the file
- Seperated the mvc package into controller package, and model package
- Added the MockModel class to test and validate the interaction between the controller and the
  model.
- Added tests to verify the correctness of new and modified functions

___

## Adaptations from Assignment 5 (Part 2) to Assignment 6 (Part 3)

- Reintroduced the view class, now in the view package, in order to support the GUI view
- Re-implemented the commands as per the Command Design Pattern, and also introduced the mask-apply
  command.
- Created GUIImageController, which extends the ImageController, which is used exclusively for the
  GUI view, and now allows the application to be of a event-driven architecture.
- Modified the compression function, to be significantly faster, to allow the GUI to be more
  responsive
- Modified App.java to accept the text argument
- Slight code refactoring based on the point deductions in Assignment 4 and 5

___

## Works_Cited

1. Oblivion11. (2021). Pikachu. Pokemon Unite Unofficial Fan Site. Retrieved October 21, 2024,
   from https://www.pokemonunite.gg/build/2623/pikachu-fat-pikachu.
2. dazzlerlemmykoopa200. (2023). Mario-Original-PNG. Deviant Art. Jul 24, 2023. Retrieved October
   21, 2024, from https://www.deviantart.com/dazzlerlemmykoopa200/art/Mario-8bit-973525963.
3. mufiinchihiro. (2011). Mario-Original. Deviant Art. Retrieved October 21, 2024,
   from https://www.deviantart.com/mufiinchihiro/art/cursor-mario-bros-animado-221200182.
4. Su, E. (2022). Singapore Skyline. SPIEGAL Business. Retrieved November 6, 2024,
   from https://www.spiegel.de/wirtschaft/soziales/new-york-und-singapur-sind-die-teuersten-staedte-der-welt-a-42f95374-9b5c-4c14-a518-1326280cc031.

___
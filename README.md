# ImageManipulationAndEnhancement

An application to enhance and manipulte images.

Design Objective:

    Enforce SOLID principles. 
    Minimum to No change to existing class files.
    Should be easy to onboard new operations.
    Avoid Duplicate codes.
    Configurable code practice.


View :

    Interface 
    1. CommandExecutor : It contains the method to start the execution with a run method to start listening to the CLI commands.
        Class: 
            1.ImageProcessorCLI Implements the run method to continuosly listen to the user commands or a script file.
    
    2. OperationCreator : Contains method to connect to the controllers of each operations based on the user command.
        Class: 
            1.ImageOperationFactory: Creates operation objects to connect CLI inputs and arguments to the controller interface.



Controller:

    Interface
    1. CLIOperation : Mandates the need for execute method method which calls the operation class.
        AbstractClass : 
        1. AbstractOperation : Maintains the image cache object per execution to store user images and supports the function of adding and getting images. 
        Also contains the common function of validating the command arguments.
            AbstractClass :
                1. AdjustBrightness : Conducts basic check before handing over the image to operations.
                    Brighten: Concrete class overriding the AdjustBrightness execute method based on brightness operation.
                    Darken : overrides the execute method specific to darken operation.
                2. Filter : Handles calls to blur or sharpen method by creating operation based on the command passed.
                3. Flip : Conducts sanity check of argument and image involved in operation.
                    VerticalFlip : returns the vertical flip operation object to interact with the model layer.
                    HorizontalFlip : returns the horizontal flip operation object.
                4. Visualize : Controller class that handles all the request to visualize functions involving RGB component, luma, intensity and value.
            CombineRGB : Handles the operation of combining three images into one RGB image. Validates dimensions of  object and checks for valid values.
            Load : This class loads the given image from the path which inturn uses the reader based on format of image.
            Save : Saves the image to given path, by converting it to buffered image before using the ImageIO JDK.
            Sepia : Handles the sepia image operation by request and args validation.

Operations:
Interface
1.ImageOperation
2.MultiplImageOperation

        AbstractClass : 
            1. AbstractVisualize:
                VisualizeRed
                VisualizeGreen
                VisualizeBlue
                VisualizeLuma
                VisualizeIntensity
                VisualizeValue
            2. ApplyBrightness:
            3. ApplyFlip
                ApplyHorizontalFlip
                ApplyVerticalFlip
            4. Filter
                Blur
                Sharpen

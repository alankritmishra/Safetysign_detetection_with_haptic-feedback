
# Safety Sign Detection with Haptic Feedback

This project is a safety sign detection android application that detects three kinds of threat level- danger, caution and prohibitory. For detecting the safety signs, the application uses the video feed from the mobile's primary camera. Upon detection, the app will provide a haptic and speech feedback to notify user about the potential threat.

### Dataset

[Safety_sign_dataset.zip](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety_sign_dataset.zip) contains our custom dataset that we collected and percolated. It consists of traffic and hazardous signs from a variety of sources. The dataset has been manually scanned and assigned an appropriate threat label and resized to a uniform size. 

### YOLO v5Model Training

[Safety_sign_dataset.zip](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety_sign_dataset.zip) file contains the source code used for training our custom dataset on YOLOv5 model. The trained model weights are uploaded here [insert lini].

### Android Application

[Safety Sign Detection Android](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/tree/main/Safety%20Sign%20Detection%20Android) contains the source code for the android application. Incase if you wish to train your own model, after training  use the Python script `export.py` in the `models` folder of the [YOLOv5 repo](https://github.com/ultralytics/yolov5) to generate a TorchScript-formatted YOLOv5 model named `best.torchscript.ptl` for mobile apps. Place the optimised model in `Safety Sign Detection Android\app\src\main\assets`  folder. 

Start Android Studio, then open the project located in [Safety Sign Detection Android](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/tree/main/Safety%20Sign%20Detection%20Android). 

#### App screenshots

[Screenshot 1](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety%20Sign%20Detection%20Android/screenshots/screenshot_1.jpg)

[Screenshot 2](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety%20Sign%20Detection%20Android/screenshots/screenshot_2.PNG)

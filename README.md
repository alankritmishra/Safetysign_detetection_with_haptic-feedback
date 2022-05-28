
# Safety Sign Detection with Haptic Feedback

When walking on the road or navigating unfamiliar areas, the elderly, visually handicapped, and persons with hearing loss encounter challenges. The information on numerous safety signs such as road signs, traffic signs, workplace safety signs or industrial hazard signs is usually unhelpful to them. This paper proposes a solution that can detect a comprehensive set of safety signs in real-time. Our app runs a deep learning model pre-trained on a custom-built dataset. The deep learning model we have used is explicitly built for object detection to find regions of interest, create appropriate bounding boxes, and classify the signs with three different levels of severity - Danger, Caution, and Prohibitory. The camera-equipped smartphone relays haptic or audio feedback upon the successful detection of a safety sign.

## Dataset

[Safety_sign_dataset](https://drive.google.com/drive/folders/1Me8nEczFo_LSSrHP2wKQj4zGawN8vRQB?usp=sharing) contains our custom dataset that we collected and percolated. It consists of traffic and hazardous signs from a variety of sources. The dataset has been manually scanned and assigned an appropriate label based on the level of attention or action required and resized to a uniform size. 

You need a `data.yaml` file with the `train` and `val` directory paths to read the dataset. The dataset directory itself should contain `images` and `labels` directories. The `images` directory should contain all the images and the `labels` directory should contain all the corresponding labels. Both the directories should contain `train` and `val` subdirectories. The `train` and `val` directories should contain the images and labels for training and validation respectively. Labels should be in `txt` format and should have the same name as the corresponding image.

## YOLO v5Model Training

[YOLOv5_model_training](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/CV_project_safty_sign_yolo5.ipynb) file contains the source code used for training our custom dataset on YOLOv5 model. The trained model weights are uploaded [here](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/best.pt)

## Android Application

[Safety Sign Detection Android](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/tree/main/Safety%20Sign%20Detection%20Android) contains the source code for the android application. Incase if you wish to train your own model, after training  use the Python script `export.py` in the `models` folder of the [YOLOv5 repo](https://github.com/ultralytics/yolov5) to generate a TorchScript-formatted YOLOv5 model named `best.torchscript.ptl` for mobile apps. Place the optimised model in `Safety Sign Detection Android\app\src\main\assets`  folder. 

Start Android Studio, then open the project located in [Safety Sign Detection Android](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/tree/main/Safety%20Sign%20Detection%20Android). 

### App screenshots
<img src="https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety%20Sign%20Detection%20Android/screenshots/screenshot_1.jpg" width="20%" height="20%">     <img src="https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety%20Sign%20Detection%20Android/screenshots/screenshot_2.PNG" width="21%" height="20%">

<!-- ![Screenshot 1](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety%20Sign%20Detection%20Android/screenshots/screenshot_1.jpg)width="25px -->

<!-- ![Screenshot 2](https://github.com/alankritmishra/Safetysign_detetection_with_haptic-feedback/blob/main/Safety%20Sign%20Detection%20Android/screenshots/screenshot_2.PNG)-->

## Code Reference
<a href="https://doi.org/10.5281/zenodo.4679653"><img src="https://zenodo.org/badge/DOI/10.5281/zenodo.4679653.svg" alt="DOI"></a>

import cv2 as cv
import webbrowser
import subprocess

capture = cv.VideoCapture(0)

def changeRes(width, height):
    capture.set(3, width)
    capture.set(4, height)

#values for outline
x1, x2, x3, x4 = (170, 270, 370, 470)
y1, y2, y3, y4 = (90, 190, 290, 390)

#center coordinates for color detection
c1x, c1y = (int((x1+x2)/2.0), int((y1+y2)/2.0))
c2x, c2y = (int((x2+x3)/2.0), int((y1+y2)/2.0))
c3x, c3y = (int((x3+x4)/2.0), int((y1+y2)/2.0))
c4x, c4y = (int((x1+x2)/2.0), int((y2+y3)/2.0))
c5x, c5y = (int((x2+x3)/2.0), int((y2+y3)/2.0))
c6x, c6y = (int((x3+x4)/2.0), int((y2+y3)/2.0))
c7x, c7y = (int((x1+x2)/2.0), int((y3+y4)/2.0))
c8x, c8y = (int((x2+x3)/2.0), int((y3+y4)/2.0))
c9x, c9y = (int((x3+x4)/2.0), int((y3+y4)/2.0))

#arrays to store color values
side1 = []
side2 = []
side3 = []
side4 = []
side5 = []
side6 = []
sides = [side1, side2, side3, side4, side5, side6]

#Method to scan a sticker and get its color
def getColor(img, x, y):
    if (img[y,x,0] > 130 and img[y,x,1] > 130 and img[y,x,2] > 130):
        return 'W'
    elif (img[y,x,0] > img[y,x,1] and img[y,x,2] < 100):
        return 'B'
    elif (img[y,x,0] < img[y,x,1] and img[y,x,2] < 100):
        return 'G'
    elif (img[y,x,2] < 150):
        return 'R'
    elif ((img[y,x,1]-img[y,x,2]) > 50):
        return 'O'
    else:
        return 'Y'

#Method to draw outline of cube
def drawOutline():
    cv.rectangle(frame, (x2,y2), (x3,y3), (255,0,0), thickness=2)
    cv.rectangle(frame, (x1,y2), (x2,y3), (255,0,0), thickness=2)
    cv.rectangle(frame, (x3,y2), (x4,y3), (255,0,0), thickness=2)
    cv.rectangle(frame, (x2,y1), (x3,y2), (255,0,0), thickness=2)
    cv.rectangle(frame, (x1,y1), (x2,y2), (255,0,0), thickness=2)
    cv.rectangle(frame, (x3,y1), (x4,y2), (255,0,0), thickness=2)
    cv.rectangle(frame, (x2,y3), (x3,y4), (255,0,0), thickness=2)
    cv.rectangle(frame, (x1,y3), (x2,y4), (255,0,0), thickness=2)
    cv.rectangle(frame, (x3,y3), (x4,y4), (255,0,0), thickness=2)

#adds text to webcam feed to help orient cube correctly
def addText(centerColor, topColor, bottomColor, leftColor, rightColor):
    cv.putText(frame, 'Center Color = ' + centerColor, (130,30), cv.FONT_HERSHEY_TRIPLEX, 1, (255,0,0), thickness=2)
    cv.putText(frame, topColor, (310,80), cv.FONT_HERSHEY_TRIPLEX, 1, (255,0,0), thickness=2)
    cv.putText(frame, bottomColor, (310,420), cv.FONT_HERSHEY_TRIPLEX, 1, (255,0,0), thickness=2)
    cv.putText(frame, leftColor, (140,250), cv.FONT_HERSHEY_TRIPLEX, 1, (255,0,0), thickness=2)
    cv.putText(frame, rightColor, (480,250), cv.FONT_HERSHEY_TRIPLEX, 1, (255,0,0), thickness=2)

#collects colors for white side
while True:
    #getting webcam frame
    isTrue, frame = capture.read()

    #drawing outline of cube and adding code to help orient cube for scanning
    drawOutline()
    addText('White', 'B', 'G', 'O', 'R')
    
    #test code (prints out BGR values for center sticker)
    cv.putText(frame, str(frame[c5y,c5x,0]) + " " + str(frame[c5y,c5x,1]) + " " + str(frame[c5y,c5x,2]), (70,70), cv.FONT_HERSHEY_TRIPLEX, 1.0, (0,0,255), thickness=1)

    #displays webcam feed
    cv.imshow('Webcam', frame)

    if cv.waitKey(20) & 0xFF==ord(' '):
        #Collecting sticker colors
        side1.append(getColor(frame, c1x, c1y))
        side1.append(getColor(frame, c2x, c2y))
        side1.append(getColor(frame, c3x, c3y))
        side1.append(getColor(frame, c4x, c4y))
        side1.append(getColor(frame, c5x, c5y))
        side1.append(getColor(frame, c6x, c6y))
        side1.append(getColor(frame, c7x, c7y))
        side1.append(getColor(frame, c8x, c8y))
        side1.append(getColor(frame, c9x, c9y))

        print(side1)
        break

#collects colors for green side
while True:
    #getting webcam frame
    isTrue, frame = capture.read()

    #drawing outline of cube and adding code to help orient cube for scanning
    drawOutline()
    addText('Green', 'W', 'Y', 'O', 'R')
    
    #test code (prints out BGR values for center sticker)
    #cv.putText(frame, str(frame[c5y,c5x,0]) + " " + str(frame[c5y,c5x,1]) + " " + str(frame[c5y,c5x,2]), (70,70), cv.FONT_HERSHEY_TRIPLEX, 1.0, (0,0,255), thickness=1)

    #displays webcam feed
    cv.imshow('Webcam', frame)

    if cv.waitKey(20) & 0xFF==ord(' '):
        #Collecting sticker colors
        side2.append(getColor(frame, c1x, c1y))
        side2.append(getColor(frame, c2x, c2y))
        side2.append(getColor(frame, c3x, c3y))
        side2.append(getColor(frame, c4x, c4y))
        side2.append(getColor(frame, c5x, c5y))
        side2.append(getColor(frame, c6x, c6y))
        side2.append(getColor(frame, c7x, c7y))
        side2.append(getColor(frame, c8x, c8y))
        side2.append(getColor(frame, c9x, c9y))

        print(side2)
        break

#collects colors for the red side
while True:
    #getting webcam frame
    isTrue, frame = capture.read()

    #drawing outline of cube and adding code to help orient cube for scanning
    drawOutline()
    addText('Red', 'W', 'Y', 'G', 'B')
    
    #test code (prints out BGR values for center sticker)
    #cv.putText(frame, str(frame[c5y,c5x,0]) + " " + str(frame[c5y,c5x,1]) + " " + str(frame[c5y,c5x,2]), (70,70), cv.FONT_HERSHEY_TRIPLEX, 1.0, (0,0,255), thickness=1)

    #displays webcam feed
    cv.imshow('Webcam', frame)

    if cv.waitKey(20) & 0xFF==ord(' '):
        #Collecting sticker colors
        side3.append(getColor(frame, c1x, c1y))
        side3.append(getColor(frame, c2x, c2y))
        side3.append(getColor(frame, c3x, c3y))
        side3.append(getColor(frame, c4x, c4y))
        side3.append(getColor(frame, c5x, c5y))
        side3.append(getColor(frame, c6x, c6y))
        side3.append(getColor(frame, c7x, c7y))
        side3.append(getColor(frame, c8x, c8y))
        side3.append(getColor(frame, c9x, c9y))

        print(side3)
        break

#collects colors for the blue side
while True:
    #getting webcam frame
    isTrue, frame = capture.read()

    #drawing outline of cube and adding code to help orient cube for scanning
    drawOutline()
    addText('Blue', 'W', 'Y', 'R', 'O')
    
    #test code (prints out BGR values for center sticker)
    #cv.putText(frame, str(frame[c5y,c5x,0]) + " " + str(frame[c5y,c5x,1]) + " " + str(frame[c5y,c5x,2]), (70,70), cv.FONT_HERSHEY_TRIPLEX, 1.0, (0,0,255), thickness=1)

    #displays webcam feed
    cv.imshow('Webcam', frame)

    if cv.waitKey(20) & 0xFF==ord(' '):
        #Collecting sticker colors
        side4.append(getColor(frame, c1x, c1y))
        side4.append(getColor(frame, c2x, c2y))
        side4.append(getColor(frame, c3x, c3y))
        side4.append(getColor(frame, c4x, c4y))
        side4.append(getColor(frame, c5x, c5y))
        side4.append(getColor(frame, c6x, c6y))
        side4.append(getColor(frame, c7x, c7y))
        side4.append(getColor(frame, c8x, c8y))
        side4.append(getColor(frame, c9x, c9y))

        print(side4)
        break

#collects colors for the orange side
while True:
    #getting webcam frame
    isTrue, frame = capture.read()

    #drawing outline of cube and adding code to help orient cube for scanning
    drawOutline()
    addText('Orange', 'W', 'Y', 'B', 'G')
    
    #test code (prints out BGR values for center sticker)
    #cv.putText(frame, str(frame[c5y,c5x,0]) + " " + str(frame[c5y,c5x,1]) + " " + str(frame[c5y,c5x,2]), (70,70), cv.FONT_HERSHEY_TRIPLEX, 1.0, (0,0,255), thickness=1)

    #displays webcam feed
    cv.imshow('Webcam', frame)

    if cv.waitKey(20) & 0xFF==ord(' '):
        #Collecting sticker colors
        side5.append(getColor(frame, c1x, c1y))
        side5.append(getColor(frame, c2x, c2y))
        side5.append(getColor(frame, c3x, c3y))
        side5.append(getColor(frame, c4x, c4y))
        side5.append(getColor(frame, c5x, c5y))
        side5.append(getColor(frame, c6x, c6y))
        side5.append(getColor(frame, c7x, c7y))
        side5.append(getColor(frame, c8x, c8y))
        side5.append(getColor(frame, c9x, c9y))

        print(side5)
        break

#collects colors for the yellow side
while True:
    #getting webcam frame
    isTrue, frame = capture.read()

    #drawing outline of cube and adding code to help orient cube for scanning
    drawOutline()
    addText('Yellow', 'O', 'R', 'B', 'G')
    
    #test code (prints out BGR values for center sticker)
    #cv.putText(frame, str(frame[c5y,c5x,0]) + " " + str(frame[c5y,c5x,1]) + " " + str(frame[c5y,c5x,2]), (70,70), cv.FONT_HERSHEY_TRIPLEX, 1.0, (0,0,255), thickness=1)

    #displays webcam feed
    cv.imshow('Webcam', frame)

    if cv.waitKey(20) & 0xFF==ord(' '):
        #Collecting sticker colors
        side6.append(getColor(frame, c1x, c1y))
        side6.append(getColor(frame, c2x, c2y))
        side6.append(getColor(frame, c3x, c3y))
        side6.append(getColor(frame, c4x, c4y))
        side6.append(getColor(frame, c5x, c5y))
        side6.append(getColor(frame, c6x, c6y))
        side6.append(getColor(frame, c7x, c7y))
        side6.append(getColor(frame, c8x, c8y))
        side6.append(getColor(frame, c9x, c9y))

        print(side6)
        break

capture.release()
cv.destroyAllWindows()

color_data = ''
for side in sides:
    for color in side:
        color_data += color

print(color_data)

#code to input color data into java program and get output of solution
url = subprocess.check_output('java Driver ' + color_data, stderr=subprocess.PIPE)
#url = str(url)
#url_formatted.join(s for s in url if ord(s)>31 and ord(s)<126)

#print(url)

#code to open a google tab showing an animation of the solution

#chrome_path = 'C:/Program Files/Google/Chrome/Application/chrome.exe %s'

#webbrowser.get(chrome_path).open(str(url_formatted))
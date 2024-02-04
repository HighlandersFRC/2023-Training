import cv2
import time
import datetime

ip_address_ender = 44 # ip address ender for front limelight
totalTime = 5.0  # how long you want the video to be (in seconds)
firstTime = True

cap = cv2.VideoCapture('http://10.44.99.44:5800', cv2.CAP_FFMPEG) # starts the video capture of stream using ffmpeg as backend
print('backend: ' + str(cap.getBackendName())) # prints to ensure pipeline is created

width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH) + 0.5) # gets the video width (pixels)
height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT) + 0.5) # gets the ideo height (pixels)
size = (width, height) # stores the size of the stream
print('size: ' + str(size))

fourcc = cv2.VideoWriter_fourcc(*'XVID') # sets the fourcc codec for video stream (XVID for .avi, mp4v for .mp4)

videoName = str(datetime.datetime.now()).replace('.', '-').replace(' ', '-').replace(':', '-') # stores the datetime so video can be named well
splitList = videoName.split('-') # splits the string into indivual strings
year = splitList[0]
month = splitList[1]
day = splitList[2]
hour = splitList[3]
minute = splitList[4]
second = splitList[5]
decimal = splitList[6]
videoName = 'LL' + str(ip_address_ender) + 'Video-' + year + 'Y-' + month + 'M-' + day + 'D-' + hour + 'h-' + minute + 'm-' + second + 's.avi' # final name for video file, currentlt set as .avi type

out = cv2.VideoWriter(videoName, fourcc, 20.0, size, True) #creates writer object

while(True):
    _, frame = cap.read()
    # cv2.imshow('Recording...', frame) # shows video stream on laptop (commented out for limelight)
    out.write(frame) # writes frame to file
    if(firstTime): # sets the time when video capture starts, to ensure video last certain amount of time
        firstTime = False
        startTime = time.time()
        print('Recording Started')
    if time.time() - startTime >= totalTime: # ends video capture after time is up
        print('Time Elapsed: ' + str(time.time() - startTime))
        print('Recording Ended')
        break
cap.release() # stops video streams
out.release() # stops video streams
# cv2.destroyAllWindows() # ends showing video stream on laptop
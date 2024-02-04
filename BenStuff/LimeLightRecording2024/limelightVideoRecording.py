import cv2
import time
import datetime
ip_address_ender = 44
totalTime = 5.0  #how long you want the video to be (in seconds)
cap = cv2.VideoCapture('http://10.44.99.44:5800', cv2.CAP_FFMPEG)
print('backend: ' + str(cap.getBackendName()))
width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH) + 0.5)
height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT) + 0.5)
size = (width, height)
print('size: ' + str(size))
fourcc = cv2.VideoWriter_fourcc(*'XVID')
videoName = str(datetime.datetime.now()).replace('.', '-').replace(' ', '-').replace(':', '-')
splitList = videoName.split('-')
year = splitList[0]
month = splitList[1]
day = splitList[2]
hour = splitList[3]
minute = splitList[4]
second = splitList[5]
decimal = splitList[6]
videoName = 'LL' + str(ip_address_ender) + 'Video-' + year + 'Y-' + month + 'M-' + day + 'D-' + hour + 'h-' + minute + 'm-' + second + 's.avi'
out = cv2.VideoWriter(videoName, fourcc, 20.0, size, True)
firstTime = False

while(True):
    _, frame = cap.read()
    # cv2.imshow('Recording...', frame)
    out.write(frame)
    if(firstTime == False):
        firstTime = True
        startTime = time.time()
    if time.time() - startTime >= totalTime:
        print('time elapsed: ' + str(time.time() - startTime))
        break
cap.release()
out.release()
# cv2.destroyAllWindows()
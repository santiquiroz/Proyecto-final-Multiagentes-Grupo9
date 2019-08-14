import serial, time
arduino = None
def run():
    global arduino

    archivo = open("hello.txt", "r")

    entrada=archivo.read()

    if(entrada is not None):

        if(int(entrada) == 1):
            arduino.flushInput()
            arduino.flushOutput()
            arduino.write(entrada.encode())
            rawString = arduino.readline()
            arduino.flushInput()
            arduino.flushOutput()
            print(str(rawString))
        elif(int(entrada) == 0):
            arduino.write(entrada.encode())


if __name__ == "__main__":
    arduino = serial.Serial("COM4", 9600)
    time.sleep(1)
    while(True):
        run()
